package com.example.bit_puzzler;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PuzzSelection extends Activity {
	private boolean puzzselect;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puzz_selection);
		String bool = getIntent().getStringExtra(MainActivity.ACTIVITY_TYPE);
		if (bool.equals("true"))
			puzzselect=true;
		else
			puzzselect=false;
		
		PuzzleHelper db = new PuzzleHelper(this);
		
		Button b1 = (Button)findViewById(R.id.b1);
		b1.setText((String)db.get(1, Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME));
		setColor(b1,1);
		Button b2 = (Button)findViewById(R.id.b2);
		b2.setText((String)db.get(2, Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME));
		setColor(b2,2);
		Button b3 = (Button)findViewById(R.id.b3);
		b3.setText((String)db.get(3, Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME));
		setColor(b3,3);
		Button b4 = (Button)findViewById(R.id.b4);
		b4.setText((String)db.get(4, Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME));
		setColor(b4,4);
		Button b5 = (Button)findViewById(R.id.b5);
		b5.setText((String)db.get(5, Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME));
		setColor(b5,5);
		Button b6 = (Button)findViewById(R.id.b6);
		b6.setText((String)db.get(6, Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME));
		setColor(b6,6);
		Button b7 = (Button)findViewById(R.id.b7);
		b7.setText((String)db.get(7, Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME));
		setColor(b7,7);
		Button b8 = (Button)findViewById(R.id.b8);
		b8.setText((String)db.get(8, Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME));
		setColor(b8,8);
		Button b9 = (Button)findViewById(R.id.b9);
		b9.setText((String)db.get(9, Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME));
		setColor(b9,9);
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	private void setColor(Button btn, int i){
		btn.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
		if (!puzzselect){
			btn.setTextColor(Color.WHITE);
			return;
		}
		
		int res = puzzStatus(i);
		if (res==2){
			btn.setTextColor(Color.WHITE);
		}else if (res==3){
			btn.setTextColor(Color.RED);
		}else if (res==1){
			btn.setTextColor(Color.YELLOW);
		}else if (res==0){
			btn.setTextColor(Color.GREEN);
		}
	}
	public void b1Click(View v){puzzSelect(1);}
	public void b2Click(View v){puzzSelect(2);}
	public void b3Click(View v){puzzSelect(3);}
	public void b4Click(View v){puzzSelect(4);}
	public void b5Click(View v){puzzSelect(5);}
	public void b6Click(View v){puzzSelect(6);}
	public void b7Click(View v){puzzSelect(7);}
	public void b8Click(View v){puzzSelect(8);}
	public void b9Click(View v){puzzSelect(9);}
	public void puzzSelect(int i){
		Intent intent = null;
		if (puzzselect){
			intent = new Intent(this, PuzzleActivity.class);
		}else{
			intent = new Intent(this, HighScoresActivity.class);
		}
		intent.putExtra(MainActivity.EXTRA_MESSAGE, i);
		startActivity(intent);
	}
	//Returns 0 if solved, 1 if in progress, 2 if unlocked, 3 if locked
	private int puzzStatus(int i){
		return (i*11)%3;
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.puzz_selection, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
