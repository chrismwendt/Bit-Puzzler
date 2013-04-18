package com.example.bit_puzzler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PuzzleHelper extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "puzzles.db";
	private static Connection con;
	
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
		int isolved = 0;
		if (solved) isolved++;
		try{
		PreparedStatement prep = con.prepareStatement("insert into " + Puzzles.Schema.TABLE_NAME
				    + " values("+ id+","+ puzzname+"," + description + ","+ input +","+ output +","+ program +","+ isolved +","+ hiscore + ");");

		prep.setInt(1,id);
		prep.setString(2, puzzname);
		prep.setString(3, description);
		prep.setString(4, input);
		prep.setString(5, output);
		prep.setString(6, program);
		prep.setInt(7, isolved);
		prep.setInt(8, hiscore);
		prep.execute();
		prep.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void printall(){
		  Statement stat = null;

		  try {

		   stat = con.createStatement();

		   ResultSet res = stat.executeQuery("select * from "+Puzzles.Schema.TABLE_NAME);

		   while (res.next()) {

		    System.out.println(res.getString(Puzzles.Schema._ID) + " "
		      + res.getString(Puzzles.Schema.COLUMN_NAME_PUZZLE_NAME));

		   }

		  } catch (Exception e) {

		   System.out.println("ERROR: failed\n"
		     + e.getMessage());

		  } finally {
		   try {
		    if (stat != null)
		     stat.close();
		   } catch (Exception ex) {
		   }
		  }
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int original, int updated) {
	}
	public static void main(String[] args){
		PuzzleHelper ph = new PuzzleHelper(null);
		ph.add(1, "Puzz", "None", "1010", "1001", "Prog", true, 21);
		ph.printall();
	}
}
