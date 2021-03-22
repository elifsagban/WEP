package com.elif.wep;
import java.util.ArrayList;



public class Task {

    private String title;
    private ArrayList<TaskChild> childArrayList;
    private SubItemController subItemController;



    public Task(String title, ArrayList<TaskChild> childArrayList ) {
        this.title = title;
        this.childArrayList = childArrayList;
        this.subItemController = new SubItemController();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setChildArrayList(ArrayList<TaskChild> childArrayList) {
        this.childArrayList = childArrayList;
    }

    public ArrayList<TaskChild> getChildArrayList() {
        return subItemController.getListOfItems();
    }

    public void addTaskChild(String taskName, String priority) {
        subItemController.addSubItem(taskName, priority);
    }

}
