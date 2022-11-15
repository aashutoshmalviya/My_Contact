package com.example.mycontacts;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mycontacts.RoomDatabase.Contact;
import com.example.mycontacts.RoomDatabase.ContactDao;
import com.example.mycontacts.RoomDatabase.ContactRoomDatabase;

import java.util.List;

public class ContactRepository {
    private ContactDao mContactDao;
    private LiveData<List<Contact>> mAllContact;

    ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        mContactDao = db.contactDao();
        mAllContact = mContactDao.getAllContacList();
    }

    LiveData<List<Contact>> getmAllContact() {
        return mAllContact;
    }

    void insert(Contact contact) {
        ContactRoomDatabase.databaseWriteExecuter.execute(() -> {
            mContactDao.insert(contact);
        });
    }

    void delete(Contact contact) {
        ContactRoomDatabase.databaseWriteExecuter.execute(()->{
        mContactDao.delete(contact);
        });
    }
}
