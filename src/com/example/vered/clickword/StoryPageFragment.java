package com.example.vered.clickword;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * Created by Vered on 11/15/2014.
 */
public class StoryPageFragment  extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_story_page, container, false);
        Bundle args = getArguments();
        ((TextView) rootView.findViewById(R.id.text)).setText(
                args.getString(ARG_OBJECT));
        return rootView;
    }
}

