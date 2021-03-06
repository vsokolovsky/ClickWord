package com.example.vered.clickword;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity {
    private StoryPagerAdapter mStoryPagerAdapter;
    private ViewPager mViewPager;
    private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

    private int currentPosition=-1;
    	@Override
    	public void onPageSelected(int newPosition) {
    		Log.d("ChangeListener","onPageSelected:"+newPosition);
    		FragmentLifecycle fragmentToShow = (FragmentLifecycle)mStoryPagerAdapter.getItem(newPosition);
    		fragmentToShow.onResumeFragment();
    		if (currentPosition!=-1){
	    		FragmentLifecycle fragmentToHide = (FragmentLifecycle)mStoryPagerAdapter.getItem(currentPosition);
	    		fragmentToHide.onPauseFragment();
    		}
    		currentPosition = newPosition;
    	}

    	@Override
    	public void onPageScrolled(int arg0, float arg1, int arg2) { }

    	public void onPageScrollStateChanged(int arg0) { }
    };
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("a","on create main activity");
        setContentView(R.layout.activity_main);

//        List<String> pagesText = parsePagesDataFromFile();
        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mStoryPagerAdapter =
                new StoryPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mStoryPagerAdapter);
        mViewPager.setOnPageChangeListener(pageChangeListener);
        pageChangeListener.onPageSelected(0);
    }


/*
       private List<String> parsePagesDataFromFile(){

        List<String> pagesData = new ArrayList<String>();
        File file = new File(appDataPath,"zyplenokResultUTF8.txt");

        StringBuilder text = null;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            boolean sectionEnd=false;
            while ((line = br.readLine()) != null) {
                if ((line.trim().length() <= 0) && sectionEnd) {
                    if (text != null) {
                        pagesData.add(text.toString());
                        text = null;
                    }
                    sectionEnd = false;
                } else {
                    if (line.trim().length() <= 0) {
                        sectionEnd = true;
                    } else {
                        sectionEnd = false;
                    }
                    if (text == null) {
                        text = new StringBuilder();
                    }
                    text.append(line);
                    text.append('\n');
                }

            }
            if (text != null) {
                pagesData.add(text.toString());

            }

        }
        catch (IOException e) {
            Log.d("a",e.getMessage());
            //You'll need to add proper error handling here
        }
        return pagesData;
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


/*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("t","onCreate()");
        setContentView(R.layout.activity_main);
        init();

    }



    private void init() {
        String definition = getTextFromFile();
        TextView definitionView = (TextView) findViewById(R.id.text);
        definitionView.setMovementMethod(LinkMovementMethod.getInstance());
        definitionView.setText(definition, TextView.BufferType.SPANNABLE);

        Spannable spans = (Spannable) definitionView.getText();
        BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
        iterator.setText(definition);
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator
                .next()) {
            String possibleWord = definition.substring(start, end);
            if (Character.isLetterOrDigit(possibleWord.charAt(0))) {
                ClickableSpan clickSpan = getClickableSpan(possibleWord, start, end);
                spans.setSpan(clickSpan, start, end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }


    }


    private ClickableSpan getClickableSpan(final String word,final int start,final int end) {
        return new ClickableSpan() {
            final String mWord;
            final int mStart, mEnd;
            {
                mStart = start;
                mEnd = end;
                mWord = word;
            }

            @Override
            public void onClick(View widget) {
                Log.d("tapped on:", mWord);
                Toast.makeText(widget.getContext(), mWord+" "+mStart+":"+mEnd, Toast.LENGTH_SHORT)
                        .show();


            }

           public void updateDrawState(TextPaint ds) {
          //       super.updateDrawState(ds);
            }
        };
    }

    */
}
