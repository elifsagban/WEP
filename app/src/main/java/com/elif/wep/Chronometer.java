package com.elif.wep;

import android.os.Handler;
import android.view.View;

import android.widget.TextView;


import java.util.Locale;
import java.util.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;

class Chronometer {

    private int seconds = 0;
    private Timestamp start_time;
    private Timestamp break_time;
    private boolean running;
    private boolean wasRunning;


    ArrayList items = new ArrayList();


    public ArrayList getItems() {
        return items;
    }



    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getBreak_time() {
        return break_time;
    }

    public void setBreak_time(Timestamp break_time) {
        this.break_time = break_time;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isWasRunning() {
        return wasRunning;
    }

    public void setWasRunning(boolean wasRunning) {
        this.wasRunning = wasRunning;
    }



    protected void onPause()
    {
        onPause();
        wasRunning = running;
        running = false;
    }


    protected void onResume()
    {
        onResume();
        if (wasRunning) {
            running = true;
        }
    }


    public void onClickStart()
    {
        running = true;

        if(seconds > 0) {
            Timestamp start = new Timestamp(System.currentTimeMillis());
            setStart_time(start);
            System.out.println("start " + getStart_time());

            long diff = getStart_time().getTime() -  getBreak_time().getTime();

            ArrayList item =  new ArrayList();
            item.add(getStart_time().getTime());
            item.add(getBreak_time().getTime());
            item.add(diff);

            items.add(item);
            System.out.println("break time result " + diff);

        }

    }


    public void onClickStop()
    {
        running = false;

        if(seconds > 0) {
            Timestamp b_timestamp = new Timestamp(System.currentTimeMillis());
            setBreak_time(b_timestamp);
            System.out.println("break " + b_timestamp);
        }

    }

     void calculateBreakTime() {

        if(break_time != null && start_time != null) {

            long diff = getStart_time().getTime() -  getBreak_time().getTime();
            System.out.println("break time result " + diff);

        }
    }

    // Reset the stopwatch when
    // the Reset button is clicked.
    // Below method gets called
    // when the Reset button is clicked.
    public void onClickSave()
    {
       // running = false;
      //  seconds = 0;

        long retryDate = System.currentTimeMillis();

        int sec = seconds;

        Timestamp original = new Timestamp(retryDate);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(original.getTime());
        cal.add(Calendar.SECOND, sec);
        Timestamp later = new Timestamp(cal.getTime().getTime());

        System.out.println(original);
        System.out.println(later);
        running = false;
    }

    // Sets the NUmber of seconds on the timer.
    // The runTimer() method uses a Handler
    // to increment the seconds and
    // update the text view.
    protected void runTimer(TextView timeView) {

        // Get the text view.
       // final TextView timeView = (TextView) findViewById(R.id.time_view);

        // Creates a new Handler
        final Handler handler = new Handler();

        // Call the post() method,
        // passing in a new Runnable.
        // The post() method processes
        // code without a delay,
        // so the code in the Runnable
        // will run almost immediately.
        handler.post(new Runnable() {
            @Override

            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

                // Set the text view text.
                timeView.setText(time);

                // If running is true, increment the
                // seconds variable.
                if (running) {
                    seconds++;
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }
}
