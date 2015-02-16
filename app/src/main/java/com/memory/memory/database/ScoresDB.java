package com.memory.memory.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.memory.memory.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Zalila on 2015-02-13.
 */
public class ScoresDB {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "memory.db";

    private static final String TABLE_SCORES = "table_scores";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "name";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_SCORE = "score";
    private static final int NUM_COL_SCORE = 2;

    private SQLiteDatabase bdd;

    private Database database;

    public ScoresDB(Context context){
        database = new Database(context, NOM_BDD, null, VERSION_BDD);
        database.onCreate(bdd);
    }

    public void open(){
        bdd = database.getWritableDatabase();
        //SQLiteDatabase mDatabase = SQLiteDatabase.openOrCreateDatabase("memory.db", SQLiteDatabase.CREATE_IF_NECESSARY,null);
    }

    public void close(){
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertScore(Player player){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, player.getName());
        values.put(COL_SCORE, player.getScore());
        return bdd.insert(TABLE_SCORES, null, values);
    }

    public int updateScore(int id, Player player){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, player.getName());
        values.put(COL_SCORE, player.getScore());
        return bdd.update(TABLE_SCORES, values, COL_ID + " = " + id, null);
    }

    public int removePlayerWithID(int id){
        return bdd.delete(TABLE_SCORES, COL_ID + " = " + id, null);
    }

    private Player cursorToScore(Cursor c){
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();

        Player player = new Player(c.getString(NUM_COL_NAME));
        player.setScore(c.getInt(NUM_COL_SCORE));

        c.close();

        return player;
    }

    private Vector<Player> getAll(){
        Vector<Player> scores = new Vector<>();


        return scores;
    }

    public List<Player> getAllScores() {
        List<Player> players = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_SCORES + "ORDER BY `score` LIMIT 10";

        this.open();
        SQLiteDatabase db = this.getBDD();//WritableDatabase();
        if (!isTableExists(db, TABLE_SCORES))
            database.onCreate(db);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Integer.parseInt(cursor.getString(0));
                Player player = new Player(cursor.getString(1));
                player.setScore(cursor.getInt(2));
                players.add(player);
            } while (cursor.moveToNext());
        }

        return players;
    }

    boolean isTableExists(SQLiteDatabase db, String tableName)
    {
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
        if (!cursor.moveToFirst())
        {
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }
}