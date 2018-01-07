package com.spring_ballet.keep.bean.MovieBean;


import android.os.Parcel;
import android.os.Parcelable;

import com.spring_ballet.keep.base.BaseClass;

import java.util.List;

public class InTheaters extends BaseClass implements Parcelable{
    private List<Subjects> subjects;

    protected InTheaters(Parcel in) {
        subjects = in.createTypedArrayList(Subjects.CREATOR);
    }

    public static final Creator<InTheaters> CREATOR = new Creator<InTheaters>() {
        @Override
        public InTheaters createFromParcel(Parcel in) {
            return new InTheaters(in);
        }

        @Override
        public InTheaters[] newArray(int size) {
            return new InTheaters[size];
        }
    };

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(subjects);
    }
}
