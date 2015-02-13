package com.memory.memory.model;

import android.media.Image;

/**
 * Created by Zalila on 2015-01-30.
 */
public class Card {
    private int id;
    private Image image;
    private int position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
