package com.example.vered.clickword;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.support.v4.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * Created by Vered on 11/15/2014.
 */
public class StoryPageFragment  extends Fragment implements FragmentLifecycle{
    public static final String ARG_OBJECT = "object";
    private String appDataPath = Environment.getExternalStorageDirectory()+"/ClickWord";
    private MediaPlayer mediaPlayer;
    private int pageNum;
  
    public void init(int pageNum){
    	this.pageNum = pageNum;

    	
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    //    Bundle args = getArguments();
    //    pageNum =  args.getInt(ARG_OBJECT);
 
        Log.d("*****","on create:"+pageNum);

    }
    
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        

    	// The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_story_page, container, false);
     //   Bundle args = getArguments();
     //   pageNum =  args.getInt(ARG_OBJECT);
 
        Log.d("*****","on create view:"+pageNum);
   
        String pageText = getPageText(pageNum);
        ((TextView) rootView.findViewById(R.id.text)).setText(
        		pageText);

        return rootView;
    }

    public String getPageText(int i){
        File file = new File(appDataPath+"/1","page"+i+".txt");

        StringBuilder text = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {

                   text.append(line);
                    text.append('\n');
            }
        }
        catch (IOException e) {
            Log.d("error reading file",e.getMessage());
            //You'll need to add proper error handling here
        }
   

    	return text.toString();
    }
    
    @Override
    public void onResumeFragment() {

 //       Bundle args = getArguments();
 //       pageNum =  args.getInt(ARG_OBJECT);
    	Log.d("***", "onResumeFragment() "+pageNum);

    	mediaPlayer = new MediaPlayer();
   	    Log.d("*****","setDataSource:"+pageNum);

	       try {
	            mediaPlayer.setDataSource(appDataPath+"/1/page"+pageNum+".aac");
	        } catch (Exception e) {
	        	Log.d("Fail", "***failed*** on mediaPlayer setDataSource");

	        }

    	Log.d("*****","prepare and start:"+pageNum);
	       try {
	            mediaPlayer.prepare();
	            mediaPlayer.start();
	        } catch (Exception e) {
	        	Log.d("page", "failed on mediaPlayer.start()");
	            e.printStackTrace();
	        }
    }
    
    @Override
    public void onPauseFragment() {
  //      Bundle args = getArguments();
  //      pageNum =  args.getInt(ARG_OBJECT);
   	Log.d("***", "onPauseFragment()" +pageNum);
       	   
	   if (mediaPlayer!=null){
  		   mediaPlayer.stop(); 
 		   mediaPlayer.release();
 		   mediaPlayer = null;
 	   }
    }


    @Override
    public void onDestroy() {
 	   Log.d("*****","on destroy:"+pageNum);
  	   if (mediaPlayer!=null){
  		   mediaPlayer.stop(); 
 		   mediaPlayer.release();
 		   mediaPlayer = null;
 	   }

        super.onDestroy();
    }



}

