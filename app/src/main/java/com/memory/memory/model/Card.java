package com.memory.memory.model;

import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.memory.memory.R;

/**
 * Created by Zalila on 2015-01-30.
 */
public class Card {
    private int id;
    private int position;
    private View view;
    private ViewGroup parent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public ViewGroup getParent() {
        return parent;
    }

    public void setParent(ViewGroup parent) {
        this.parent = parent;
    }

    public void takeOff(GridView gridView){
        ImageView iv = (ImageView) gridView.getAdapter().getView(this.position,
                this.view, this.parent);
        iv.setImageResource(R.drawable.no_image);
        iv.setImageAlpha(0);
        iv.setEnabled(false);
    }

    public void reverse(GridView gridView){
        ImageView iv = (ImageView) gridView.getAdapter().getView(this.position,
                this.view, this.parent);
        iv.setImageResource(R.drawable.back_red_2);
    }
}
