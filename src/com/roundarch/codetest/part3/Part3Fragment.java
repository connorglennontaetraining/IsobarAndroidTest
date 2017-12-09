package com.roundarch.codetest.part3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.roundarch.codetest.R;

public class Part3Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part3, null);

        View emptyView = (View) view.findViewById(R.id.empty_textview);
        ListView listView = (ListView) view.findViewById(R.id.part3_listview);
        listView.setEmptyView(emptyView);

        // TODO - the listview will need to be provided with a source for data

        // TODO - (optional) you can set up handling to list item selection if you wish

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // TODO - when the fragment resumes, it would be a good time to register to receieve broadcasts
        // TODO - from the service.  The broadcast will serve as a way to inform us that data is available
        // TODO - for consumption

        // TODO - this is also a good place to leverage the Service's IBinder interface to tell it you want
        // TODO - to refresh data
    }

    @Override
    public void onPause() {
        super.onPause();


    }

    // TODO - our listView needs a source of data, and here might be a good place to create that

    // TODO - we also need a means of responding to the Broadcasts sent by our Service

}
