package com.elif.wep;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

public class Statistics extends AppCompatActivity {

    FirebaseAuth fAuth;
    DatabaseReference db;
    String userID;
    RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerViewStatistics;
    private statisticsAdapter statisticsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        fAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance().getReference().child("tasks").child(userID);

        recyclerViewStatistics = findViewById(R.id.statisticsRecyclerView);
        recyclerViewStatistics.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 1);


        Query taskQuery = db.orderByChild("done").equalTo(true);


        recyclerViewStatistics.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<TaskItem> options = new FirebaseRecyclerOptions.Builder<TaskItem>()
                .setQuery(taskQuery, TaskItem.class)
                .build();

        statisticsAdapter = new statisticsAdapter(options);
        recyclerViewStatistics.setAdapter(statisticsAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        statisticsAdapter.startListening();
    }


}