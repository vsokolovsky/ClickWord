package com.example.vered.clickword;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Vered on 11/15/2014.
 */
public class StoryPagerAdapter extends FragmentStatePagerAdapter {
        private List<String> mPagesText;

        public StoryPagerAdapter(FragmentManager fm,List<String> pagesText) {
            super(fm);
            mPagesText = pagesText;

        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new StoryPageFragment();
            Bundle args = new Bundle();
            args.putString(StoryPageFragment.ARG_OBJECT, mPagesText.get(i));
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return mPagesText.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }
