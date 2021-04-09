package com.elif.wep;
import java.io.Serializable;
import java.util.ArrayList;

public class TaskItem implements Serializable {

    private String title;
    private String description;
    private String date;
    private String id;
    private boolean isDone;
    private ArrayList breaks = new ArrayList();


    public TaskItem() {

    }

    public TaskItem(String title, String description, String date ,String id, boolean isDone, ArrayList breaks) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.id = id;
        this.isDone = isDone;
        this.breaks = breaks;

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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }



}
