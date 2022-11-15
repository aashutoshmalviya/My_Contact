package com.example.mycontacts;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mycontacts.RoomDatabase.Contact;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository mRepository;
    private final LiveData<List<Contact>> mAllContacts;

    public ContactViewModel(Application application) {
        super(application);
        this.mRepository = new ContactRepository(application);
        this.mAllContacts = mRepository.getmAllContact();
    }
    LiveData<List<Contact>> getmAllContacts(){return mAllContacts;}

    public void insert(Contact contact){mRepository.insert(contact);}

    public void deleteContact(Contact contact){mRepository.delete(contact);}
}
