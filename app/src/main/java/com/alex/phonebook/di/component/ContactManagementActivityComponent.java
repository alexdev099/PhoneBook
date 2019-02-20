package com.alex.phonebook.di.component;

import com.alex.phonebook.di.interfaces.ContactManagementActivityScope;
import com.alex.phonebook.di.module.ContactManagementActivityModule;
import com.alex.phonebook.view.ContactManagementActivity;

import dagger.Component;

@Component(modules = {ContactManagementActivityModule.class}, dependencies = ApplicationComponent.class)
@ContactManagementActivityScope
public interface ContactManagementActivityComponent {

   void inject(ContactManagementActivity activity);
}
