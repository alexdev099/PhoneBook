package com.alex.phonebook.di.component;

import com.alex.phonebook.di.interfaces.MainActivityScope;
import com.alex.phonebook.di.module.MainActivityModule;
import com.alex.phonebook.view.MainActivity;

import dagger.Component;

@Component(modules = {MainActivityModule.class}, dependencies = ApplicationComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

}
