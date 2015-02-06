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

        //TODO Random

        Vector<Card> rows;
        for (int i = 0; i < row ; i++){
            rows = new Vector<>();
            for (int j = 0; j < column ; j++){
                if (((i*column)+ j) < cards.size())
                    rows.add(cards.get(i*column+j));
                else
                    rows.add(cards.get((i*column) + j - cards.size()));//((i+1)*(j+1))-cards.size()));
            }
            cardGrid.add(rows);
        }
        /*int i = 0, j = 0 ;rows = new Vector<>();
        while(cards.iterator().hasNext()){
            if ((j==0)&&(i!=0))    {cardGrid.add(rows);rows = new Vector<>();}
            if (j==4)    {j=0;i++;}
            rows.add(cards.iterator().next());
            j++;
        }*/
    }

    private Vector<Card> selectCards(int number) {
        Vector<Card> cards = new Vector<>();
        Card c;
        for (int i = 0; i < number ; i++)
        {
            c = new Card();
            c.setId(images[i]);
            cards.add(c);
        }
        return cards;
    }

    public Vector<Vector<Card>> getCardGrid() {
        return cardGrid;
    }

    public void setCardGrid(Vector<Vector<Card>> cardGrid) {
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
