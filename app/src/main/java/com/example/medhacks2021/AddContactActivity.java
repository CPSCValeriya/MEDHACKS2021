package com.example.medhacks2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        getSupportActionBar().hide();

        Button addContact = findViewById(R.id.submit_addcontact_btn);
        UserManager userManager = UserManager.getInstance();

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText addCode = findViewById(R.id.entercode_txt);
                String relCode = addCode.getText().toString();

                System.out.print("printing relCode: " + relCode);

                if(userManager.getUserFromPassword(relCode)!=null){
                    userManager.getCurrentUser().getContacts().add(new Contact(userManager.getUserFromPassword(relCode).getName(), (int) userManager.getUserFromPassword(relCode).getPoints(),userManager.getUserFromPassword(relCode).getPhoneNumber()));
                    AddContactActivity.super.onBackPressed();
                }else{
                    Toast.makeText(AddContactActivity.this, "INCORRECT USER CODE",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}