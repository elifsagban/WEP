package com.elif.wep;
import java.util.Calendar;

public class TaskChild {

    protected String taskItem;
    protected String priority;
    protected int currDay;
    protected int currMonth;
    protected int currYear;
    protected Calendar calendar;
    protected Boolean isDone;

    public TaskChild(String taskItem, String priority) {
        this.taskItem = taskItem;
        this.priority = priority;
        calendar = Calendar.getInstance();
        this.currDay = getCurrDay();
        this.currMonth = getCurrMonth();
        this.currYear = getCurrYear();
        this.isDone = false;
    }


    public String getTaskItem() {
        return taskItem;
    }

    public void setTaskItem(String taskItem) {
        this.taskItem = taskItem;
    }

    public int getCurrDay() {
        currDay = calendar.get(Calendar.DATE);
        return currDay;
    }

    public int getCurrMonth() {
        currMonth = calendar.get(Calendar.MONTH);

        return currMonth;
    }

    public int getCurrYear() {
        currYear = calendar.get(Calendar.YEAR);

        return currYear;
    }


}
