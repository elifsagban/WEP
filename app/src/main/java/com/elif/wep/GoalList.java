package com.elif.wep;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class GoalList extends AppCompatActivity {

    private Button addGoal;
    private RecyclerView recyclerView;
    private String userGoalName;
    private final ArrayList<Goal> goals = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    GoalRecylerViewAdapter goalRecylerViewAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        addGoal = findViewById(R.id.addGoalList);
        recyclerView = findViewById(R.id.recycleViewGoal);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        goalRecylerViewAdapter = new GoalRecylerViewAdapter(goals);
        recyclerView.setAdapter(goalRecylerViewAdapter);



        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder goalNameDialog = new AlertDialog.Builder(GoalList.this);
                goalNameDialog.setTitle("Enter Goal Name");

                final EditText goalName = new EditText(GoalList.this);

                goalName.setInputType(InputType.TYPE_CLASS_TEXT);
                goalNameDialog.setView(goalName);

                goalNameDialog.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userGoalName = goalName.getText().toString();
                        Toast.makeText(GoalList.this, userGoalName + " is created", Toast.LENGTH_LONG).show();
                        Goal goal = new Goal(userGoalName);
                        goals.add(goal);



                    }
                });

                goalNameDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                goalNameDialog.show();
            }


        });


    }
}