package com.alex.phonebook.presenter;

import com.alex.phonebook.view.IMvpView;

public interface IMvpPresenter<V extends IMvpView> {

    void attachView(V mvpView);

    void detachView();

    void viewIsReady();

    void destroy();
}
