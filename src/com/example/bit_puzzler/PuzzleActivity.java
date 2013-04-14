package com.example.bit_puzzler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class PuzzleActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puzzle);

		Intent intent = getIntent();
		
		TextView numberTextView = (TextView)findViewById(R.id.puzzle_number);
		TextView titleTextView = (TextView)findViewById(R.id.puzzle_title);
		TextView descriptionTextView = (TextView)findViewById(R.id.puzzle_description);
		TextView inputTextView = (TextView)findViewById(R.id.input);
		TextView outputTextView = (TextView)findViewById(R.id.output);
		
		String number = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		String title = "Title of puzzle goes here";
		String description = "Description of puzzle goes here";
		String input = "1011011";
		String output = "1011100";
		
		numberTextView.setText(number);
		titleTextView.setText(title);
		descriptionTextView.setText(description);
		inputTextView.setText(input);
		outputTextView.setText(output);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.puzzle, menu);
		return true;
	}
}
