package com.example.medhacks2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "TEST: ";
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        // new user

        Button btn = findViewById(R.id.signupscreen_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userName = (EditText) findViewById(R.id.entername_txt);
                String userNameText = userName.getText().toString();
                userManager = userManager.getInstance();
                User currentUser = new User(userNameText, 10,"6045553333");
                userManager.addUsers(currentUser);
                userManager.setCurrentUser(currentUser);
                userManager.getUniquePasswords().add(userManager.getCurrentPassword());
                userManager.toString();

                System.out.print("sign up CURRENT USER: " + userManager.getCurrentUser().getName());
                System.out.print("sign up CURRENT USER PASS: " + userManager.getCurrentUser().getPassword());

                Intent intent = new Intent(SignUpActivity.this, SignUpSuccessfulActivity.class);
                startActivity(intent);

            }
        });


    }


}