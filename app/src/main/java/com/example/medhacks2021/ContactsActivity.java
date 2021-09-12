package com.example.medhacks2021;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private ArrayAdapter<User> user;
    private ListView lstNames;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private List<String> contacts;
    private ArrayAdapter<User> adapter;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        getSupportActionBar().hide();

        userManager = UserManager.getInstance();

        this.lstNames = (ListView) findViewById(R.id.contacts_list);
        showContacts();
        clickContact();

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.print(lstNames.getAdapter().getCount());
    }

    private void showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            contacts = getContactNames();
            adapter = new ContactsActivity.contactAdapter();
            lstNames.setAdapter(adapter);
        }
    }


    private class contactAdapter extends ArrayAdapter<User>{

        public contactAdapter(){
            super(ContactsActivity.this, R.layout.contact_element, userManager.getUsers());
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View item = convertView;
            if(item == null){
                LayoutInflater inflater = ContactsActivity.this.getLayoutInflater();
                item = inflater.inflate(R.layout.contact_element,parent,false);
            }
            TextView name = (TextView) item.findViewById(R.id.contact_txt);
            name.setText(userManager.getCurrentUser().getContacts().get(position).getName());
            TextView pts = (TextView) item.findViewById(R.id.pointsTracker_txt);
            pts.setText(Long.toString(userManager.getCurrentUser().getContacts().get(position).getPoints()));
            return item;
        }
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we cannot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<String> getContactNames() {
        List<String> contacts = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contacts.add(name);
                userManager.addContact(new Contact(name, 0, "6043339622"));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contacts;
    }

    public void clickContact(){
        ListView list = findViewById(R.id.contacts_list);
        list.setOnItemClickListener((adapter, view, position, id)->{
            Intent intent = new Intent(ContactsActivity.this, ChatActivity.class);
            startActivity(intent);
        });
    }



}