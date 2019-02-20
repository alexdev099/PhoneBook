package com.alex.phonebook.di;

import android.app.Activity;
import android.app.Application;

import com.alex.phonebook.di.component.ApplicationComponent;
import com.alex.phonebook.di.component.DaggerApplicationComponent;
import com.alex.phonebook.di.module.ApplicationContextModule;

public class ThePhoneBookApplication extends Application {

    private ApplicationComponent thePhoneBookApplicationComponent;

    public static ThePhoneBookApplication get(Activity activity) {
        return (ThePhoneBookApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        thePhoneBookApplicationComponent = DaggerApplicationComponent.builder()
                .applicationContextModule(new ApplicationContextModule(this)).build();
    }

    public ApplicationComponent getThePhoneBookApplicationComponent() {
        return thePhoneBookApplicationComponent;
    }
}
