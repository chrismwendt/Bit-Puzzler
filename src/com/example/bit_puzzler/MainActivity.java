package com.example.bit_puzzler;
import android.app.Activity;
import android.content.Intent;
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
