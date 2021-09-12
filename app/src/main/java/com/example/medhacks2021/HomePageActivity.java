package com.example.medhacks2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();

        userManager = UserManager.getInstance();
        User mainUser = userManager.getCurrentUser();

        TextView userName = findViewById(R.id.homepagename_txt);
        userName.setText(mainUser.getName());
        TextView points = findViewById(R.id.points_txt);
        points.setText(Long.toString(mainUser.getPoints()));

        System.out.println("CURRENT USER: " + userManager.getCurrentUser().getName());
        System.out.println("CURRENT USER PASS: " + userManager.getCurrentUser().getPassword());
        Button contactsBtn = findViewById(R.id.contacts_btn);
        contactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent i= new Intent(HomePageActivity.this, MainActivity.class);
        startActivity(i);
        finish();

    }
}