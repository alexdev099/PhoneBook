package com.alex.phonebook.model;

import java.util.List;

import io.reactivex.Single;

public interface IContactsRepository {

    Single<Long> addContact(Contact contact);

    Single<Integer> updateContact(Contact contact);

    Single<Integer> deleteContact(Contact contact);

    Single<List<Contact>> getContactsList();

    Single<Contact> getContactInfo(long id);
}


