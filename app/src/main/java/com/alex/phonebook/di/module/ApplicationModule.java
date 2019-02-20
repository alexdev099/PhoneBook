package com.alex.phonebook.di.module;

import android.content.Context;

import com.alex.phonebook.di.interfaces.ApplicationContext;
import com.alex.phonebook.di.interfaces.ApplicationScope;
import com.alex.phonebook.model.AppDatabase;
import com.alex.phonebook.model.ContactsLocalRepository;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApplicationContextModule.class)
public class ApplicationModule {

    @Provides
    @ApplicationScope
    public ContactsLocalRepository localDBRepository(@ApplicationContext Context context) {
        return new ContactsLocalRepository(AppDatabase.getInstance(context).contactDao());
    }
}
