package com.elif.wep;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText nameText, emailText, passwordText;
    Button registerBtn, loginBtn;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView navigation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        nameText = findViewById(R.id.name);
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        registerBtn = findViewById(R.id.register);
        loginBtn = (Button) findViewById(R.id.login);


        drawerNav();


        // change menu if user logged-in

        if (mAuth.getCurrentUser() != null) {
            navigation.getMenu().clear();
            navigation.inflateMenu(R.menu.menu_user); //inflate new items.
        }

        if(mAuth.getCurrentUser() == null) {
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



        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openLoginPage();

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Fill out each cell!", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(Register.this, "Please enter a password more than 6 characters", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(Register.this, "Registered successfully!", Toast.LENGTH_SHORT).show();


                            } else {
                                Toast.makeText(Register.this, "The user is not registered!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    public void logout() {

        AlertDialog alertDialog = new AlertDialog.Builder(Register.this).create();
        alertDialog.setTitle("WEP");
        alertDialog.setMessage("Do you want to sign out?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.signOut();
                    }
                });
        alertDialog.show();

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


    public void openLoginPage() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}