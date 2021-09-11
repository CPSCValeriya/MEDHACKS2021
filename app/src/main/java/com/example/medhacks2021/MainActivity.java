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

        Button btn = findViewById(R.id.login_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userManager.getUsers().size() == 0){
                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    startActivity(intent);
                }

            }
        });
        //initial commit
    }
}