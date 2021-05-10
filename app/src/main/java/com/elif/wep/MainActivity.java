package com.elif.wep;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button registerBtn;
    private Button LogoutBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home_item);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private MenuItem item;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                this.item = item;
                switch (item.getItemId()) {
                    case R.id.home_item:
                        return true;
                    case R.id.task_item:
                        startActivity(new Intent(getApplicationContext()
                                , TaskList.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.plan_item:
                        startActivity(new Intent(getApplicationContext()
                                , Schedule.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.stat_item:
                        startActivity(new Intent(getApplicationContext()
                                , Statistics.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });


        registerBtn = (Button) findViewById(R.id.mainRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                openRegisterPage();

            }
        });

        mAuth = FirebaseAuth.getInstance();


//        LogoutBtn = (Button) findViewById(R.id.mainLogout);
//        LogoutBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                mAuth.signOut();
//                System.out.println("user sign-out");
//
//            }
//        });


    }



    private void openRegisterPage() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}