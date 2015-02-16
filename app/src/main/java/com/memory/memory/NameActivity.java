package com.memory.memory;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.memory.memory.model.Player;
import com.memory.memory.model.Tablet;


public class NameActivity extends ActionBarActivity {

    boolean vsIA ;
    EditText firstPlayerName, secondPlayerName;
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        vsIA = getIntent().getBooleanExtra("vsIA", false);
        firstPlayerName = (EditText) findViewById(R.id.firstPlayerName);
        secondPlayerName = (EditText) findViewById(R.id.secondPlayerName);
        if (vsIA){
            secondPlayerName.setText("ASUS Nexus 7");
            secondPlayerName.setEnabled(false);
        }
        playButton = (Button) findViewById(R.id.playButton);
        playButton.setEnabled(false);

        secondPlayerName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (secondPlayerName.getText().toString().equals(""))
                    playButton.setEnabled(false);
                else if (firstPlayerName.getText().toString().equals(""))
                    playButton.setEnabled(false);
                else
                    playButton.setEnabled(true);
                return false;
            }
        });
        firstPlayerName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (firstPlayerName.getText().toString().equals(""))
                    playButton.setEnabled(false);
                else if (secondPlayerName.getText().toString().equals(""))
                    playButton.setEnabled(false);
                else
                    playButton.setEnabled(true);
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_name, menu);
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

    public void playClick(View view){
        Player playerOne = new Player(((EditText) findViewById(R.id.firstPlayerName)).getText().toString());
        Tablet playerTwo;
        //if (vsIA)
            playerTwo = new Tablet(((EditText) findViewById(R.id.secondPlayerName)).getText().toString(), 4);
        /*else
            playerTwo = new Player(((EditText) findViewById(R.id.secondPlayerName)).getText().toString());*/
        if (!playerOne.getName().equals(playerTwo.getName())) {
            Intent intent = new Intent(NameActivity.this, GameActivity.class);
            intent.putExtra("playerOne", playerOne);
            intent.putExtra("playerTwo", playerTwo);
            startActivity(intent);
        }
    }

}
