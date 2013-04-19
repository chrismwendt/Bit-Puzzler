package com.example.bit_puzzler;

import java.sql.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PuzzleHelper extends SQLiteOpenHelper{
	private static int version = 2;
	private static final String DATABASE_NAME = "puzzles.db";
	private static Connection con;
	
	PuzzleHelper(Context context){
		super(context, DATABASE_NAME, null, version);
		/* Must be called before invoking a helper
		 * 
		 * File file = context.getFileStreamPath(DATABASE_NAME);
		if(!file.exists()){
			try { 
       // catches IOException below
       FileOutputStream fOut = openFileOutput("puzzles.db", context.MODE_PRIVATE);
           } catch (IOException ioe) 
      {ioe.printStackTrace();}
		}
		*/
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL("CREATE TABLE IF NOT EXISTS " + Puzzles.Schema.TABLE_NAME + " (" + Puzzles.Schema._ID + " INTEGER PRIMARY KEY,"
				+ Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_DESCRIPTION + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_INPUT + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_OUTPUT + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_PROGRAM + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_SOLVED + " INTEGER,"
				+ Puzzles.Schema.COLUMN_NAME_HISCORE + " INTEGER);");
		
	}
	public void add(int id, String puzzname, String description, String input, String output, String program, boolean solved, int hiscore){
		if(program == null)
			version++;
		int isolved = 0;
		if (solved) isolved++;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues(8);
    	values.put(Puzzles.Schema._ID, id);
    	values.put(Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME, puzzname);
    	values.put(Puzzles.Schema.COLUMN_NAME_DESCRIPTION, description);
    	values.put(Puzzles.Schema.COLUMN_NAME_INPUT, input);
    	values.put(Puzzles.Schema.COLUMN_NAME_OUTPUT, output);
    	values.put(Puzzles.Schema.COLUMN_NAME_PROGRAM, program);
    	values.put(Puzzles.Schema.COLUMN_NAME_SOLVED, isolved);
    	values.put(Puzzles.Schema.COLUMN_NAME_HISCORE, hiscore);
    	
    	db.insert(Puzzles.Schema.TABLE_NAME, null, values);
	}
	public void printall(){
		Cursor data = this.getReadableDatabase().query(Puzzles.Schema.TABLE_NAME, null, null, null, null, null, null);
		data.moveToNext();
		while(!data.isLast()){
			System.out.println(data.getInt(data.getColumnIndex(Puzzles.Schema._ID))+ ": " + data.getString(data.getColumnIndex(Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME)));
		}
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int original, int updated) {
		if(updated == 2){
			ContentValues values = new ContentValues(8);
	    	values.put(Puzzles.Schema._ID, 2);
	    	values.put(Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME, "Moving Around");
	    	values.put(Puzzles.Schema.COLUMN_NAME_DESCRIPTION, "Move the memory pointer to the left and flip the bit using '>' & '*'");
	    	values.put(Puzzles.Schema.COLUMN_NAME_INPUT, "00");
	    	values.put(Puzzles.Schema.COLUMN_NAME_OUTPUT, "01");
	    	values.put(Puzzles.Schema.COLUMN_NAME_PROGRAM, (String) null);
	    	values.put(Puzzles.Schema.COLUMN_NAME_SOLVED, false);
	    	values.put(Puzzles.Schema.COLUMN_NAME_HISCORE, -1);
	    	db.insert(Puzzles.Schema.TABLE_NAME, null, values);
		}
		//Find last ID and add all new ids
	}
	@Override
	public void onDowngrade(SQLiteDatabase db, int original, int downgrade){
		db.delete(Puzzles.Schema.TABLE_NAME, null, null);
		ContentValues values = new ContentValues(8);
    	values.put(Puzzles.Schema._ID, 1);
    	values.put(Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME, "Getting Started");
    	values.put(Puzzles.Schema.COLUMN_NAME_DESCRIPTION, "Flip the input bit using '*'");
    	values.put(Puzzles.Schema.COLUMN_NAME_INPUT, "0");
    	values.put(Puzzles.Schema.COLUMN_NAME_OUTPUT, "1");
    	values.put(Puzzles.Schema.COLUMN_NAME_PROGRAM, (String) null);
    	values.put(Puzzles.Schema.COLUMN_NAME_SOLVED, false);
    	values.put(Puzzles.Schema.COLUMN_NAME_HISCORE, -1);
    	db.insert(Puzzles.Schema.TABLE_NAME, null, values);
	}
	public static void main(String[] args){
		PuzzleHelper ph = new PuzzleHelper(null);
		ph.add(1, "Puzz", "None", "1010", "1001", "Prog", true, 21);
		ph.printall();
	}
}
