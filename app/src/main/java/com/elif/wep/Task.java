package com.elif.wep;
;

public class Task {

    private String title;
    private String taskList;

    public Task(String title, String taskList) {
        this.title = title;
        this.taskList = taskList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getTaskList() {
        return taskList;
    }

    public void setTaskList(String taskList) {
        this.taskList = taskList;
    }
}
