package com.example.bit_puzzler;

import java.io.Serializable;

public class HighScore implements Serializable {	
	private static final long serialVersionUID = 1L;

	private final String player;
	private final int score;
	
	public HighScore( final String player, final int score ) {
		this.player = player;
		this.score = score;
	}
	
	public String getPlayer() {
		return this.player;
	}
	
	public int getScore() {
		return this.score;
	}
}