package com.example.bit_puzzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PuzzleActivity extends Activity implements OnClickListener {
	public String input;
	public String correctOutput;
	public int puzzleNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puzzle);
		
		//TextView name = (TextView)findViewById(R.id.puzzle_name);
		Intent intent = getIntent();
		puzzleNumber = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 1);
		
		TextView numberTextView = (TextView)findViewById(R.id.puzzle_number);
		TextView titleTextView = (TextView)findViewById(R.id.puzzle_title);
		TextView descriptionTextView = (TextView)findViewById(R.id.puzzle_description);
		TextView inputTextView = (TextView)findViewById(R.id.input);
		TextView correctOutputTextView = (TextView)findViewById(R.id.correct_output);
		Button runButton = (Button)findViewById(R.id.button_run_program);
		runButton.setOnClickListener(this);
		
		PuzzleHelper fetch = new PuzzleHelper(this);
		SQLiteDatabase reader = fetch.getReadableDatabase();
		Cursor data = reader.query(Puzzles.Schema.TABLE_NAME, null, "_ID='" + puzzleNumber + "'", null, null, null, null);
		data.moveToNext();
		if(data.moveToLast()==false){
			numberTextView.setText(String.valueOf(puzzleNumber));
			titleTextView.setText("This puzzle isn't finished yet");
			descriptionTextView.setText("An unfinished puzzle");
			inputTextView.setText("" + " <-- input");
			correctOutputTextView.setText("" + " <-- correct output");
			input = "";
			correctOutput = "";
		}
		else{
			String number = String.valueOf(puzzleNumber);
			String title = data.getString(data.getColumnIndex(Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME));
			String description = data.getString(data.getColumnIndex(Puzzles.Schema.COLUMN_NAME_DESCRIPTION));
			input = data.getString(data.getColumnIndex(Puzzles.Schema.COLUMN_NAME_INPUT));
			correctOutput = data.getString(data.getColumnIndex(Puzzles.Schema.COLUMN_NAME_OUTPUT));
			numberTextView.setText(number);
			titleTextView.setText(title);
			descriptionTextView.setText(description);
			inputTextView.setText(input + " <-- input");
			correctOutputTextView.setText(correctOutput + " <-- correct output");
		}
		
		TextView t1 = (TextView)findViewById(R.id.puzzle_number);
		TextView t2 = (TextView)findViewById(R.id.puzzle_title);
		TextView t3 = (TextView)findViewById(R.id.puzzle_description);
		TextView t4 = (TextView)findViewById(R.id.input);
		TextView t5 = (TextView)findViewById(R.id.correct_output);
		TextView t6 = (TextView)findViewById(R.id.player_output);
		t1.setTextColor(Color.WHITE);
		t2.setTextColor(Color.WHITE);
		t3.setTextColor(Color.WHITE);
		t4.setTextColor(Color.WHITE);
		t5.setTextColor(Color.WHITE);
		t6.setTextColor(Color.WHITE);
		EditText e1 = (EditText)findViewById(R.id.edit_program);
		e1.setTextColor(Color.WHITE);
		Button b1 = (Button)findViewById(R.id.button_run_program);
		b1.setTextColor(Color.WHITE);
		b1.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.puzzle, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO handle a click
		EditText programEditText = (EditText)findViewById(R.id.edit_program);
		TextView playerOutputTextView = (TextView)findViewById(R.id.player_output);
		
		String program = programEditText.getText().toString();
		String playerOutput = run(program);
		playerOutputTextView.setText(playerOutput + " <-- your output");
		
		if (playerOutput.equals(correctOutput)) {
			makeCompleteDialog().show();
		} else {
			if (playerOutput.equals("")) {
				playerOutputTextView.setText("Error <-- your output");
			} else {
				playerOutputTextView.setText(playerOutput + " <-- your output");
			}
		}
		
		// if the output is correct, mark it as completed in the database, make Puzzle Selection and Next buttons
	}

	private String run(String program) {
    	int maxInstructions = 1000;
    	Fiddler fiddler = new Fiddler(program, input);
    	boolean success = fiddler.execute(maxInstructions);
    	if (!success) {
    		return "";
    	} else {
    		return fiddler.toString().substring(0, correctOutput.length());
    	}
	}
	
	private AlertDialog makeCompleteDialog() {
		final Context context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Puzzle complete! Go back to the selection page, submit you score, or go on to the next puzzle.")
               .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(context, PuzzleActivity.class);
						intent.putExtra(MainActivity.EXTRA_MESSAGE, puzzleNumber+1);
						startActivity(intent);
                   }
               })
               
               .setNeutralButton("Submit Highscore", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(context, AddScoreActivity.class);
						intent.putExtra("HSScore", input.length());
						intent.putExtra("HSLevel", puzzleNumber);
						startActivity(intent);
                   }
               })
               .setNegativeButton("Other Puzzle", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(context, PuzzSelection.class);
						intent.putExtra(MainActivity.ACTIVITY_TYPE, "true");
						startActivity(intent);
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
	}
}
