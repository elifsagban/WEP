package com.elif.wep;

import android.renderscript.RenderScript;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskItem implements Serializable {

    private String title;
    private String description;
    private String date;
    private String id;

    private int seconds;
    private boolean done;
    ArrayList<Integer> breaks;
    ArrayList<Integer> duration;


    public TaskItem() {

    }

    public TaskItem(String title, String description, String date, String id, int seconds, boolean done,
                    ArrayList<Integer> breaks, ArrayList<Integer> duration) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.id = id;
        this.seconds = seconds;
        this.done = done;
        this.breaks = breaks;
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

    public ArrayList<Integer> getBreaks() {
        return breaks;
    }

    public void setBreaks(ArrayList<Integer> breaks) {
        this.breaks = breaks;
    }

    public ArrayList<Integer> getDuration() {
        return duration;
    }

    public void setDuration(ArrayList<Integer> duration) {
        this.duration = duration;
    }

    public int countBreaks() {
        int breaks = 0;

        if (getBreaks() != null) {
            for (int i = 0; i < getBreaks().size(); i++) {
                breaks++;
            }
        }

        return breaks;
    }

    public int totalDuration() {
        int totalDuration = 0;

        if (getDuration() != null) {

            for (int i = 0; i < getDuration().size(); i++) {
                totalDuration += getDuration().get(i);

            }
        }

        return totalDuration;
    }
    
}
