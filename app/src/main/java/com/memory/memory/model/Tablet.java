package com.memory.memory.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.memory.memory.GameActivity;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Zalila on 2015-01-30.
 */
public class Tablet extends Player  implements Parcelable {

    private Vector<Card> memory = new Vector<>();
    private Vector<Integer> positions = new Vector<>();
    private int memorySize;

    public Tablet(String name ,int memorySize) {
        super(name);
        this.memorySize = memorySize;
    }

    public Tablet(Parcel in) {
        super(in);
        this.memorySize = in.readInt();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.getName());
        dest.writeInt(this.getScore());
        dest.writeInt(memorySize);
    }

    public static final Parcelable.Creator<Tablet> CREATOR = new Parcelable.Creator<Tablet>()
    {
        @Override
        public Tablet createFromParcel(Parcel source)
        {
            return new Tablet(source);
        }

        @Override
        public Tablet[] newArray(int size)
        {
            return new Tablet[size];
        }
    };

    public void addCard(Card card){
        if (memory.indexOf(card) == -1) {
            if (memory.size() < memorySize)
                memory.add(card);
            else {
                memory.remove(0);
                memory.add(card);
            }
        }
    }

    public void winTurn(Card cardOne, Card cardTwo){
        memory.removeElement(cardOne);
        memory.removeElement(cardTwo);
        positions.removeElement(cardOne.getPosition());
        positions.removeElement(cardTwo.getPosition());
    }

    public boolean play(GameActivity gameActivity){
            for (int i = 0; i < memory.size(); i++){
                for (int j = i + 1; j < memory.size(); j++){
                    if (memory.get(i).getId() == memory.get(j).getId()){
                        gameActivity.getGridview().performItemClick(gameActivity.getGridview().getChildAt(memory.get(i).getPosition()),memory.get(i).getPosition(),0);
                        gameActivity.getGridview().performItemClick(gameActivity.getGridview().getChildAt(memory.get(j).getPosition()),memory.get(j).getPosition(),0);
                        return true;
                    }
                }
            }
        int pos = randomPosition();
        gameActivity.getGridview().performItemClick(gameActivity.getGridview().getChildAt(pos),pos,0);
        if (memory.size() > 0)
            for (int i = 0; i < memory.size() - 1; i++){
                if (memory.get(i).getId() == gameActivity.getGrid().getCardGrid().get(pos).getId()){
                    gameActivity.getGridview().performItemClick(gameActivity.getGridview().getChildAt(memory.get(i).getPosition()),memory.get(i).getPosition(),0);
                    return true;
                }
            }
        int pos2 = randomPosition();
        while ( pos == pos2){pos2 = randomPosition();}
        gameActivity.getGridview().performItemClick(gameActivity.getGridview().getChildAt(pos2), pos2, 0);

        return false;
    }

    public Vector<Card> getMemory() {
        return memory;
    }

    public void setMemory(Vector<Card> memory) {
        this.memory = memory;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void wait(int i ){
        Long t1 = System.currentTimeMillis();
        while(System.currentTimeMillis()<t1+i){

            try{
                wait();
            }catch(Exception exc){}

        }
    }

    public Vector<Integer> getPositions() {
        return positions;
    }

    public void setPositions(Vector<Integer> positions) {
        this.positions = positions;
    }

    public void initPositions(int column, int row) {
        for (int i = 0; i < column * row; i++){
            positions.add(i);
        }
    }

    public int randomPosition(){
        Vector<Integer> memoryPostion = new Vector<>();
            for (int i = 0; i < memory.size(); i++) {
                memoryPostion.add(memory.get(i).getPosition());
            }
        while(memoryPostion.indexOf(this.positions.get(0)) != -1)
            Collections.shuffle(this.positions, new Random(System.nanoTime()));
        return this.positions.get(0).intValue();
    }
}
