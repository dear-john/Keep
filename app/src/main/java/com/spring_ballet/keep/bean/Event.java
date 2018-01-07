package com.spring_ballet.keep.bean;

public class Event {
    private final boolean loadFinish;

    public Event(boolean loadFinish) {
        this.loadFinish = loadFinish;
    }

    public boolean isLoadFinish() {
        return loadFinish;
    }
}
