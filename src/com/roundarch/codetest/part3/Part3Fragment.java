package com.roundarch.codetest.part3;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.roundarch.codetest.R;

import java.util.ArrayList;
import java.util.Map;

public class Part3Fragment extends Fragment {

    IntentFilter filter;
    MyBroadCastReceiver myBroadCastReceiver;
    ServiceConnection serviceConnection;
    Part3Service part3Service;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part3, null);

        View emptyView = (View) view.findViewById(R.id.empty_textview);
        listView = (ListView) view.findViewById(R.id.part3_listview);
        listView.setEmptyView(emptyView);

        // TODO - the listview will need to be provided with a source for data

        // TODO - (optional) you can set up handling to list item selection if you wish

        myBroadCastReceiver = new MyBroadCastReceiver();
        filter  = new IntentFilter();
        filter.addAction(Part3Service.ACTION_SERVICE_DATA_UPDATED);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Part3Service.Part3ServiceBinder binder = (Part3Service.Part3ServiceBinder) iBinder;
                part3Service = binder.getService();
                part3Service.refreshData();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                part3Service = null;
            }
        };

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getActivity(), Part3Service.class);
        getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unbindService(serviceConnection);
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().registerReceiver(myBroadCastReceiver, filter);

        if (part3Service!=null) {
            part3Service.refreshData();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        getActivity().unregisterReceiver(myBroadCastReceiver);
    }

    class MyBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Part3Service.ACTION_SERVICE_DATA_UPDATED)) {
                ArrayList<? extends Map<String, String>> data = intent.getParcelableArrayListExtra("results");
                ArrayList<Map<String, String>> data2 = new ArrayList<>();
                for(Map<String, String> dataValues: data){
                    data2.add(dataValues);
                }
                ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(), data2);
                listView.setAdapter(listViewAdapter);
            }
        }
    }
}
