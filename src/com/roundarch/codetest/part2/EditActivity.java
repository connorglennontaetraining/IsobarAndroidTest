package com.roundarch.codetest.part2;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.roundarch.codetest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EditActivity extends FragmentActivity {

    EditFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        fragment = (EditFragment) getSupportFragmentManager().findFragmentById(R.id.edit_fragment);
        DataModel dataModel = getIntent().getParcelableExtra(Part2Fragment.EXTRA_MODEL);
        fragment.setModel(dataModel);
    }
}
