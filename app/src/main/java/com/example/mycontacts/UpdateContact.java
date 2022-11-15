package com.example.mycontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycontacts.RoomDatabase.Contact;

import kotlin.jvm.internal.CollectionToArray;

public class UpdateContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        EditText nameFirstUpdate=findViewById(R.id.UpdateFirstName);
        EditText nameLastUpdate=findViewById(R.id.UpdateLastName);
        EditText phoneUpdate=findViewById(R.id.UpdatePhone);
        nameFirstUpdate.setText(getIntent().getStringExtra("firstname"));
        nameLastUpdate.setText(getIntent().getStringExtra("lastname"));
        phoneUpdate.setText(getIntent().getStringExtra("phone"));
        Button save=findViewById(R.id.updatesave);
        Button delete=findViewById(R.id.updatedelete);
        int id=Integer.parseInt(getIntent().getStringExtra("id"));
        ContactViewModel mContactViewModel=new  ViewModelProvider(this).get(ContactViewModel.class);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(nameFirstUpdate.getText()).equals("")){
                    Toast.makeText(view.getContext(),"Please enter first name",Toast.LENGTH_SHORT).show();
                }else if (String.valueOf(phoneUpdate.getText()).equals("")){
                    Toast.makeText(view.getContext(),"Please enter Phone number",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Contact contact = new Contact(id
                            , nameFirstUpdate.getText() + ""
                            , nameLastUpdate.getText() + "",
                            String.valueOf(phoneUpdate.getText()));
                    mContactViewModel.insert(contact);
                    finish();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact=new Contact(id
                        ,nameFirstUpdate.getText()+"",nameLastUpdate.getText()+"",
                        String.valueOf(phoneUpdate.getText()));
                mContactViewModel.deleteContact(contact);
                finish();
            }
        });
    }
}