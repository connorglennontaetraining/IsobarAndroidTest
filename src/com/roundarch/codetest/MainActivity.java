package com.roundarch.codetest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.roundarch.codetest.part1.Part1Fragment;
import com.roundarch.codetest.part2.Part2Fragment;
import com.roundarch.codetest.part3.Part3Fragment;
import com.roundarch.codetest.part3.Part3Service;


public class MainActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private Part3Service.Part3ServiceBinder zipBinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(new TestPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void onStart() {
        super.onStart();

        // TODO - for Part3, this might be a good place to bind to our Service
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public class TestPagerAdapter extends FragmentStatePagerAdapter {
        public TestPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                {
                    Fragment fragment = new Part1Fragment();
                    return fragment;
                }
                case 1:
                {
                    Fragment fragment = new Part2Fragment();
                    return fragment;
                }
                case 2:
                {
                    Fragment fragment = new Part3Fragment();
                    return fragment;
                }

                default:
                    throw new RuntimeException("Invalid count for pager adapter");
            }

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return "Part 1";
                case 1:
                    return "Part 2";
                case 2:
                    return "Part 3";
            }
            return "OBJECT " + (position + 1);
        }
    }
}
