package com.example.medhacks2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private UserManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        userManager = UserManager.getInstance();

        Button login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                    intent.putExtra("Key","Login");
                    startActivity(intent);

            }
        });

        Button signup_btn = findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                    intent.putExtra("Key","Signup");
                    startActivity(intent);
            }
        });
        //initial commit
    }
}