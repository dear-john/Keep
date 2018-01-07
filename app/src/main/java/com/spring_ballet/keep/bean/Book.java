package com.spring_ballet.keep.bean;


import android.os.Parcel;
import android.os.Parcelable;

import com.spring_ballet.keep.base.BaseClass;
import com.spring_ballet.keep.bean.BookBean.Books;

import java.util.List;

public class Book extends BaseClass implements Parcelable{
    private List<Books> books;

    protected Book(Parcel in) {
        books = in.createTypedArrayList(Books.CREATOR);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(books);
    }
}
