package com.example.bit_puzzler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class PuzzleActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TextView name = (TextView)findViewById(R.id.puzzle_name);
		Intent intent = getIntent();
		name.setText(intent.getStringExtra("puzzle_number"));
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puzzle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.puzzle, menu);
		return true;
	}
}
