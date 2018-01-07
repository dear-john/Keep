package com.spring_ballet.keep.bean.MovieBean;


import android.os.Parcel;
import android.os.Parcelable;

import com.spring_ballet.keep.base.BaseClass;

import java.util.List;

public class UsBox extends BaseClass implements Parcelable{
    private String date;
    private String title;
    private List<Subject> subjects;

    protected UsBox(Parcel in) {
        date = in.readString();
        title = in.readString();
        subjects = in.createTypedArrayList(Subject.CREATOR);
    }

    public static final Creator<UsBox> CREATOR = new Creator<UsBox>() {
        @Override
        public UsBox createFromParcel(Parcel in) {
            return new UsBox(in);
        }

        @Override
        public UsBox[] newArray(int size) {
            return new UsBox[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(title);
        parcel.writeTypedList(subjects);
    }
}
