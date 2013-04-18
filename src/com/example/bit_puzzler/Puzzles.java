package com.example.bit_puzzler;

import android.provider.BaseColumns;

public final class Puzzles{
	private Puzzles(){
	}
	public static final class Schema implements BaseColumns{
		private Schema(){
			
		}
		/**
         * The table name offered by this provider
         */
        public static final String TABLE_NAME = "puzzlesdata";

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "modified DESC";

        /*
         * Column definitions
         */

        /**
         * Column name for the name of the puzzle
         * <P>Type: TEXT</P>
         */
        public static final String COLUMN_NAME_PUZZLE_NAME = "name";

        /**
         * Column name of the puzzle description
         * <P>Type: TEXT</P>
         */
        public static final String COLUMN_NAME_DESCRIPTION = "description";

        /**
         * Column name for the input of the puzzle
         * <P>Type: TEXT</P>
         */
        public static final String COLUMN_NAME_INPUT = "input";

        /**
         * Column name for the output of the puzzle
         * <P>Type: TEXT</P>
         */
        public static final String COLUMN_NAME_OUTPUT = "output";
        
        /**
         * Column name for the current user input
         * <P>Type: TEXT</P>
         */
        public static final String COLUMN_NAME_PROGRAM = "program";
        
        /**
         * Column name indicating if the puzzle is solved
         * <P>Type: INTEGER</P>
         */
        public static final String COLUMN_NAME_SOLVED = "solved";
        
        /**
         * Column name for the shortest program that is correct
         * <P>Type: INTEGER</P>
         */
        public static final String COLUMN_NAME_HISCORE = "hiscore";
        
        
	}
}
