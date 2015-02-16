package com.memory.memory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.memory.memory.model.Grid;
import com.memory.memory.adapter.ImageAdapter;
import com.memory.memory.model.Player;
import com.memory.memory.model.Tablet;
import com.memory.memory.model.TabletTask;
import com.memory.memory.model.Turn;


public class GameActivity extends ActionBarActivity {

    private GridView gridview;
    private Grid grid;
    private Player playerOne;
    private Tablet playerTwo;
    private Turn turn;
    private ImageView playerOneCircle, playerTwoCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gridview = (GridView) findViewById(R.id.gameGrid);
        gridview.setAdapter(new ImageAdapter(this));

        grid = new Grid(4, 6);

        playerOne = getIntent().getExtras().getParcelable("playerOne");
        TextView playerOneView = (TextView) findViewById(R.id.playerOneTextView);
        playerOneView.setText(playerOne.getName());
        //playerOneView.setRotation(90);

        playerTwo = getIntent().getExtras().getParcelable("playerTwo");
        TextView playerTwoView = (TextView) findViewById(R.id.playerTwoTextView);
        playerTwoView.setText(playerTwo.getName());
        //playerTwoView.setRotation(270);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageView iv = (ImageView) gridview.getAdapter().getView(position, v, parent);
                if (iv.getImageAlpha() != 0){
                    iv.setImageResource(grid.getCardGrid().get(position).getId());
                    if (vsIA())
                        playerTwo.addCard(grid.getCardGrid().get(position));
                if (turn.getCardOne() == null) {
                    turn.setCardOne(grid.getCardGrid().get(position));
                    turn.getCardOne().setView(v);
                    turn.getCardOne().setParent(parent);
                } else if (turn.getCardTwo() == null) {
                    if (turn.getCardOne().getPosition() != position) {
                        turn.setCardTwo(grid.getCardGrid().get(position));
                        turn.getCardTwo().setView(v);
                        turn.getCardTwo().setParent(parent);
                        gridview.setEnabled(false);
                        if (turn.equalCards()) {
                            turn.getPlayer().setScore(turn.getPlayer().getScore() + 1);
                            ((TextView) findViewById(R.id.playerOneScoreTextView)).setText(playerOne.getScore() + "");
                            ((TextView) findViewById(R.id.playerTwoScoreTextView)).setText(playerTwo.getScore() +"");
                            if (vsIA())
                                playerTwo.winTurn(turn.getCardOne(), turn.getCardTwo());
                            if (turn.getPlayer().equals(playerOne)) {
                                playerOneCircle.setImageResource(R.drawable.circle_orange);
                                playerOneCircle.setClickable(true);
                            } else {
                                playerTwoCircle.setImageResource(R.drawable.circle_orange);
                                playerTwoCircle.setClickable(true);
                            }
                        } else {
                            if (turn.getPlayer().equals(playerOne)) {
                                playerTwoCircle.setImageResource(R.drawable.circle_orange);
                                playerTwoCircle.setClickable(true);
                            } else {
                                playerOneCircle.setImageResource(R.drawable.circle_orange);
                                playerOneCircle.setClickable(true);
                            }
                        }
                    }
                }
            }
            }
        });

        playerOneCircle = (ImageView) findViewById(R.id.playerOneToken);
        playerOneCircle.setClickable(false);
        playerTwoCircle = (ImageView) findViewById(R.id.playerTwoToken);
        playerTwoCircle.setClickable(false);

        playerOneCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnEnd();
                playerOneCircle.setClickable(false);
                playerOneCircle.setImageResource(R.drawable.circle_green);
                gridview.setEnabled(true);
            }
        });

        playerTwoCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnEnd();
                playerTwoCircle.setClickable(false);
                playerTwoCircle.setImageResource(R.drawable.circle_green);
                gridview.setEnabled(true);
                if (vsIA()) {
                    playerTwo.play(GameActivity.this);
                    playerTwo.wait(1000);
                }
            }
        });

        if (vsIA()){
            playerTwo.initPositions(4,6);
        }
        if (Math.random() < 0.5) {
            Toast.makeText(GameActivity.this, playerOne.getName() + " start", Toast.LENGTH_SHORT).show();
            ((ImageView) findViewById(R.id.playerOneToken)).setImageResource(R.drawable.circle_green);
            turn = new Turn(playerOne);
        } else{
            Toast.makeText(GameActivity.this, playerTwo.getName() + " start", Toast.LENGTH_SHORT).show();
            ((ImageView) findViewById(R.id.playerTwoToken)).setImageResource(R.drawable.circle_green);
            turn = new Turn(playerTwo);
            if (vsIA()){
                playerTwoCircle.setImageResource(R.drawable.circle_orange);
                playerTwoCircle.setClickable(true);
            }
        }

    }

    private boolean vsIA() {
        return playerTwo.getName().equals("ASUS Nexus 7");
    }

    private void turnEnd() {
        if (turn.getCardOne() != null)
        if (turn.equalCards()) {
            turn.getCardOne().takeOff(gridview);
            turn.getCardTwo().takeOff(gridview);
            if (playerOne.getScore() + playerTwo.getScore() < 12)
                turn = new Turn(turn.getPlayer());
            else
                partyEnd();
        } else {
            turn.getCardOne().reverse(gridview);
            turn.getCardTwo().reverse(gridview);
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

    private void partyEnd() {
        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
        intent.putExtra("playerOne", playerOne);
        intent.putExtra("playerTwo", playerTwo);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //showDialog();
            Dialog dialog = onCreateDialog();
            dialog.show();
        }
        return true;
    }
    protected Dialog onCreateDialog() {
// Création d'un boite de dialogue
        Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("you want to leave the game?");
        builder.setCancelable(false);
        builder.setTitle("Confirmation");

        builder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(GameActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

        builder.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        dialog = builder.create();
        return dialog;
    }

    public GridView getGridview() {
        return gridview;
    }

    public void setGridview(GridView gridview) {
        this.gridview = gridview;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Tablet getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Tablet playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public ImageView getPlayerOneCircle() {
        return playerOneCircle;
    }

    public void setPlayerOneCircle(ImageView playerOneCircle) {
        this.playerOneCircle = playerOneCircle;
    }

    public ImageView getPlayerTwoCircle() {
        return playerTwoCircle;
    }

    public void setPlayerTwoCircle(ImageView playerTwoCircle) {
        this.playerTwoCircle = playerTwoCircle;
    }
}
