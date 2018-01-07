package com.spring_ballet.keep.bean.BookBean;


import android.os.Parcel;
import android.os.Parcelable;

import com.spring_ballet.keep.base.BaseClass;
import com.spring_ballet.keep.bean.MovieBean.Images;

import java.util.List;

public class Books extends BaseClass implements Parcelable{
    private List<String> author;
    private Rating rating;
    private String pubdate;
    private List<Tag> tags;
    private String origin_title;
    private String image;
    private List<String> translator;
    private Images images;
    private String alt;
    private int pages;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String alt_title;
    private String author_intro;
    private String summary;

    private Books(Parcel in) {
        author = in.createStringArrayList();
        rating = in.readParcelable(Rating.class.getClassLoader());
        pubdate = in.readString();
        tags = in.createTypedArrayList(Tag.CREATOR);
        origin_title = in.readString();
        image = in.readString();
        translator = in.createStringArrayList();
        images = in.readParcelable(Images.class.getClassLoader());
        alt = in.readString();
        pages = in.readInt();
        publisher = in.readString();
        isbn10 = in.readString();
        isbn13 = in.readString();
        title = in.readString();
        alt_title = in.readString();
        author_intro = in.readString();
        summary = in.readString();
    }

    public static final Creator<Books> CREATOR = new Creator<Books>() {
        @Override
        public Books createFromParcel(Parcel in) {
            return new Books(in);
        }

        @Override
        public Books[] newArray(int size) {
            return new Books[size];
        }
    };

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
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
        parcel.writeStringList(author);
        parcel.writeParcelable(rating, i);
        parcel.writeString(pubdate);
        parcel.writeTypedList(tags);
        parcel.writeString(origin_title);
        parcel.writeString(image);
        parcel.writeStringList(translator);
        parcel.writeParcelable(images, i);
        parcel.writeString(alt);
        parcel.writeInt(pages);
        parcel.writeString(publisher);
        parcel.writeString(isbn10);
        parcel.writeString(isbn13);
        parcel.writeString(title);
        parcel.writeString(alt_title);
        parcel.writeString(author_intro);
        parcel.writeString(summary);
    }
}
