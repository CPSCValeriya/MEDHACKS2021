package com.example.medhacks2021;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
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

    }


    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            contacts = getContactNames();

            System.out.print("PRINTING CONTACTS: ");
            for(int i = 0 ; i < contacts.size() ; i++){
                System.out.print(contacts.get(i) + ", ");
            }


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
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.contact_element,parent,false);
            }

            TextView name = (TextView) item.findViewById(R.id.contact_txt);
            name.setText(userManager.getCurrentUser().getContacts().get(position).getName());
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
        // Get the ContentResolver
        ContentResolver cr = getContentResolver();
        // Get the Cursor of all the contacts
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        // Move the cursor to first. Also check whether the cursor is empty or not.
        if (cursor.moveToFirst()) {
            // Iterate through the cursor
            do {
                // Get the contacts name
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
              //String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add(name);
                //'System.out.print("PHONE: " + phoneNumber);
                userManager.addContact(new Contact(name, 0, "6043339622"));
            } while (cursor.moveToNext());
        }
        // Close the curosor
        cursor.close();

        return contacts;
    }

}