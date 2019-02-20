package com.alex.phonebook.presenter;

import com.alex.phonebook.view.IMainActivityView;

public interface IContactsListPresenter extends IMvpPresenter<IMainActivityView> {

    void recyclerViewItemClicked(long id);

    void newContactClicked();
}