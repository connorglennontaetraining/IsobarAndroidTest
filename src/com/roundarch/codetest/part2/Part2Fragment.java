package com.roundarch.codetest.part2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.roundarch.codetest.R;

public class Part2Fragment extends Fragment {
    private static String EXTRA_MODEL = "extra_model";
    private DataModel mModel = new DataModel();

    private TextView textView1;
    private TextView textView3;
    private TextView textView2;

    @Override
    public View
            onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part2, null);

        // TODO -
        view.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_edit();
            }
        });

        textView1 = (TextView)view.findViewById(R.id.textView1);
        textView2 = (TextView)view.findViewById(R.id.textView2);
        textView3 = (TextView)view.findViewById(R.id.textView3);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EXTRA_MODEL)) {
                mModel = (DataModel) savedInstanceState.get(EXTRA_MODEL);
            }
        }

        setTextViews();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_MODEL, mModel);

        super.onSaveInstanceState(outState);
    }

    public void onClick_edit() {
        // TODO - package up the data model and provide it to the new EditActivity as it is being created
        Intent intent = new Intent(this.getActivity(), EditActivity.class);

        // TODO - this probably isn't the best way to start the EditActivty, try to fix it
        startActivity(intent);
    }

    // TODO - provide a method to obtain the data model when it is returned from the EditActivity

    private void setTextViews() {
        textView1.setText(mModel.getText1());
        textView2.setText(mModel.getText2());
        textView3.setText(String.valueOf(mModel.getText3()));
    }
}
