package com.example.medhacks2021;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
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
        User mainUser = userManager.getUsers().get(0);

        TextView userName = findViewById(R.id.homepagename_txt);
        userName.setText(mainUser.getName());
        TextView points = findViewById(R.id.points_txt);
        points.setText(Long.toString(mainUser.getPoints()));

        Button contactsBtn = findViewById(R.id.contacts_btn);
        contactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

    }
}