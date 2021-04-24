package com.elif.wep;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {


    FirebaseAuth mAuth;
    EditText emailText, passwordText;
    Button loginBtn, signupBtn;
    TextView forgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.SignupBtn);
        forgotPassword = findViewById(R.id.forgotPassword);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.sendPasswordResetEmail(emailText.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                                Toast.makeText(Login.this, "Email Sent", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(Login.this, MainActivity.class);
                startActivity(signupIntent);
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Enter every details", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Login.this, "Log in Successful", Toast.LENGTH_SHORT).show();
                                        Intent dashboardIntent = new Intent(Login.this, MainActivity.class);
                                        startActivity(dashboardIntent);
                                    } else {
                                        Toast.makeText(Login.this, "Please enter correct details", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });
    }
}