package com.example.mycontacts.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact contact);

@Delete
    void delete(Contact contact);

@Query("SELECT * FROM contact_database ORDER BY id ASC")
    LiveData<List<Contact>> getAllContacList();
}
