package com.alex.phonebook.presenter;

import android.util.Log;

import com.alex.phonebook.model.Contact;
import com.alex.phonebook.model.IContactsRepository;
import com.alex.phonebook.view.IViewContact;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ViewContactPresenter extends BasePresenter<IViewContact> implements IViewContactPresenter {
    private final String TAG = getClass().getSimpleName();

    private IContactsRepository repository;

    private CompositeDisposable disposables = new CompositeDisposable();

    public ViewContactPresenter(IContactsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void viewIsReady() {
        getView().initialization();
    }

    @Override
    public void editContactButtonClicked() {
        getView().editContact();
    }

    //Deleting contact, then closing activity
    @Override
    public void deleteContactButtonClicked(Contact contact) {
        disposables.add(repository.deleteContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(Integer count) {
                        Log.i(TAG, "Successfully deleted number of contacts: " + count);
                        getView().closeActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }));
    }

    //When user returns to this activity from edit activity, this method gets updated information for the contact fields
    // and displays them in the views.
    @Override
    public void getUpdatedContactInformation(long id) {
        disposables.add(repository.getContactInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Contact>() {

                    @Override
                    public void onSuccess(Contact contact) {
                        getView().setContactDataForFilling(contact);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }));
    }

    @Override
    public void destroy() {
        disposables.dispose();
    }
}