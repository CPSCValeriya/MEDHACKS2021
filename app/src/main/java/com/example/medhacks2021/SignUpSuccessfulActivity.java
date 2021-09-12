package com.example.medhacks2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUpSuccessfulActivity extends AppCompatActivity {

    UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_in_successful);
        getSupportActionBar().hide();
        userManager = UserManager.getInstance();

        TextView password = findViewById(R.id.show_password);
        password.setText(userManager.getCurrentUser().getPassword());

        Button btn = findViewById(R.id.login_screen);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpSuccessfulActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });


    }
}