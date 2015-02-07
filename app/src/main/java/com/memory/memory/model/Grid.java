package com.memory.memory.model;

import com.memory.memory.R;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Zalila on 2015-01-30.
 */
public class Grid {

    //private Vector<Card> cards = new Vector<>();
    private Vector<Card> cardGrid  = new Vector<>();

    public Grid(int column, int row) {
        Vector<Card> cards = selectCards((column * row) / 2);

        //TODO Random

        //cardGrid = random(cards);
        Collections.shuffle(cards, new Random(System.nanoTime()));
        cardGrid = cards;
        /*Vector<Card> rows;
        for (int i = 0; i < row; i++){
            rows = new Vector<>();
            for (int j = 0; j < column; j++){
                if (((i * column) + j) < cards.size())
                    rows.add(cards.get(i * column + j));
                else
                    rows.add(cards.get((i * column) + j - cards.size()));
            }
            cardGrid.add(rows);
        }*/
    }

    private Vector<Card> random(Vector<Card> cards) {

        return  cards;
    }

    private Vector<Card> selectCards(int number) {
        Vector<Card> cards = new Vector<>();
        Card c;
        for (int i = 0; i < number ; i++)
        {
            c = new Card();
            c.setId(images[i]);
            cards.add(c);
            cards.add(c);
        }
        return cards;
    }

    public Vector<Card> getCardGrid() {
        return cardGrid;
    }

    public void setCardGrid(Vector<Card> cardGrid) {
        this.cardGrid = cardGrid;
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
