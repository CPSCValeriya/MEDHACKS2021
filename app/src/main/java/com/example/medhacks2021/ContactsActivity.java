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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private ListView lstNames;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private List<String> contacts;

    private ArrayAdapter<Contact> adapter;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        getSupportActionBar().hide();

        lstNames = (ListView) findViewById(R.id.contacts_list);
        userManager = UserManager.getInstance();
        contacts = new ArrayList();

        Button addContact = findViewById(R.id.addcontact_btn);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity.this, AddContactActivity.class);
                startActivity(intent);
                adapter.notifyDataSetChanged();
            }
        });

        showContacts();
        clickContact();

    }

    @Override
    protected void onResume() {
        super.onResume();
        populateContactList();
    }

    private void populateContactList() {
        adapter = new ContactsActivity.contactAdapter();
        lstNames.setAdapter(adapter);
    }

    private void showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {

            contacts = getContactNames();
            if( userManager.getCurrentUser().getContacts().size() > contacts.size()){
                for(int i = 0 ; i < (userManager.getCurrentUser().getContacts().size()-contacts.size()); i++){
                    System.out.println("ADD CONTACTS MANUALLY " + i);
                    contacts.add(userManager.getCurrentUser().getContacts().get(i).getName());
                }
            }

            adapter = new ContactsActivity.contactAdapter();
            lstNames.setAdapter(adapter);
        }
    }

    private class contactAdapter extends ArrayAdapter<Contact>{

        public contactAdapter(){
            super(ContactsActivity.this, R.layout.contact_element, userManager.getCurrentUser().getContacts());
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(contacts.size() == 0){
                showContacts();
            }

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
        if(contacts.size() == 0){
            ContentResolver cr = getContentResolver();
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    System.out.println("NUM CONTACTS");
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    userManager.getCurrentUser().getContacts().add(new Contact(name, 0, "6043339622"));
                    contacts.add(name);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return contacts;
    }

    public void clickContact(){
        lstNames.setOnItemClickListener((adapter, view, position, id)->{
            Intent intent = new Intent(ContactsActivity.this, ChatActivity.class);
            startActivity(intent);
        });
    }



}