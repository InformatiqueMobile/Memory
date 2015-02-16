package com.memory.memory;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.memory.memory.database.SQLiteHelper;
import com.memory.memory.model.Player;

import java.util.ArrayList;
import java.util.List;


public class ScoresActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);



        SQLiteHelper db = new SQLiteHelper(this);
        List<Player> players = db.getAllScores();
        ListView listPlayers = (ListView) findViewById(R.id.highscoresListView);
        players = sort(players);

        List<String> scores = new ArrayList<String>();
        for (int i = 0; i < players.size(); i++){
            scores.add((i+1) + "-\t" + players.get(i).getName() + " \t score = " + players.get(i).getScore());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scores);
        listPlayers.setAdapter(adapter);

    }

    private List sort(List players) {
        int j;
        List<Player> playerList = new ArrayList<Player>();
        if (players.size() > 10)
            j=10;
        else
            j= players.size();

        for (int i = 0 ; i < j; i++){
            Player player = null;
            for (int k = 0; k< players.size(); k++) {
                if (playerList.indexOf(players.get(k)) == -1) {
                    player = (Player) players.get(k);
                    break;
                }
            }
            for (int k = 1; k < players.size(); k++){
                if ((player.getScore()<((Player)players.get(k)).getScore())
                    && playerList.indexOf(((Player)players.get(k))) == -1){
                    player = (Player) players.get(k);
                }
            }
            playerList.add(player);
        }
        return playerList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scores, menu);
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
