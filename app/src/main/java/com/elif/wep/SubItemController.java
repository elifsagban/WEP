package com.elif.wep;
import java.util.ArrayList;


public class SubItemController {
    private ArrayList<TaskChild> exampleList;
    private static SubItemController controller;

    public SubItemController(){
        initMovieList();
    }

    private ArrayList<TaskChild> initMovieList(){
        if (this.exampleList == null) {
            this.exampleList = new ArrayList<>();
        }
        return this.exampleList;
    }

    public void addSubItem(String taskName, String priority) {
        exampleList.add(new TaskChild(taskName, priority));
    }


    public ArrayList<TaskChild> getListOfItems() {
        return exampleList;
    }

    public static SubItemController getController(){
        if(controller == null){
            controller = new SubItemController();
        }
        return controller;
    }

}