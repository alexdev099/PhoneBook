package com.alex.phonebook.presenter;

import com.alex.phonebook.view.IMvpView;

public abstract class BasePresenter<T extends IMvpView> implements IMvpPresenter<T> {

    private T view;

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public T getView() {
        return view;
    }

    @Override
    public void destroy() {

    }
}
