package com.elif.wep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Statistics extends AppCompatActivity {
    private ImageButton statPage;
    private ImageButton planPage;
    private ImageButton homePage;
    private ImageButton taskPage;
    private ImageButton profilePage;
    TaskItem taskItem;
    TextView statistic1, statistic2, statistic3, statistic4;
    DatabaseReference reff;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        statistic1 = (TextView) findViewById(R.id.statistic1);
        statistic2 = (TextView) findViewById(R.id.statistic2);
        statistic3 = (TextView) findViewById(R.id.statistic3);
        statistic4 = (TextView) findViewById(R.id.statistic4);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        reff = FirebaseDatabase.getInstance().getReference().child("tasks").child("vp8ntKbAP0VNuvAoxvcwGEinnn62").child("MYvI1bmHPaylq7F9sxE");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String title =  snapshot.child("title").getValue(String.class);
                Integer seconds =  snapshot.child("seconds").getValue(Integer.class);
                Integer breaks =  snapshot.child("breaks").child("0").getValue(Integer.class);
                Integer duration =  snapshot.child("duration").child("0").getValue(Integer.class);
                statistic1.setText(title);
                statistic2.setText(seconds);
                statistic3.setText(breaks);
                statistic4.setText(duration);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}