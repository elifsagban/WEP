package com.elif.wep;
import java.util.ArrayList;


public class SubItemController {
    private ArrayList<TaskChild> exampleList;
    private ArrayList<TaskChild> initialList;
    private static SubItemController controller;

    public SubItemController(){
        initMovieList();
    }

    private ArrayList<TaskChild> initMovieList(){
        if (this.exampleList == null) {
            this.exampleList = new ArrayList<>();
          //  exampleList.add(new TaskChild("inner-one"));
        }
        return this.exampleList;
    }

    public void addSubItem(String taskName) {
        exampleList.add(new TaskChild(taskName));
    }

    public ArrayList<TaskChild> buildInitialList() {
        initialList = new ArrayList<TaskChild>();
        initialList.add(new TaskChild(""));
        return initialList;
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