package com.spring_ballet.keep.bean.MovieBean;


import android.os.Parcel;
import android.os.Parcelable;

import com.spring_ballet.keep.base.BaseClass;

public class Avatars extends BaseClass implements Parcelable{
    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    private Avatars(Parcel in) {
        small = in.readString();
        large = in.readString();
        medium = in.readString();
    }

    public static final Creator<Avatars> CREATOR = new Creator<Avatars>() {
        @Override
        public Avatars createFromParcel(Parcel in) {
            return new Avatars(in);
        }

        @Override
        public Avatars[] newArray(int size) {
            return new Avatars[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(small);
        parcel.writeString(large);
        parcel.writeString(medium);
    }
}
