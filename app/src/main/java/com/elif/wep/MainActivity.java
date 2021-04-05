package com.elif.wep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ImageButton statPage;
    private ImageButton planPage;
    private ImageButton homePage;
    private ImageButton taskPage;
    private ImageButton profilePage;

    private Button registerBtn;

    private int seconds = 0;

    // Is the stopwatch running?
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    // Save the state of the stopwatch
    // if it's about to be destroyed.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }

    private void runTimer()
        {

            // Get the text view.
            final TextView timeView = (TextView)findViewById(R.id.time_view);

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

                public void run()
                {
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


        registerBtn = (Button) findViewById(R.id.mainRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                openRegisterPage();

            }
        });
        statPage = (ImageButton) findViewById(R.id.statPage);
        statPage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                openStatsPage();

            }
        });
        planPage = (ImageButton) findViewById(R.id.planPage);
        planPage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                openPlanPage();

            }
        });
        homePage = (ImageButton) findViewById(R.id.homePage);
        homePage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                openHomePage();

            }
        });
        taskPage = (ImageButton) findViewById(R.id.taskPage);
        taskPage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                openTaskPage();

            }
        });
        profilePage = (ImageButton) findViewById(R.id.profilePage);
        profilePage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                openProfilePage();

            }
        });
    }
    public void openStatsPage() {
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
    }
    private void openPlanPage() {
        Intent intent = new Intent(this, Schedule.class);
        startActivity(intent);
    }
    private void openHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void openTaskPage() {
        Intent intent = new Intent(this, TaskList.class);
        startActivity(intent);
    }
    private void openProfilePage() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
    private void openRegisterPage() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }



}