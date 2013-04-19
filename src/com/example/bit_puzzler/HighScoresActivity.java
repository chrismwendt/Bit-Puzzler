package com.example.bit_puzzler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class HighScoresActivity extends Activity {
	public int puzzleNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
		Intent intent = getIntent();
		puzzleNumber = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 1);
		TextView tv = (TextView)findViewById(R.id.title);
		tv.setText("Highscores Level " + puzzleNumber);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_scores, menu);
		return true;
	}

}
