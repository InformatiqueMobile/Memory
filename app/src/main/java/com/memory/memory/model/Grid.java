package com.memory.memory.model;

import com.memory.memory.R;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Zalila on 2015-01-30.
 */
public class Grid {
    private Vector<Card> cardGrid  = new Vector<>();

    //crée la grille
    public Grid(int column, int row) {
        Vector<Card> cards = selectCards((column * row) / 2);

        // mélange la grille
        Collections.shuffle(cards, new Random(System.nanoTime()));
        cardGrid = cards;
        for (int i = 0; i < cardGrid.size(); i++){
            cardGrid.get(i).setPosition(i);
        }
    }


    //ajoute les cartes
    private Vector<Card> selectCards(int number) {
        Vector<Card> cards = new Vector<>();
        Card c1,c2;
        for (int i = 0; i < number ; i++)
        {
            c1 = new Card();
            c1.setId(images[i]);
            cards.add(c1);
            c2 = new Card();
            c2.setId(images[i]);
            cards.add(c2);
        }
        return cards;
    }

    public Vector<Card> getCardGrid() {
        return cardGrid;
    }

    public void setCardGrid(Vector<Card> cardGrid) {
        this.cardGrid = cardGrid;
    }

    // references des images
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
