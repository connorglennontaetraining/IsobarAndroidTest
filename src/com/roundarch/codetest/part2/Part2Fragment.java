package com.roundarch.codetest.part2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.roundarch.codetest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class Part2Fragment extends Fragment {
    public static String EXTRA_MODEL = "extra_model";
    private DataModel mModel = new DataModel();

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;

    Unbinder mButterKnifeUnbinder;

    @Override
    public View
            onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_part2, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EXTRA_MODEL)) {
                mModel = (DataModel) savedInstanceState.get(EXTRA_MODEL);
            }
        }
        mButterKnifeUnbinder = ButterKnife.bind(this, view);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_edit();
            }
        });
        setTextViews();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mButterKnifeUnbinder.unbind();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_MODEL, mModel);

        super.onSaveInstanceState(outState);
    }

    public void onClick_edit() {
        Intent intent = new Intent(this.getActivity(), EditActivity.class);
        intent.putExtra(EXTRA_MODEL, (Parcelable) mModel);
        startActivityForResult(intent, 1);
    }

    private void getModelFromIntent(Intent data){
        mModel = data.getParcelableExtra(EXTRA_MODEL);
        setTextViews();
    }

    private void setTextViews() {
        textView1.setText(mModel.getText1());
        textView2.setText(mModel.getText2());
        textView3.setText(String.valueOf(mModel.getText3()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            getModelFromIntent(data);
        }
    }
}
