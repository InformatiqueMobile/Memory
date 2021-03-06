package com.memory.memory;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onePlayerClick(View view){
        Intent intent = new Intent(MainActivity.this, NameActivity.class);
        intent.putExtra("vsIA", true);
        startActivity(intent);
    }

    public void twoPlayersClick(View view){
        Intent intent = new Intent(MainActivity.this, NameActivity.class);
        intent.putExtra("vsIA", false);
        startActivity(intent);
    }

    public void highscoresClick(View view){
        Intent intent = new Intent(MainActivity.this, ScoresActivity.class);
        startActivity(intent);
    }

    public void exitClick(){
        finish();
        System.exit(0);
    }
}
