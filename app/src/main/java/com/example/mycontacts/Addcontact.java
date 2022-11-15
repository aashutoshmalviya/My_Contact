package com.example.mycontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycontacts.RoomDatabase.Contact;

public class Addcontact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);
        Button saveButton=findViewById(R.id.SaveContact);
        ContactViewModel mContactViewModel=new ViewModelProvider(this).get(ContactViewModel.class);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstname=findViewById(R.id.EditfristName);
                EditText lastname=findViewById(R.id.EditLastName);
                EditText number=findViewById(R.id.EditPhone);
                if (String.valueOf(firstname.getText()).equals("")){
                    Toast.makeText(view.getContext(),"Please enter first name",Toast.LENGTH_SHORT).show();
                }else if (String.valueOf(number.getText()).equals("")){
                    Toast.makeText(view.getContext(),"Please enter Phone number",Toast.LENGTH_SHORT).show();
                }
                else {
                    Contact contact = new Contact(String.valueOf(firstname.getText()),
                            String.valueOf(lastname.getText()),
                            String.valueOf(number.getText()));
                    mContactViewModel.insert(contact);
                    finish();
                }
            }
        });

    }
}