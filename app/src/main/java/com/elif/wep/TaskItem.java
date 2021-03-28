package com.elif.wep;
import java.util.ArrayList;


public class TaskItem {

    private String title;
    private String description;
    private String date;
    private String id;


    public TaskItem() {

    }

    public TaskItem(String title, String description, String date, String id) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.id = id;


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

}
