package com.example.bit_puzzler;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PuzzleHelper extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "puzzles.db";
	
	PuzzleHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
		db.execSQL("CREATE TABLE " + Puzzles.Schema.TABLE_NAME + " (" + Puzzles.Schema._ID + " INTEGER PRIMARY KEY,"
				+ Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_DESCRIPTION + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_INPUT + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_OUTPUT + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_PROGRAM + " TEXT,"
				+ Puzzles.Schema.COLUMN_NAME_SOLVED + " INTEGER,"
				+ Puzzles.Schema.COLUMN_NAME_HISCORE + " INTEGER);");
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int original, int updated) {
	}
}
