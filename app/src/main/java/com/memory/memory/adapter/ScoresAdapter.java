package com.memory.memory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.memory.memory.R;
import com.memory.memory.database.ScoresDB;
import com.memory.memory.model.Player;

import java.util.List;

/**
 * Created by Zalila on 2015-02-03.
 */
public class ScoresAdapter extends BaseAdapter {
    private Context mContext;
    private List<Player> players;
    private LayoutInflater inflater;

    public ScoresAdapter(Context c) {
        mContext = c;
        inflater = LayoutInflater.from(c);
        ScoresDB scoresDB = new ScoresDB(c);
        scoresDB.open();
        players = scoresDB.getAllScores();
    }

    public int getCount() {
        return players.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ListView listView;
        ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();
            listView = new ListView(mContext);
            //listView.setLayoutParams(new GridView.LayoutParams(230, 230));
            //listView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            convertView = inflater.inflate(R.layout.activity_scores, null);
            listView.setPadding(8, 8, 8, 8);
            /*holder.numElement = (TextView)convertView.findViewById(R.id.numElement);
            holder.element = (TextView)convertView.findViewById(R.id.element);
            holder.score = (TextView)convertView.findViewById(R.id.score);*/
            convertView.setTag(holder);
        } else {
            listView = (ListView) convertView;
            holder = (ViewHolder) convertView.getTag();
        }

        holder.numElement.setText("" + position);
        holder.element.setText(players.get(position).getName());
        holder.score.setText(players.get(position).getScore());
        return listView;
    }


    private class ViewHolder {
        TextView numElement;
        TextView element;
        TextView score;
    }

}
