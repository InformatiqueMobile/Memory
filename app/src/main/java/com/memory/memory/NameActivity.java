package com.memory.memory;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.memory.memory.model.Player;


public class NameActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
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
        Player playerTwo = new Player(((EditText) findViewById(R.id.secondPlayerName)).getText().toString());
        Intent intent = new Intent(NameActivity.this, GameActivity.class);
        intent.putExtra("playerOne", playerOne);
        intent.putExtra("playerTwo", playerTwo);
        startActivity(intent);
    }

}
