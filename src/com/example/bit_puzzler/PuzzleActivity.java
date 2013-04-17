package com.example.bit_puzzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PuzzleActivity extends Activity implements OnClickListener {
	public String correctOutput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puzzle);
		
		//TextView name = (TextView)findViewById(R.id.puzzle_name);
		Intent intent = getIntent();
		//name.setText(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));
		
		TextView numberTextView = (TextView)findViewById(R.id.puzzle_number);
		TextView titleTextView = (TextView)findViewById(R.id.puzzle_title);
		TextView descriptionTextView = (TextView)findViewById(R.id.puzzle_description);
		TextView inputTextView = (TextView)findViewById(R.id.input);
		TextView correctOutputTextView = (TextView)findViewById(R.id.correct_output);
		Button runButton = (Button)findViewById(R.id.button_run_program);
		runButton.setOnClickListener(this);
		
		// TODO get these from the database
		String number = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		String title = "Title of puzzle goes here";
		String description = "Description of puzzle goes here";
		String input = "1011011";
		correctOutput = "1101011";
		
		numberTextView.setText(number);
		titleTextView.setText(title);
		descriptionTextView.setText(description);
		inputTextView.setText(input + " <-- input");
		correctOutputTextView.setText(correctOutput + " <-- correct output");
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
			playerOutputTextView.setText("Incorrect");
		}
		
		// run the program
		// if an error occurred, display it
		// if the output is correct, mark it as completed in the database, make Puzzle Selection and Next buttons
	}

	private String run(String program) {
		return "1101011";
	}
	
	private AlertDialog makeCompleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Puzzle complete! Go to the next one or back to the selection page.")
               .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(null, PuzzSelection.class);
						startActivity(intent);
                   }
               })
               .setNegativeButton("Other Puzzle", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
	}
}
