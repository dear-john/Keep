package com.spring_ballet.keep.bean.MovieBean;


import android.os.Parcel;
import android.os.Parcelable;

import com.spring_ballet.keep.base.BaseClass;

import java.util.List;

public class Subjects extends BaseClass implements Parcelable{
    private Rating rating;
    private List<String> genres;
    private String title;
    private List<Casts> casts;
    private List<Directors> directors;
    private String year;
    private Images images;
    private String id;

    private Subjects(Parcel in) {
        rating = in.readParcelable(Rating.class.getClassLoader());
        genres = in.createStringArrayList();
        title = in.readString();
        casts = in.createTypedArrayList(Casts.CREATOR);
        directors = in.createTypedArrayList(Directors.CREATOR);
        year = in.readString();
        images = in.readParcelable(Images.class.getClassLoader());
        id = in.readString();
    }

    public static final Creator<Subjects> CREATOR = new Creator<Subjects>() {
        @Override
        public Subjects createFromParcel(Parcel in) {
            return new Subjects(in);
        }

        @Override
        public Subjects[] newArray(int size) {
            return new Subjects[size];
        }
    };

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Casts> getCasts() {
        return casts;
    }

    public void setCasts(List<Casts> casts) {
        this.casts = casts;
    }

    public List<Directors> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Directors> directors) {
        this.directors = directors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(rating, i);
        parcel.writeStringList(genres);
        parcel.writeString(title);
        parcel.writeTypedList(casts);
        parcel.writeTypedList(directors);
        parcel.writeString(year);
        parcel.writeParcelable(images, i);
        parcel.writeString(id);
    }
}
