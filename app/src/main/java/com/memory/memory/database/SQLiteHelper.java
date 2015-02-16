package com.memory.memory.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.memory.memory.model.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zalila on 2015-02-16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "memorydb";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SCORES_TABLE = "CREATE TABLE scores ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, "+
                "score INTEGER )";

        db.execSQL(CREATE_SCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS scores");
        this.onCreate(db);
    }

    private static final String TABLE_SCORES = "scores";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE= "score";

    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_SCORE};

    public void addScore(Player player){
        Log.d("addScore", player.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, player.getName());
        values.put(KEY_SCORE, player.getScore());

        db.insert(TABLE_SCORES, null, values);

        db.close();
    }

    public Player getScore(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_SCORES, COLUMNS, " id = ?",
                        new String[] { String.valueOf(id) },
                        null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Player player = new Player(cursor.getString(1));
        player.setScore(cursor.getInt(2));

        Log.d("getScore(" + id + ")", player.toString());

        return player;
    }

    public List<Player> getAllScores() {
        List<Player> scores = new LinkedList<Player>();

        String query = "SELECT  * FROM " + TABLE_SCORES ;//+ "ORDER BY " + KEY_SCORE + " DESC";//  LIMIT 10";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Player player = null;
        if (cursor.moveToFirst()) {
            do {
                player = new Player(cursor.getString(1));
                player.setScore(cursor.getInt(2));

                scores.add(player);
            } while (cursor.moveToNext());
        }

        Log.d("getAllScores()", scores.toString());

        return scores;
    }

/*    public int updateScores(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", player.getName());
        values.put("score", player.getScore());

        int i = db.update(TABLE_SCORES,
                values,
                KEY_ID+" = ?",
                new String[] { String.valueOf(player.getId()) });

        db.close();

        return i;

    }


    public void deleteScore(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SCORES,
                KEY_ID+" = ?",
                new String[] { String.valueOf(player.getId()) });

        db.close();

        Log.d("deleteScore", player.toString());

    }*/
}