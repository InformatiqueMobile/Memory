package com.memory.memory.model;

/**
 * Created by Zalila on 2015-02-03.
 */
public class Turn {

    private Card cardOne;
    private Card cardTwo;
    private Player player;

    public Turn(Player player) {
        this.cardOne = null;
        this.cardTwo = null;
        this.player = player;
    }

    public Card getCardOne() {
        return cardOne;
    }

    public void setCardOne(Card cardOne) {
        this.cardOne = cardOne;
    }

    public Card getCardTwo() {
        return cardTwo;
    }

    public void setCardTwo(Card cardTwo) {
        this.cardTwo = cardTwo;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean equalCards(){
        if (this.cardOne.getId() == this.cardTwo.getId())
            return true;
        return false;
    }
}
