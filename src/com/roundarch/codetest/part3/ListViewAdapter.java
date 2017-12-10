package com.roundarch.codetest.part3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.roundarch.codetest.R;

import java.util.ArrayList;
import java.util.Map;

public class ListViewAdapter extends ArrayAdapter<Map<String, String>> {
    public ListViewAdapter(Context context, ArrayList<Map<String, String>> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Map<String, String> data = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.part3_listview_item,
                    parent, false);
        }

        TextView tvZipCode = view.findViewById(R.id.zipcode);
        tvZipCode.setText("Zipcode: " + data.get("zipcode"));

        TextView tvZipClass = view.findViewById((R.id.zipclass));
        tvZipClass.setText("Zipclass: " + data.get("zipclass"));

        return view;
    }
}
