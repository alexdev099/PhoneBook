package com.alex.phonebook.di.module;

import com.alex.phonebook.di.interfaces.ViewActivityScope;
import com.alex.phonebook.model.ContactsLocalRepository;
import com.alex.phonebook.presenter.IViewContactPresenter;
import com.alex.phonebook.presenter.ViewContactPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewActivityModule {

    @Provides
    @ViewActivityScope
    public IViewContactPresenter viewContactPresenter(ContactsLocalRepository contactsLocalRepository) {
        return new ViewContactPresenter(contactsLocalRepository);
    }
}
