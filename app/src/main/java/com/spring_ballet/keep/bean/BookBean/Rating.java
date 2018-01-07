package com.spring_ballet.keep.bean.BookBean;


import android.os.Parcel;
import android.os.Parcelable;

import com.spring_ballet.keep.base.BaseClass;

public class Rating extends BaseClass implements Parcelable{
    private int max;
    private int numRaters;
    private float average;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getNumRaters() {
        return numRaters;
    }

    public void setNumRaters(int numRaters) {
        this.numRaters = numRaters;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    private int min;

    private Rating(Parcel in) {
        max = in.readInt();
        numRaters = in.readInt();
        average = in.readFloat();
        min = in.readInt();
    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(max);
        parcel.writeInt(numRaters);
        parcel.writeFloat(average);
        parcel.writeInt(min);
    }
}
