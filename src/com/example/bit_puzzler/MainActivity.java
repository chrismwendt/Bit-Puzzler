package com.example.bit_puzzler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "puzzle_number";
	public final static String ACTIVITY_TYPE = "is_puzzselect";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = (Button)findViewById(R.id.btnStart);
        Button btnScores = (Button)findViewById(R.id.btnScores);
        Button txtHeader = (Button)findViewById(R.id.txtHeader);
        txtHeader.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        btnStart.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        btnScores.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        Button btnReset = (Button)findViewById(R.id.btnReset);
        btnReset.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        File file = getFileStreamPath("puzzles.db");
        if(!file.exists()){
        	try { 
        		FileOutputStream fOut = openFileOutput("puzzles.db", MODE_PRIVATE);
        		fOut.close();
        	} catch (IOException ioe) 
        	{
        		
        	}	
        }
        PuzzleHelper fetch = new PuzzleHelper(this);
        SQLiteDatabase db = fetch.getWritableDatabase();
        //db.execSQL("DROP TABLE IF EXISTS '"+Puzzles.Schema.TABLE_NAME +"'");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Puzzles.Schema.TABLE_NAME + " (" + Puzzles.Schema._ID + " INTEGER PRIMARY KEY,"
				+ Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_DESCRIPTION + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_INPUT + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_OUTPUT + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_PROGRAM + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_SOLVED + " INTEGER,"
				+ Puzzles.Schema.COLUMN_NAME_HISCORE + " INTEGER);");
        fetch.add(1, "Getting Started", "Flip the input bit using '*'.", "0", "1", (String) null, false, -1);
        fetch.add(2, "Moving Around", "Move the memory pointer to the left and right using '<' & '>'.", "00", "01", (String) null, false, -1);
        fetch.add(3, "An Advanced Puzzle", "Combined moving and flipping.", "1011011", "1101011", (String) null, false, -1);
        fetch.add(4, "Going around the block", "The '[' command will continue perform instructions between itself & ']' until it arrives at a 0 after the instructions.", "11110", "00000", (String) null, false, -1);
        fetch.add(5, "Nested Loops", "Use nested loops to solve this puzzle.", "1110111011100", "0001000100010", (String) null, false, -1);
        fetch.add(6, "Alternating", "Bits alternate between 0 and 1.", "010101010101011", "000000000000001", (String) null, false, -1);
        fetch.add(7, "Nested Loops", "Use nested loops to solve this puzzle.", "1110111011100", "0001000100010", (String) null, false, -1);
        fetch.add(8, "Nested Loops", "Use nested loops to solve this puzzle.", "1110111011100", "0001000100010", (String) null, false, -1);
        fetch.add(9, "Random", "Use nested loops to solve this puzzle.", "10111100", "00000000", (String) null, false, -1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    public void sendMessage(View view){
    	Intent intent = new Intent(this, PuzzleActivity.class);
    	startActivity(intent);
    }
    public void openPuzzSelect(View view){
    	openSelectActivity(view,true);
    }
    public void openScoresSelect(View view){
    	openSelectActivity(view,false);
    }
    public void resetPuzzles(View view){
    	PuzzleHelper h = new PuzzleHelper(this);
    	for (int i = 1; i <= 9; i++) {
    		h.saveSolved(i, false);
    	}
    }
    private void openSelectActivity(View view, boolean puzzselect){
    	Intent intent = new Intent(this, PuzzSelection.class);
    	String arg = "true";
    	if (!puzzselect) arg = "false";
    	intent.putExtra(ACTIVITY_TYPE, arg);
    	startActivity(intent);	
    }
}