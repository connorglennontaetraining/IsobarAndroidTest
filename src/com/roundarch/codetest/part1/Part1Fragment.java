package com.roundarch.codetest.part1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import com.roundarch.codetest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by mdigiovanni on 8/15/13.
 */
public class Part1Fragment extends Fragment {
    // TODO - any member variables you need to store?
    @BindView(R.id.sbTop)
    SeekBar mSeekBarTop;
    @BindView(R.id.sbBottom)
    SeekBar getmSeekBarBottom;
    @BindView(R.id.switchSyncSeekBars)
    Switch mSwitchSyncSeekBars;

    //FIXME: Improve something! Anything
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part1, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO - obtain references to your views from the layout

        // TODO - hook up any event listeners that make sense for the task
    }
}
