package com.alex.phonebook.di.module;

import com.alex.phonebook.di.interfaces.ContactManagementActivityScope;
import com.alex.phonebook.model.ContactsLocalRepository;
import com.alex.phonebook.presenter.ContactManagementPresenter;
import com.alex.phonebook.presenter.IContactManagementPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactManagementActivityModule {

    @Provides
    @ContactManagementActivityScope
    public IContactManagementPresenter contactManagementPresenter(ContactsLocalRepository contactsLocalRepository) {
        return new ContactManagementPresenter(contactsLocalRepository);
    }
}
