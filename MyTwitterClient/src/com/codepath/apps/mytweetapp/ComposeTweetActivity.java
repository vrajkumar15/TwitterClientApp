package com.codepath.apps.mytweetapp;

import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComposeTweetActivity extends Activity {
	EditText compT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose_tweet);
	}



	
	public void postTweet(View v){
		compT = (EditText) findViewById(R.id.etCompose);

		String composedTweet = compT.getText().toString();
		
	 	MyTwitterApp.getRestClient().postTweetTimeline(composedTweet, new AsyncHttpResponseHandler(){
			public void onSuccess(String response) {
				 System.out.println(response);
			}
		});
	 	
		//Toast.makeText(this, composedTweet, Toast.LENGTH_SHORT).show();
        Intent i = new Intent();
        i.putExtra("posted_data", "Tweet posted successfully" );
        setResult(RESULT_OK, i);
        finish();
	}


}
