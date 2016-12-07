package com.viovie.example.landingslide;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    public static final int MAX_INSTANCE = 3;

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return MainFragment.newInstance(0, Color.RED);
            }
            case 1: {
                return MainFragment.newInstance(0, Color.YELLOW);
            }
            case 2: {
                return MainFragment.newInstance(0, Color.GREEN);
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return MAX_INSTANCE;
    }
}
