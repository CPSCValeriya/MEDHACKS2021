package com.example.medhacks2021;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class LogInActivity extends AppCompatActivity {

    private static final String TAG = "TEST: ";
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();

        Button btn = findViewById(R.id.loginscreen_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userName = (EditText) findViewById(R.id.entername_txt);
                String userNameText = userName.getText().toString();
                userManager = userManager.getInstance();
                userManager.addUsers(new User(userNameText, 10));
                userManager.toString();
                Intent intent = new Intent(LogInActivity.this, LogInSuccessfulActivity.class);
                startActivity(intent);

            }
        });

    }


}