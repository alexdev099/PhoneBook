package com.alex.phonebook.presenter;

import com.alex.phonebook.view.IContactManagementView;

public interface IContactManagementPresenter extends IMvpPresenter<IContactManagementView> {

    void saveButtonClicked();
}
