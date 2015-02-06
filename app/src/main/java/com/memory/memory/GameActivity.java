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

import com.memory.memory.model.ImageAdapter;


public class GameActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final GridView gridview = (GridView) findViewById(R.id.gameGrid);
        gridview.setAdapter(new ImageAdapter(this));

        TextView playerTwo = (TextView)findViewById(R.id.playerTwoTextView);
        playerTwo.setRotation(270);

        TextView playerOne = (TextView)findViewById(R.id.playerOneTextView);
        playerOne.setRotation(90);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(GameActivity.this, "" + position, Toast.LENGTH_SHORT).show();

                ImageView iv = (ImageView) gridview.getAdapter().getView(position, v, parent);

                //if (iv.equals(R.drawable.back_red_2) )
                iv.setImageResource(R.drawable.no_image);
            }
        });


    }
}
