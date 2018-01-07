package com.spring_ballet.keep.bean.MovieBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Subject implements Parcelable{
    private int box;
    private int rank;
    @SerializedName("new")
    private boolean isNew;
    private Subjects subject;

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }

    private Subject(Parcel in) {
        box = in.readInt();
        rank = in.readInt();
        isNew = in.readByte() != 0;
        subject = in.readParcelable(Subjects.class.getClassLoader());
    }

    public static final Creator<Subject> CREATOR = new Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel in) {
            return new Subject(in);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(box);
        parcel.writeInt(rank);
        parcel.writeByte((byte) (isNew ? 1 : 0));
        parcel.writeParcelable(subject, i);
    }
}
