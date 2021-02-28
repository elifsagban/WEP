package com.elif.wep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button regButton;
    private Button taskButton;
    private Button goalButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regButton = (Button) findViewById(R.id.mainRegister);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterPage();
            }
        });

        taskButton = findViewById(R.id.mainTask);
        taskButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openTaskPage();
            }
        });

        goalButton = findViewById(R.id.mainGoal);
        goalButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openGoalPage();
            }
        });



    }

    private void openGoalPage() {
        Intent intent = new Intent(this, GoalList.class);
        startActivity(intent);
    }

    private void openTaskPage() {
        Intent intent = new Intent(this, TaskList.class);
        startActivity(intent);
    }

    private void openRegisterPage() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}