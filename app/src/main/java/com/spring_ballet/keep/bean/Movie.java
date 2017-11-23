package com.spring_ballet.keep.bean;

import com.spring_ballet.keep.bean.MovieBean.Casts;
import com.spring_ballet.keep.bean.MovieBean.Directors;
import com.spring_ballet.keep.bean.MovieBean.Images;
import com.spring_ballet.keep.bean.MovieBean.Rating;

import java.util.List;

public class Movie {
    public String id;
    public String title;
    public String original_title;
    public List<String> aka;
    public Rating rating;
    public int ratings_count;
    public int wish_count;
    public int collect_count;
    public Images images;
    public List<Directors> directors;
    public List<String> countries;
    public List<Casts> casts;
    public String year;
    public List<String> languages;
    public List<String> durations;
    public List<String> genres;
    public String summary;
}
