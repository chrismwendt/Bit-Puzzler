package com.example.bit_puzzler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddScoreActivity extends Activity {
	public int score;
	public int level;
	protected Button addButton;
	protected Button cancelButton;
	protected EditText player;
	protected Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_score);
		Intent intent = getIntent();
		score = intent.getIntExtra("HSScore", 1);
		level = intent.getIntExtra("HSLevel", 1);

		TextView title = (TextView) findViewById(R.id.TextView01);
		title.setText("Submit Score Level " + level);
		TextView tv = (TextView) findViewById(R.id.ScoreText);
		tv.setText("Level: " + level + "    Score: " + score);
		player = (EditText) findViewById(R.id.player);
		addButton = (Button) findViewById(R.id.addButton);
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((Button) findViewById(R.id.addButton)).setEnabled(false);
				String player = AddScoreActivity.this.player.getText()
						.toString();
				HighScore newScore = new HighScore(player, score);
				new AddHighScoreTask().execute(newScore);
			}
		});
		cancelButton = (Button)findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});

		EditText e1 = (EditText)findViewById(R.id.player);
		e1.setTextColor(Color.WHITE);
	}

	private class AddHighScoreTask extends AsyncTask<HighScore, Void, Void> {

		protected Void doInBackground(HighScore... highScores) {
			HighscoresHelper hsh = new HighscoresHelper();
			hsh.addHighScore(highScores[0], level);

			return null;
		}

		protected void onPostExecute(Void result) {
			((Button) findViewById(R.id.addButton)).setEnabled(true);
			AddScoreActivity.this.finish();
			Intent intent = new Intent(context, HighScoresActivity.class);
			intent.putExtra(MainActivity.EXTRA_MESSAGE, level);
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_score, menu);
		return true;
	}

}
