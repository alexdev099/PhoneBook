package com.alex.phonebook.di.component;

import com.alex.phonebook.di.interfaces.ViewActivityScope;
import com.alex.phonebook.di.module.ViewActivityModule;
import com.alex.phonebook.view.ViewActivity;

import dagger.Component;

@Component(modules = {ViewActivityModule.class}, dependencies = ApplicationComponent.class)
@ViewActivityScope
public interface ViewActivityComponent {

    void inject(ViewActivity viewActivity);
}
