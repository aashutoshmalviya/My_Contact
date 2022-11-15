package com.example.mycontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.mycontacts.RoomDatabase.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    public static final int NEW_CONTACT_ACTIVITY_REQUEST_CODE=1;

    private ContactViewModel mContactViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        final ContactListAdapter adapter=new ContactListAdapter(new ContactListAdapter.ContactDiff(),this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        mContactViewModel =new ViewModelProvider(this).get(ContactViewModel.class);
        mContactViewModel.getmAllContacts().observe(this,contacts -> {
            adapter.submitList(contacts);
        });

        FloatingActionButton addContact=findViewById(R.id.fab);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Addcontact.class));
            }
        });
    }

}