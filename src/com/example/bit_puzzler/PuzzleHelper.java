package com.example.bit_puzzler;

import java.sql.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class PuzzleHelper extends SQLiteOpenHelper{
	private static int version = 1;
	private static final String DATABASE_NAME = "puzzles.db";
	
	PuzzleHelper(Context context){
		super(context, DATABASE_NAME, null, version);
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
	public Object get(int puzzle_number, String column_name){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(Puzzles.Schema.TABLE_NAME, null, "_ID='"+puzzle_number+"'", null, null, null, null);
		cursor.moveToNext();
		if(column_name.equals(Puzzles.Schema.COLUMN_NAME_HISCORE)||column_name.equals(Puzzles.Schema.COLUMN_NAME_SOLVED))
			return cursor.getInt(cursor.getColumnIndex(column_name));
		else
			return cursor.getString(cursor.getColumnIndex(column_name));
	}
	public void add(int id, String puzzname, String description, String input, String output, String program, boolean solved, int hiscore){
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
	public void saveProgram(int level, String program){
		SQLiteDatabase db = this.getWritableDatabase();
		String strFilter = String.valueOf(level);
		ContentValues args = new ContentValues(8);
		args.put(Puzzles.Schema.COLUMN_NAME_PROGRAM, program);
		db.update(Puzzles.Schema.TABLE_NAME, args, strFilter, null);
		
	}
	public void saveSolved(int level, boolean solved){
		SQLiteDatabase db = this.getWritableDatabase();
		String strFilter = String.valueOf(solved);
		ContentValues args = new ContentValues(8);
		args.put(Puzzles.Schema.COLUMN_NAME_SOLVED, solved);
		db.update(Puzzles.Schema.TABLE_NAME, args, strFilter, null);
		
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
		if(updated >= 2 && original < 2){
			ContentValues values = new ContentValues(8);
	    	values.put(Puzzles.Schema._ID, 2);
	    	values.put(Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME, "Moving Around");
	    	values.put(Puzzles.Schema.COLUMN_NAME_DESCRIPTION, "Move the memory pointer to the left and flip the bit using '>' & '*'");
	    	values.put(Puzzles.Schema.COLUMN_NAME_INPUT, "00");
	    	values.put(Puzzles.Schema.COLUMN_NAME_OUTPUT, "01");
	    	values.put(Puzzles.Schema.COLUMN_NAME_PROGRAM, (String) null);
	    	values.put(Puzzles.Schema.COLUMN_NAME_SOLVED, false);
	    	values.put(Puzzles.Schema.COLUMN_NAME_HISCORE, -1);
	    	try{
	    		db.insert(Puzzles.Schema.TABLE_NAME, null, values);
	    	}
	    	catch(SQLiteException e){
	    		;
	    	}
		}
		if(updated >= 3&& original < 3){
			ContentValues values = new ContentValues(8);
	    	values.put(Puzzles.Schema._ID, 3);
	    	values.put(Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME, "Going around the block");
	    	values.put(Puzzles.Schema.COLUMN_NAME_DESCRIPTION, "The '[' command will continue all instructions between it & ']' until it arrives at a 0 after the instructions");
	    	values.put(Puzzles.Schema.COLUMN_NAME_INPUT, "11110");
	    	values.put(Puzzles.Schema.COLUMN_NAME_OUTPUT, "00000");
	    	values.put(Puzzles.Schema.COLUMN_NAME_PROGRAM, (String) null);
	    	values.put(Puzzles.Schema.COLUMN_NAME_SOLVED, false);
	    	values.put(Puzzles.Schema.COLUMN_NAME_HISCORE, -1);
	    	try{
	    		db.insert(Puzzles.Schema.TABLE_NAME, null, values);
	    	}
	    	catch(SQLiteException e){
	    		;
	    	}
		}
		if(updated >= 4&& original < 4){
			ContentValues values = new ContentValues(8);
	    	values.put(Puzzles.Schema._ID, 4);
	    	values.put(Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME, "An Advanced Puzzle");
	    	values.put(Puzzles.Schema.COLUMN_NAME_DESCRIPTION, "Use everything you've learned here");
	    	values.put(Puzzles.Schema.COLUMN_NAME_INPUT, "1011011");
	    	values.put(Puzzles.Schema.COLUMN_NAME_OUTPUT, "1101011");
	    	values.put(Puzzles.Schema.COLUMN_NAME_PROGRAM, (String) null);
	    	values.put(Puzzles.Schema.COLUMN_NAME_SOLVED, false);
	    	values.put(Puzzles.Schema.COLUMN_NAME_HISCORE, -1);
	    	try{
	    		db.insert(Puzzles.Schema.TABLE_NAME, null, values);
	    	}
	    	catch(SQLiteException e){
	    		;
	    	}
		}
		//Find last ID and add all new ids
	}
	@Override
	public void onDowngrade(SQLiteDatabase db, int original, int downgrade){
		System.err.println("DROP");
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
}
