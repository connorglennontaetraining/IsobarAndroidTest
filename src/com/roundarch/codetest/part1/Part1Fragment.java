package com.roundarch.codetest.part1;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import com.roundarch.codetest.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mdigiovanni on 8/15/13.
 */
public class Part1Fragment extends Fragment {
    // TODO - any member variables you need to store?
    @BindView(R.id.sbTop)
    SeekBar mSeekBarTop;
    @BindView(R.id.sbBottom)
    SeekBar mSeekBarBottom;
    @BindView(R.id.switchSyncSeekBars)
    Switch mSwitchSyncSeekBars;
    @BindView(R.id.tvDifference)
    TextView mtvDifference;

    Unbinder mButterKnifeUnbinder;

    int mTopValue, mBottomValue, mDifference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_part1, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButterKnifeUnbinder = ButterKnife.bind(this, view);

        mTopValue = mSeekBarTop.getProgress();
        mBottomValue = mSeekBarBottom.getProgress();

        addListener(mSeekBarTop, mSeekBarBottom);
        addListener(mSeekBarBottom, mSeekBarTop);
    }

    private void addListener(SeekBar target, final SeekBar syncBuddy){
        target.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            boolean isTracking = false;
            int dI;
            int lastValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(isTracking) {
                    int dI = i - lastValue;
                    if (mSwitchSyncSeekBars.isChecked()) {
                        syncBuddy.setProgress(syncBuddy.getProgress() + dI);
                    }
                    updateDifference();
                }
                lastValue = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isTracking = true;
                dI = 0;
                lastValue = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isTracking = false;
            }
        });
    }

    private void updateDifference(){
        int difference = mSeekBarTop.getProgress() - mSeekBarBottom.getProgress();
        mtvDifference.setText("" + difference);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mButterKnifeUnbinder.unbind();
    }
}
