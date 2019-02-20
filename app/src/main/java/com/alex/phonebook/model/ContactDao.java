package com.alex.phonebook.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contacts")
    List<Contact> getAll();

    @Query("SELECT * FROM contacts WHERE _id = :id")
    Contact getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Contact contact);

    @Update
    int update(Contact contact);

    @Delete
    int delete(Contact contact);
}
