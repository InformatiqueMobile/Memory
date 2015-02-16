package com.memory.memory;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.memory.memory.database.ScoresDB;
import com.memory.memory.model.Player;


public class ResultActivity extends ActionBarActivity {

    private Player playerOne, playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        playerOne = getIntent().getExtras().getParcelable("playerOne");
        playerTwo = getIntent().getExtras().getParcelable("playerTwo");
        TextView winnerView = (TextView) findViewById(R.id.winnerTextView);
        if (playerOne.getScore() == playerTwo.getScore())
            winnerView.setText("DRAW");
        else if (playerOne.getScore() > playerTwo.getScore())
            winnerView.setText(playerOne.getName());
        else
            winnerView.setText(playerTwo.getName());

        /*ScoresDB scoresDB = new ScoresDB(this);
        scoresDB.open();

        scoresDB.insertScore(winner);
        scoresDB.insertScore(loser);

        scoresDB.close();*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
