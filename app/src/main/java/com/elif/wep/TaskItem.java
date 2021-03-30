package com.elif.wep;



public class TaskItem {

    private String title;
    private String description;
    private String date;
    private String id;
<<<<<<< HEAD
    private int duration;
    private int seconds = 0;
=======
    private Chronometer chronometer;
>>>>>>> 2a393171e567686a1a5f01b38fa66da5967ac23e


    public TaskItem() {

    }

<<<<<<< HEAD
    public TaskItem(String title, String description, String date, String id, int duration) {
=======
    public TaskItem(String title, String description, String date, Chronometer chronometer, String id) {
>>>>>>> 2a393171e567686a1a5f01b38fa66da5967ac23e
        this.title = title;
        this.description = description;
        this.date = date;
        this.chronometer = chronometer;
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
