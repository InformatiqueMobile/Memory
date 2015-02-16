package com.memory.memory.model;

import android.os.AsyncTask;

import com.memory.memory.GameActivity;

/**
 * Created by Zalila on 2015-02-15.
 */
public class TabletTask extends AsyncTask {



    @Override
    protected Object doInBackground(Object[] params) {
        ((Tablet) params[1]).initPositions(4, 6);
        ((Tablet) params[1]).play((GameActivity) params[0]);

        return this;
    }

    protected void onPostExecute(){

    }
}
