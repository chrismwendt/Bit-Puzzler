package com.example.bit_puzzler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
        File file = getFileStreamPath("puzzles.db");
        int maker = 0;
        if(!file.exists()){
        	maker = 1;
        	try { 
        		FileOutputStream fOut = openFileOutput("puzzles.db", MODE_PRIVATE);
        		fOut.close();
        	} catch (IOException ioe) 
        	{
        		
        	}	
        }
        if(maker==1){
        	PuzzleHelper fetch = new PuzzleHelper(this);
        	SQLiteDatabase db = fetch.getWritableDatabase();
        	fetch.add(1, "Placeholder", "Flip the input bit using '*'", "0", "1", (String) null, false, -1);
        }
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
    private void openSelectActivity(View view, boolean puzzselect){
    	Intent intent = new Intent(this, PuzzSelection.class);
    	String arg = "true";
    	if (!puzzselect) arg = "false";
    	System.out.println(arg);
    	intent.putExtra(ACTIVITY_TYPE, arg);
    	startActivity(intent);	
    }
}