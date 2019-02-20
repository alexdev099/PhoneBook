package com.alex.phonebook.di.module;

import com.alex.phonebook.di.interfaces.MainActivityScope;
import com.alex.phonebook.model.ContactsLocalRepository;
import com.alex.phonebook.presenter.ContactsListPresenter;
import com.alex.phonebook.presenter.IContactsListPresenter;
import com.alex.phonebook.view.MyRecyclerViewAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    @MainActivityScope
    public MyRecyclerViewAdapter myRecyclerViewAdapter() {
        return new MyRecyclerViewAdapter();
    }

    @Provides
    @MainActivityScope
    public IContactsListPresenter contactsListPresenter(ContactsLocalRepository contactsLocalRepository) {
        return new ContactsListPresenter(contactsLocalRepository);
    }
}
