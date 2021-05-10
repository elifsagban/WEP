package com.elif.wep;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


class Chronometer extends AppCompatActivity {

    ArrayList<Integer> avg_duration = new ArrayList();
    ArrayList<Integer> items_duration = new ArrayList();
    ArrayList<Integer> items_break = new ArrayList();
    private int seconds = 0;
    private Timestamp start_time_break;
    private Timestamp break_time;
    private Timestamp start_time_seconds;
    private Timestamp duration_time;
    private boolean running;
    private boolean wasRunning;
    Schedule schedule;

    public ArrayList getItemsDuration() {
        return avg_duration;
    }

    public Timestamp getStart_time_seconds() {
        return start_time_seconds;
    }

    public void setStart_time_seconds(Timestamp start_time_seconds) {
        this.start_time_seconds = start_time_seconds;
    }

    public Timestamp getDuration_time() {
        return duration_time;
    }

    public void setDuration_time(Timestamp duration_time) {
        this.duration_time = duration_time;
    }

    public ArrayList getItemsBreak() {
        return items_break;
    }

    public Timestamp getStart_time_break() {
        return start_time_seconds;
    }

    public void setStart_time_break(Timestamp start_time_break) {
        this.start_time_break = start_time_break;
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


    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }


    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }


    public void onClickStart() {
        running = true;
        Timestamp startSeconds = new Timestamp(System.currentTimeMillis());
        setStart_time_seconds(startSeconds);
        System.out.println("start seconds" + getStart_time_seconds());

        if (seconds > 0) {
            Timestamp startBreak = new Timestamp(System.currentTimeMillis());
            setStart_time_break(startBreak);
            System.out.println("start breaks" + getStart_time_break());
            calculateBreakTime();


        }

    }


    public void onClickStop() {
        running = false;
        Timestamp duration_ts = new Timestamp(System.currentTimeMillis());
        setDuration_time(duration_ts);
        System.out.println("duration timestamp " + duration_ts);

        calculateDuration();
        if (seconds > 0) {
            Timestamp b_timestamp = new Timestamp(System.currentTimeMillis());
            setBreak_time(b_timestamp);
            System.out.println("break " + b_timestamp);
        }

    }

    void calculateBreakTime() {
        if (duration_time != null && start_time_seconds != null) {
            int diff = (int) (getStart_time_break().getTime() - getBreak_time().getTime());
            items_break.add(diff);
            System.out.println("break time result " + diff);


        }
    }

    public void calculateDuration() {
        if (duration_time != null && start_time_seconds != null) {
            int duration = (int) (getDuration_time().getTime() - getStart_time_seconds().getTime());
            items_duration.add(duration);
            System.out.println("duration time result " + duration);


        }

    }

    public void calculateAvgDuration() {
        int total = 0;
        int avg = 0;
        for (int i = 0; i < items_duration.size(); i++) {
            total = total + (int) items_duration.get(i);
            avg = total / items_duration.size();

        }
        System.out.println("The Average IS:" + avg);
        avg_duration.add(avg);

    }

    // Reset the stopwatch when
    // the Reset button is clicked.
    // Below method gets called
    // when the Reset button is clicked.
    public void onClickSave() {
        // running = false;
        //  seconds = 0;
        System.out.println(items_duration);
        calculateAvgDuration();
        System.out.println(avg_duration);
        System.out.println(break_time);
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
    protected void runTimer(TextView timeView, Boolean value) {


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

                    if (value){
                        if((((seconds % 3600) / 60) % 25 == 0)){
                            onClickStop();
                        }
                    }

                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }
}