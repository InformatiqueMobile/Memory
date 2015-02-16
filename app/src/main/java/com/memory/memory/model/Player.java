package com.memory.memory.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zalila on 2015-01-30.
 */

public class Player implements Parcelable{

    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeInt(score);
    }


    // CREATOR permet de décrire au Parcel comment construire l'Objet
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>()
    {
        @Override
        public Player createFromParcel(Parcel source)
        {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size)
        {
            return new Player[size];
        }
    };

    //Constructeur avec Parcel
    public Player(Parcel in) {
        this.name = in.readString();
        this.score = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name + '\t' + score ;
    }
}
