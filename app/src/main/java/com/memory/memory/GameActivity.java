package com.memory.memory;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.memory.memory.model.Card;
import com.memory.memory.model.Grid;
import com.memory.memory.model.ImageAdapter;
import com.memory.memory.model.Player;
import com.memory.memory.model.Turn;

import java.sql.Time;
import java.util.Timer;
import java.util.Vector;


public class GameActivity extends ActionBarActivity {

    private Player playerOne, playerTwo;
    private Turn turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final GridView gridview = (GridView) findViewById(R.id.gameGrid);
        gridview.setAdapter(new ImageAdapter(this));

        final Grid grid = new Grid(4, 6);

        playerOne = getIntent().getExtras().getParcelable("playerOne");
        TextView playerOneView = (TextView) findViewById(R.id.playerOneTextView);
        playerOneView.setText(playerOne.getName());
        playerOneView.setRotation(90);

        playerTwo = getIntent().getExtras().getParcelable("playerTwo");
        TextView playerTwoView = (TextView) findViewById(R.id.playerTwoTextView);
        playerTwoView.setText(playerTwo.getName());
        playerTwoView.setRotation(270);

        if (Math.random() < 0.5) {
            Toast.makeText(GameActivity.this, playerOne.getName() + " commence la partie", Toast.LENGTH_SHORT).show();
            ((ImageView) findViewById(R.id.playerOneToken)).setImageResource(R.drawable.circle_green);
            turn = new Turn(playerOne);
        } else{
            Toast.makeText(GameActivity.this, playerTwo.getName() + " commence la partie", Toast.LENGTH_SHORT).show();
            ((ImageView) findViewById(R.id.playerTwoToken)).setImageResource(R.drawable.circle_green);
            turn = new Turn(playerTwo);
        }

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageView iv = (ImageView) gridview.getAdapter().getView(position, v, parent);
                //int column = position % 4;
                //int row = position / 4;
                if (iv.getImageAlpha() != 0){
                iv.setImageResource(grid.getCardGrid().get(position).getId());
//                Toast.makeText(GameActivity.this, "" + position, Toast.LENGTH_SHORT).show();

                if (turn.getCardOne() == null) {
                    turn.setCardOne(grid.getCardGrid().get(position));
                } else if (turn.getCardTwo() == null) {
                    turn.setCardTwo(grid.getCardGrid().get(position));
                } else {
                    if (turn.equalCards()) {
                        turn.getPlayer().setScore(turn.getPlayer().getScore() + 1);

                        ImageView iv1 = (ImageView) gridview.getAdapter().getView(turn.getCardOne().getPosition(), v, parent);
                        iv1.setImageResource(R.drawable.no_image);
                        iv1.setImageAlpha(0);
                        ImageView iv2 = (ImageView) gridview.getAdapter().getView(turn.getCardTwo().getPosition(), v, parent);
                        iv2.setImageResource(R.drawable.no_image);
                        iv2.setImageAlpha(0);
                        Toast.makeText(GameActivity.this, turn.getPlayer().getName() + " " + turn.getPlayer().getScore(), Toast.LENGTH_SHORT).show();
                        turn = new Turn(turn.getPlayer());
                    } else {
                        ImageView iv1 = (ImageView) gridview.getAdapter().getView(turn.getCardOne().getPosition(), v, parent);
                        iv1.setImageResource(R.drawable.back_red_2);
                        ImageView iv2 = (ImageView) gridview.getAdapter().getView(turn.getCardTwo().getPosition(), v, parent);
                        iv2.setImageResource(R.drawable.back_red_2);
                        turn = new Turn(turn.getPlayer().equals(playerOne) ? playerTwo : playerOne);
                        if (turn.getPlayer().equals(playerOne)) {
                            ((ImageView) findViewById(R.id.playerOneToken)).setImageResource(R.drawable.circle_green);
                            ((ImageView) findViewById(R.id.playerTwoToken)).setImageResource(R.drawable.circle_red);
                            Toast.makeText(GameActivity.this, playerOne.getName(), Toast.LENGTH_SHORT).show();
                        } else {
                            ((ImageView) findViewById(R.id.playerOneToken)).setImageResource(R.drawable.circle_red);
                            ((ImageView) findViewById(R.id.playerTwoToken)).setImageResource(R.drawable.circle_green);
                            Toast.makeText(GameActivity.this, playerTwo.getName(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
            }
        });

    }

    private void createGrid() {

    }
}
