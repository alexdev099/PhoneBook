package com.alex.phonebook.di.module;

import android.content.Context;

import com.alex.phonebook.di.interfaces.ApplicationContext;
import com.alex.phonebook.di.interfaces.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationContextModule {

    private Context context;

    public ApplicationContextModule(Context context) {
        this.context = context;
    }

    @ApplicationContext
    @ApplicationScope
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}
