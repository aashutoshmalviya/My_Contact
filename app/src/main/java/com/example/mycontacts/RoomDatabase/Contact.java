package com.example.mycontacts.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_database")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;
    public int getId() {
        return id;
    }

    @NonNull
    @ColumnInfo(name="firstname")
    private String Firstname;
    @NonNull
    @ColumnInfo(name="lastname")
    private String Lastname;
    @NonNull
    @ColumnInfo(name="phone")
    private String phone;

    public Contact() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(@NonNull String firstname) {
        Firstname = firstname;
    }

    public void setLastname(@NonNull String lastname) {
        Lastname = lastname;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @Ignore
    public Contact(@NonNull String firstname,@NonNull String lastname, @NonNull String phone) {
        this.Firstname=firstname;
        this.Lastname=lastname;
        this.phone = phone;
    }

    @Ignore
    public Contact(@NonNull int id,@NonNull String firstname,@NonNull String lastname, @NonNull String phone) {
        this.id=id;
        this.Firstname=firstname;
        this.Lastname=lastname;
        this.phone = phone;
    }

    @NonNull
    public String getFirstname() {
        return Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getPhone() {
        return phone;
    }

}
