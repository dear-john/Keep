package com.spring_ballet.keep.bean.MovieBean;


import android.os.Parcel;
import android.os.Parcelable;

import com.spring_ballet.keep.base.BaseClass;

public class Casts extends BaseClass implements Parcelable{

    private String alt;
    private String id;
    private String name;
    private Avatars avatars;

    private Casts(Parcel in) {
        alt = in.readString();
        id = in.readString();
        name = in.readString();
        avatars = in.readParcelable(Avatars.class.getClassLoader());
    }

    public static final Creator<Casts> CREATOR = new Creator<Casts>() {
        @Override
        public Casts createFromParcel(Parcel in) {
            return new Casts(in);
        }

        @Override
        public Casts[] newArray(int size) {
            return new Casts[size];
        }
    };

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(alt);
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeParcelable(avatars, i);
    }
}
