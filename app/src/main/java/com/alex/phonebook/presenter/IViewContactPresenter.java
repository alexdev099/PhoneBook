package com.alex.phonebook.presenter;

import com.alex.phonebook.model.Contact;
import com.alex.phonebook.view.IViewContact;

public interface IViewContactPresenter extends IMvpPresenter<IViewContact> {

    void editContactButtonClicked();

    void deleteContactButtonClicked(Contact contact);

    void getUpdatedContactInformation(long id);
}
