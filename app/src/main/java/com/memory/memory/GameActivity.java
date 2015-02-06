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

import java.util.Vector;


public class GameActivity extends ActionBarActivity {

    private Player playerOne, playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final GridView gridview = (GridView) findViewById(R.id.gameGrid);
        gridview.setAdapter(new ImageAdapter(this));

        final Grid grid = new Grid(4,6);

        playerOne = getIntent().getExtras().getParcelable("playerOne");
        TextView playerOneView = (TextView)findViewById(R.id.playerOneTextView);
        playerOneView.setText(playerOne.getName());
        playerOneView.setRotation(90);

        playerTwo = getIntent().getExtras().getParcelable("playerTwo");
        TextView playerTwoView = (TextView)findViewById(R.id.playerTwoTextView);
        playerTwoView.setText(playerTwo.getName());
        playerTwoView.setRotation(270);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(GameActivity.this, "" + position, Toast.LENGTH_SHORT).show();

                ImageView iv = (ImageView) gridview.getAdapter().getView(position, v, parent);

                //if (iv.getImageAlpha() == R.drawable.back_red_2)
                    //iv.setImageResource(R.drawable.back_blue_0);
                //else
                  //  iv.setImageResource(R.drawable.back_red_2);
                int column = position%4;
                int row = position/4;
                iv.setImageResource(grid.getCardGrid().get(row).get(column).getId());

            }
        });


    }

    private void createGrid() {

    }
}
