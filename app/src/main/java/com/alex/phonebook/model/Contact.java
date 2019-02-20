package com.alex.phonebook.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "contacts")
public class Contact implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private long ID;

    @ColumnInfo(name = "Name")
    private final String name;

    @ColumnInfo(name = "Last_Name")
    private final String lastName;

    @ColumnInfo(name = "Phone_Number")
    private final long phoneNumber;

    @ColumnInfo(name = "Phone_Type")
    private final String phoneType;

    public Contact(String name, String lastName, long phoneNumber, String phoneType){
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public Contact(Parcel source) {
        ID = source.readLong();
        name = source.readString();
        lastName = source.readString();
        phoneNumber = source.readLong();
        phoneType = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ID);
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeLong(phoneNumber);
        dest.writeString(phoneType);
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
