package com.elif.wep;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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

    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fAuth = FirebaseAuth.getInstance();

        drawerNav();


        // change menu if user logged-in

        if (fAuth.getCurrentUser() != null) {
            navigation.getMenu().clear();
            navigation.inflateMenu(R.menu.menu_user); //inflate new items.
        }

        if(fAuth.getCurrentUser() == null) {
            navigation.getMenu().clear();
            navigation.inflateMenu(R.menu.menu); //inflate new items.
        }


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.nav_login:
                        startActivity(new Intent(getApplicationContext()
                                , Login.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_register:
                            startActivity(new Intent(getApplicationContext()
                                    , Register.class));
                            overridePendingTransition(0, 0);
                            return true;
                    case R.id.nav_account:
                        startActivity(new Intent(getApplicationContext()
                                , Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_logout:
                        logout();
                        return true;
                    default:
                        return true;
                }

            }
        });




    }

    public void logout() {

        AlertDialog alertDialog = new AlertDialog.Builder(Profile.this).create();
        alertDialog.setTitle("WEP");
        alertDialog.setMessage("Do you want to sign out?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        fAuth.signOut();
                        Toast.makeText(Profile.this, "You logged out.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);

                    }
                });
        alertDialog.show();

    }


    // toggle button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void drawerNav() {

        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigation = (NavigationView)  findViewById(R.id.navigation_menu);
        navigation.bringToFront();



        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}