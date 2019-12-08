package com.example.guesswordapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Word implements Parcelable {

    public enum Type {
        easy,
        medium,
        hard;
    }
    private int id;
    private int chances;
    private String value;
    private String description;

    public Word(int id, int chances, String value, String description) {
        this.id = id;
        this.chances = chances;
        this.value = value;
        this.description = description;
    }

    public Word(Parcel in) {
        this.id = in.readInt();
        this.chances = in.readInt();
        this.value = in.readString();
        this.description = in.readString();
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public int getChances() {
        return chances;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(chances);
        dest.writeString(getValue());
        dest.writeString(getDescription());
    }

    public static final Parcelable.Creator<Word> CREATOR
            = new Parcelable.Creator<Word>() {
        public Word createFromParcel(Parcel in) {
            return (new Word(in));
        }

        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public String getChancesString() {
        return "chances " + chances;
    }

    public void decreaseChances() {
        chances--;
    }
}
