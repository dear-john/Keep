package com.spring_ballet.keep.CommonUtils;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.spring_ballet.keep.bean.Book;
import com.spring_ballet.keep.bean.Movie;
import com.spring_ballet.keep.bean.MovieBean.InTheaters;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.bean.MovieBean.UsBox;

import java.util.ArrayList;
import java.util.List;


public class MyAsyncTask extends AsyncTask<String, Void, String> {

    private AsyncResponse asyncResponse;
    private int type;

    public MyAsyncTask(final int type) {
        this.type = type;
    }

    @Override
    protected String doInBackground(String... strings) {
        return OkHttpUtil.getData(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (!TextUtils.isEmpty(s)) {
            Gson gson = new Gson();
            List<Subjects> subjectsList = new ArrayList<>();
            switch (type) {
                case DiffTypeNumber.HOTMOVIE:
                case DiffTypeNumber.COMINGSOON:
                case DiffTypeNumber.TOPMOVIE:
                case DiffTypeNumber.SEARCHMOVIE:
                    subjectsList = gson.fromJson(s, InTheaters.class).subjects;
                    asyncResponse.onSubjectsDataReceivedSuccess(subjectsList);
                    break;
                case DiffTypeNumber.KOUBEI:
                    break;
                case DiffTypeNumber.USBOX:
                    UsBox usBox = gson.fromJson(s, UsBox.class);
                    for (UsBox.Subject sub : usBox.subjects) {
                        subjectsList.add(sub.subject);
                    }
                    asyncResponse.onSubjectsDataReceivedSuccess(subjectsList);
                    break;
                case DiffTypeNumber.BOOKTAG:
                    Book book = gson.fromJson(s, Book.class);
                    asyncResponse.onBookDataReceivedSuccess(book);
                    break;
                case DiffTypeNumber.NEWMOVIE:
                    break;
                case DiffTypeNumber.MOVIEDETAIL:
                    Movie movie = gson.fromJson(s, Movie.class);
                    asyncResponse.onMovieDataReceivedSuccess(movie);
                    break;
                default:
            }
        } else {
            asyncResponse.onDataReceivedFailed();
        }
    }

    public void setOnAsyncResponse(AsyncResponse response) {
        asyncResponse = response;
    }

    public interface AsyncResponse {

        void onSubjectsDataReceivedSuccess(List<Subjects> subjects);

        void onMovieDataReceivedSuccess(Movie movie);

        void onBookDataReceivedSuccess(Book book);

        void onDataReceivedFailed();
    }
}
