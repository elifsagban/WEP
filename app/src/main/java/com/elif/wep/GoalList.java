package com.elif.wep;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class GoalList extends AppCompatActivity {

    goalAdapter goalAdapter;
    FirebaseAuth fAuth;
    DatabaseReference db;
    String userID;
    private Button addGoal;
    private RecyclerView recyclerView;
    private String userGoalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        addGoal = findViewById(R.id.addGoalList);
        recyclerView = findViewById(R.id.recycleViewGoal);

        fAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance().getReference().child("goals").child(userID);


        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Goal> options = new FirebaseRecyclerOptions.Builder<Goal>()
                .setQuery(db, Goal.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        goalAdapter = new goalAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(goalAdapter);


        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder goalNameDialog = new AlertDialog.Builder(GoalList.this);
                goalNameDialog.setTitle("Enter Goal Name");

                final EditText goalName = new EditText(GoalList.this);
                String id = db.push().getKey();

                goalName.setInputType(InputType.TYPE_CLASS_TEXT);
                goalNameDialog.setView(goalName);

                goalNameDialog.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userGoalName = goalName.getText().toString();
                        Toast.makeText(GoalList.this, userGoalName + " is created", Toast.LENGTH_LONG).show();
                        Goal goal = new Goal(userGoalName);
                        //   goals.add(goal);

                        db.child(id).setValue(goal).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(GoalList.this, "succesfully added", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


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

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    protected void onStart() {
        super.onStart();
        goalAdapter.startListening();
    }
}