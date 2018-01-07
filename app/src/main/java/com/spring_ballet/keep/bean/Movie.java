package com.spring_ballet.keep.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.spring_ballet.keep.base.BaseClass;
import com.spring_ballet.keep.bean.MovieBean.Casts;
import com.spring_ballet.keep.bean.MovieBean.Directors;
import com.spring_ballet.keep.bean.MovieBean.Images;
import com.spring_ballet.keep.bean.MovieBean.Rating;

import java.util.List;

public class Movie extends BaseClass implements Parcelable{
    private String id;
    private String title;
    private String original_title;
    private List<String> aka;
    private Rating rating;
    private int ratings_count;
    private int wish_count;
    private int collect_count;
    private Images images;
    private List<Directors> directors;
    private String mobile_url;
    private String alt;
    private List<String> countries;
    private List<Casts> casts;
    private String year;
    private List<String> languages;
    private List<String> durations;
    private List<String> genres;
    private String summary;

    protected Movie(Parcel in) {
        id = in.readString();
        title = in.readString();
        original_title = in.readString();
        aka = in.createStringArrayList();
        rating = in.readParcelable(Rating.class.getClassLoader());
        ratings_count = in.readInt();
        wish_count = in.readInt();
        collect_count = in.readInt();
        images = in.readParcelable(Images.class.getClassLoader());
        directors = in.createTypedArrayList(Directors.CREATOR);
        mobile_url = in.readString();
        alt = in.readString();
        countries = in.createStringArrayList();
        casts = in.createTypedArrayList(Casts.CREATOR);
        year = in.readString();
        languages = in.createStringArrayList();
        durations = in.createStringArrayList();
        genres = in.createStringArrayList();
        summary = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List<Directors> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Directors> directors) {
        this.directors = directors;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<Casts> getCasts() {
        return casts;
    }

    public void setCasts(List<Casts> casts) {
        this.casts = casts;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getDurations() {
        return durations;
    }

    public void setDurations(List<String> durations) {
        this.durations = durations;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(original_title);
        parcel.writeStringList(aka);
        parcel.writeParcelable(rating, i);
        parcel.writeInt(ratings_count);
        parcel.writeInt(wish_count);
        parcel.writeInt(collect_count);
        parcel.writeParcelable(images, i);
        parcel.writeTypedList(directors);
        parcel.writeString(mobile_url);
        parcel.writeString(alt);
        parcel.writeStringList(countries);
        parcel.writeTypedList(casts);
        parcel.writeString(year);
        parcel.writeStringList(languages);
        parcel.writeStringList(durations);
        parcel.writeStringList(genres);
        parcel.writeString(summary);
    }
}
