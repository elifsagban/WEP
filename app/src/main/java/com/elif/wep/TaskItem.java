package com.elif.wep;

import java.io.Serializable;

public class TaskItem implements Serializable {

    private String title;
    private String description;
    private String date;
    private String id;

    private int seconds;
    private boolean done;


    public TaskItem() {

    }

    public TaskItem(String title, String description, String date, String id, int seconds, boolean done) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.id = id;
        this.seconds = seconds;
        this.done = done;


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
