package com.alex.phonebook.view;

import com.alex.phonebook.model.Contact;

public interface IContactManagementView extends IMvpView {

    void initialization();

    boolean checkCorrectFilling();

    void showToast();

    boolean hasID();

    Contact getCurrentContact();

    void closeActivity();
}
