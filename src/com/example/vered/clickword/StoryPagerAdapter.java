package com.example.vered.clickword;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vered on 11/15/2014.
 */
public class StoryPagerAdapter extends FragmentStatePagerAdapter {

	private Map<Integer,Fragment> myFragments = new HashMap<Integer,Fragment>();
	private int currentPosition = -1;

        public StoryPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int newPosition) {
    		Log.d("Adapter","getItem:"+newPosition);

    		Fragment fragment = myFragments.get(newPosition);
    		if (fragment == null){
        		Log.d("Adapter","!!! Creating new Item:"+newPosition);
	    		fragment = new StoryPageFragment();
	          //  Bundle args = new Bundle();
	          //  args.putInt(StoryPageFragment.ARG_OBJECT, newPosition+1);
	         //   fragment.setArguments(args);
	            myFragments.put(newPosition, fragment);
	            ((StoryPageFragment)fragment).init(newPosition+1);
    		}
/*    		if (currentPosition!=-1){
	    		Fragment fragmentToHide =  myFragments.get(currentPosition);
	    		if (fragmentToHide != null){
	    			myFragments.remove(currentPosition);
	    		}
    		}*/
    		currentPosition = newPosition;
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return "OBJECT " + (i + 1);
        }
    }
