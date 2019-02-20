package com.alex.phonebook.model;

import java.util.List;

import io.reactivex.Single;

public class ContactsLocalRepository implements IContactsRepository {

    private ContactDao contactDao;

    public ContactsLocalRepository(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public Single<List<Contact>> getContactsList() {
        return Single.fromCallable(() -> contactDao.getAll());
    }

    @Override
    public Single<Contact> getContactInfo(final long id) {
        return Single.fromCallable(() -> contactDao.getById(id));
    }

    @Override
    public Single<Long> addContact(final Contact contact) {
        return Single.fromCallable(() -> contactDao.insert(contact));
    }

    @Override
    public Single<Integer> updateContact(final Contact contact) {
        return Single.fromCallable(() -> contactDao.update(contact));
    }

    @Override
    public Single<Integer> deleteContact(final Contact contact) {
        return Single.fromCallable(() -> contactDao.delete(contact));
    }
}