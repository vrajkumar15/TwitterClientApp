package com.codepath.apps.mytweetapp;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


import com.codepath.apps.mytweetapp.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends Activity {
	String tweetStatus;

	private static final int REQUEST_CODE = 0;

@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}
	
public void onRefresh(MenuItem m1){
	//Toast.makeText(this, "settings clicked", Toast.LENGTH_SHORT).show();
	homeTimeline();
	
}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
	
		homeTimeline();
}
	
	public void homeTimeline(){
	MyTwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
		public void onSuccess(JSONArray jsonTweets) {
			ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
			ListView lvTweets = (ListView)findViewById(R.id.lvTweets);
			TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
			lvTweets.setAdapter(adapter);
			Log.d("DEBUG", jsonTweets.toString());
		}
	});
	}

	public void onCompose(MenuItem m){
		//Toast.makeText(this, "settings clicked", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, ComposeTweetActivity.class);
		//startActivity(i);
		startActivityForResult(i, REQUEST_CODE);	
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent i){
		//Toast.makeText(this, "back" , Toast.LENGTH_SHORT).show();
		  if (resultCode == 0){
			  if(requestCode == 0) {
				 tweetStatus = i.getStringExtra("posted_data");
				  
	    		  //AdvSearch data = (AdvSearch) getIntent().getSerializableExtra("criteria");
			     Toast.makeText(this, tweetStatus , Toast.LENGTH_SHORT).show();
			     

			  }
			  }
	}
	


}
