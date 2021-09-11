package com.example.medhacks2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        getSupportActionBar().hide();

        userManager = UserManager.getInstance();

        // login
        Button btn = findViewById(R.id.loginscreen_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText password = (EditText) findViewById(R.id.password_txt);
                String passwordText = password.getText().toString();
                userManager = userManager.getInstance();
                User user = userManager.getUserFromPassword(passwordText);
                if(user==null){
                    Toast.makeText(LogInActivity.this, "INCORRECT LOGIN",Toast.LENGTH_LONG).show();
                }else{
                    userManager.setCurrentUser(user);
                    Intent intent = new Intent(LogInActivity.this, HomePageActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}