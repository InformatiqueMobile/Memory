package com.memory.memory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.memory.memory.R;
import com.memory.memory.model.Player;

import java.util.List;

/**
 * Created by Zalila on 2015-02-14.
 */
public class StringAdapter extends BaseAdapter {


    List<Player> epreuve;
    LayoutInflater inflater;

    /**
     * Ici le constructeur de l'adapter
     *
     * @param context et la liste de Strings
     */
    public StringAdapter(Context context, List<Player> epreuve) {
        inflater = LayoutInflater.from(context);
        this.epreuve = epreuve;
    }

    public int getCount() {
        return epreuve.size();
    }

    public Object getItem(int position) {
        return epreuve.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView numElement;
        TextView element;
        TextView score;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_scores, null);
            /*holder.numElement = (TextView)convertView.findViewById(R.id.numElement);
            holder.element = (TextView)convertView.findViewById(R.id.element);
            holder.score = (TextView)convertView.findViewById(R.id.score);*/
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.numElement.setText("" + position);
        holder.element.setText(epreuve.get(position).getName());
        holder.score.setText(epreuve.get(position).getScore());
        return convertView;
    }

}
