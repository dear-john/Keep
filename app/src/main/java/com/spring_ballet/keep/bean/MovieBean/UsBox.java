package com.spring_ballet.keep.bean.MovieBean;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsBox {
    public String date;
    public String title;
    public List<Subject> subjects;

    public class Subject {
        public int box;
        public int rank;
        @SerializedName("new")
        public boolean isNew;
        public Subjects subject;
    }
}
