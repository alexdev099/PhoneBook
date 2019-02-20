package com.alex.phonebook.di.component;

import com.alex.phonebook.di.interfaces.ApplicationScope;
import com.alex.phonebook.di.module.ApplicationModule;
import com.alex.phonebook.model.ContactsLocalRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
@ApplicationScope
public interface ApplicationComponent {

    ContactsLocalRepository getLocalDBRepository();
}
