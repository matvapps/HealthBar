package com.gethealthbar.healthbar.tutorial;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gethealthbar.healthbar.R;

/**
 * Created by Alexandr.
 */
public class TutorialPageAdapter extends FragmentStatePagerAdapter {

    public static int PAGE_COUNT = 3;

    public TutorialPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                TutorialItemFragment fragment = new TutorialItemFragment();
                fragment.setImageID(R.drawable.tut_image_1);
                fragment.setText("Unwrap the bar.\nScan the code");
                return fragment;
            }
            case 1: {
                TutorialItemFragment fragment = new TutorialItemFragment();
                fragment.setImageID(R.drawable.tut_image_2);
                fragment.setText("Every time you eat\nHealthBar, your Virtual Hero\nbecomes stronger");
                return fragment;
            }
            case 2: {
                TutorialItemFragment fragment = new TutorialItemFragment();
                fragment.setImageID(R.drawable.tut_image_3);
                fragment.setText("Get your hero stronger and\nget amazing gifts");
                return fragment;
            }

        }

        return new TutorialItemFragment();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}
