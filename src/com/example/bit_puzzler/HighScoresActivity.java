package com.example.bit_puzzler;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class HighScoresActivity extends Activity {
	public int puzzleNumber;
	String[][] scores;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
		Intent intent = getIntent();
		puzzleNumber = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 1);
		TextView tv = (TextView)findViewById(R.id.HSTitle);
		tv.setText("Highscores Level " + puzzleNumber);

		new GetHighScoresTask().execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_scores, menu);
		return true;
	}
	private class GetHighScoresTask extends AsyncTask<Void, Void, Boolean> {

		protected Boolean doInBackground(Void... voids) {

			boolean shouldRefresh = false;

			HighscoresHelper hs = new HighscoresHelper();
			scores = hs.getHighScores(puzzleNumber);
			shouldRefresh = true;
			return shouldRefresh;
		}
		@Override
		protected void onPostExecute(Boolean shouldRefresh) {
			if (shouldRefresh) {
				TextView tv1 = (TextView)findViewById(R.id.TextView01);
				tv1.setText(scores[0][0]+"    " +scores[1][0]);
				TextView tv2 = (TextView)findViewById(R.id.TextView02);
				tv2.setText(scores[0][1]+"    " +scores[1][1]);
				TextView tv3 = (TextView)findViewById(R.id.TextView03);
				tv3.setText(scores[0][2]+"    " +scores[1][2]);
				TextView tv4 = (TextView)findViewById(R.id.TextView04);
				tv4.setText(scores[0][3]+"    " +scores[1][3]);
				TextView tv5 = (TextView)findViewById(R.id.TextView05);
				tv5.setText(scores[0][4]+"    " +scores[1][4]);
				TextView tv6 = (TextView)findViewById(R.id.TextView06);
				tv6.setText(scores[0][5]+"    " +scores[1][5]);
				TextView tv7 = (TextView)findViewById(R.id.TextView07);
				tv7.setText(scores[0][6]+"    " +scores[1][6]);
				TextView tv8 = (TextView)findViewById(R.id.TextView08);
				tv8.setText(scores[0][7]+"    " +scores[1][7]);
				TextView tv9 = (TextView)findViewById(R.id.TextView09);
				tv9.setText(scores[0][8]+"    " +scores[1][8]);
				TextView tv10 = (TextView)findViewById(R.id.TextView10);
				tv10.setText(scores[0][9]+"    " +scores[1][9]);	
			}
		}
	}
}
