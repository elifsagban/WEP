package com.elif.wep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity {
    private ImageButton statPage;
    private ImageButton planPage;
    private ImageButton homePage;
    private ImageButton taskPage;
    private ImageButton profilePage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

}