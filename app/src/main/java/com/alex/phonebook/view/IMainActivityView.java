package com.alex.phonebook.view;

import com.alex.phonebook.model.Contact;

import java.util.List;

public interface IMainActivityView extends IMvpView {

    void showContacts(List<Contact> contacts);

    void showContactInfo(Contact contactInfo);

    void addNewContact();

}
