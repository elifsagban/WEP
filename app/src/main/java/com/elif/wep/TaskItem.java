package com.elif.wep;



public class TaskItem {

    private String title;
    private String description;
    private String date;
    private String id;
    private int duration;
    private int seconds = 0;


    public TaskItem() {

    }

    public TaskItem(String title, String description, String date, String id, int duration) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.id = id;
        this.duration = duration;


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
