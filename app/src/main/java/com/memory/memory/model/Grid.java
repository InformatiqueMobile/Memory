package com.memory.memory.model;

import com.memory.memory.R;

import java.util.Vector;

/**
 * Created by Zalila on 2015-01-30.
 */
public class Grid {

    //private Vector<Card> cards = new Vector<>();
    private Vector<Vector<Card>> cardGrid  = new Vector<>();

    public Grid(int column, int row) {
        Vector<Card> cards = selectCards((column*row)/2);


    }

    private Vector<Card> selectCards(int number) {
        Vector<Card> cards = new Vector<>();
        for (int i = 0; i < number; i++)
        {
            Card c = new Card();
            c.setId(images[i]);
        }
        return cards;
    }

    // references to our images
    private Integer[] images = {
            R.drawable.clubs_01, R.drawable.hearts_02,
            R.drawable.spades_05, R.drawable.diamonds_06,
            R.drawable.clubs_07, R.drawable.hearts_08,
            R.drawable.spades_09, R.drawable.diamonds_10,
            R.drawable.clubs_k, R.drawable.hearts_q,
            R.drawable.spades_v, R.drawable.joker_red,
            R.drawable.clubs_03, R.drawable.hearts_04
    };
}
