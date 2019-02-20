package com.alex.phonebook.view;

import com.alex.phonebook.model.Contact;

public interface IViewContact extends IMvpView {

    void initialization();

    void editContact();

    void setContactDataForFilling(Contact contact);

    void closeActivity();
}
