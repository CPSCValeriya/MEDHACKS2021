package com.example.medhacks2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    private UserManager userManager;
    private User mainUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();

        userManager = UserManager.getInstance();
        mainUser = userManager.getCurrentUser();

        TextView userName = findViewById(R.id.homepagename_txt);
        userName.setText(mainUser.getName());
        TextView points = findViewById(R.id.points_txt);
        points.setText(Long.toString(mainUser.getPoints()));
        TextView userID = findViewById(R.id.user_id_txt);
        userID.setText(mainUser.getPassword());

        Button contactsBtn = findViewById(R.id.contacts_btn);
        contactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

        Button watchBtn = findViewById(R.id.watch_btn);
        watchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("current user: " + userManager.getCurrentUser().getName() + " with " + userManager.getCurrentUser().getPoints() + "points");
                System.out.println("test add: " + userManager.getCurrentUser().getPoints()+10);
                userManager.getCurrentUser().setPoints(userManager.getCurrentUser().getPoints()+10);
                System.out.println("set: " + userManager.getCurrentUser().getName() + " with " + userManager.getCurrentUser().getPoints() + "points");

                Intent intent = new Intent(HomePageActivity.this, WatchActivity.class);
                startActivity(intent);

            }
        });

        Button readBtn = findViewById(R.id.read_btn);
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("current user: " + userManager.getCurrentUser().getName() + " with " + userManager.getCurrentUser().getPoints() + "points");
                System.out.println("test add: " + userManager.getCurrentUser().getPoints()+10);
                userManager.getCurrentUser().setPoints(userManager.getCurrentUser().getPoints()+10);
                System.out.println("set: " + userManager.getCurrentUser().getName() + " with " + userManager.getCurrentUser().getPoints() + "points");

                Intent intent = new Intent(HomePageActivity.this, ReadActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView points = findViewById(R.id.points_txt);
        points.setText(Long.toString(mainUser.getPoints()));
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent i= new Intent(HomePageActivity.this, MainActivity.class);
        startActivity(i);
        finish();

    }
}