package com.elif.wep;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView navigation;

    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

       navigationMenu();

        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigation = (NavigationView)  findViewById(R.id.navigation_menu);
       // navigationMenu.getMenu().clear();
       // navigationMenu.inflateMenu(R.menu.menu_user); //inflate new items.

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.nav_login:
                        Toast.makeText(Profile.this, "Settings",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext()
                                , Login.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_register:
                            startActivity(new Intent(getApplicationContext()
                                    , Register.class));
                            overridePendingTransition(0, 0);
                            return true;
                    default:
                        return true;
                }

            }
        });





        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //        registerBtn = (Button) findViewById(R.id.mainRegister);
//        registerBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                openRegisterPage();
//
//            }
//        });

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void navigationMenu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.profile_item);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private MenuItem item;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                this.item = item;
                switch (item.getItemId()) {
                    case R.id.home_item:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);
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
                    case R.id.profile_item:
                        return true;

                }
                return false;
            }
        });
    }

    private void openRegisterPage() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }


}