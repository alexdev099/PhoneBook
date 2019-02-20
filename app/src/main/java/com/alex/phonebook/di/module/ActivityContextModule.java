package com.alex.phonebook.di.module;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityContextModule {

    private final Context context;

    public ActivityContextModule(Activity context) {
        this.context = context;
    }

    @Named("activity_context")
    @Provides
    public Context context() {
        return context;
    }
}
