package com.alex.phonebook.presenter;

import android.util.Log;

import com.alex.phonebook.model.Contact;
import com.alex.phonebook.model.IContactsRepository;
import com.alex.phonebook.view.IMainActivityView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ContactsListPresenter extends BasePresenter<IMainActivityView> implements IContactsListPresenter {
    private final String TAG = getClass().getSimpleName();

    private IContactsRepository repository;

    private CompositeDisposable disposables = new CompositeDisposable();

    public ContactsListPresenter(IContactsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void viewIsReady() {
        loadContacts();
    }

    //Get's all data from the database and shows it on the main screen.
    private void loadContacts() {
        disposables.add(repository.getContactsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Contact>>() {

                    @Override
                    public void onSuccess(List<Contact> listLiveData) {
                        getView().showContacts(listLiveData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }));
    }

    //Get's data about the selected contact, put it into the Intent (Parcelable) and opening new activity.
    public void recyclerViewItemClicked(long id) {
        disposables.add(repository.getContactInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Contact>() {
                    @Override
                    public void onSuccess(Contact contact) {
                        getView().showContactInfo(contact);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }));
    }

    @Override
    public void newContactClicked() {
        getView().addNewContact();
    }

    @Override
    public void destroy() {
        disposables.dispose();
    }
}