package com.alex.phonebook.presenter;

import android.util.Log;

import com.alex.phonebook.model.IContactsRepository;
import com.alex.phonebook.view.IContactManagementView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ContactManagementPresenter extends BasePresenter<IContactManagementView> implements IContactManagementPresenter {
    private final String TAG = getClass().getSimpleName();
    private IContactsRepository repository;
    private CompositeDisposable disposables = new CompositeDisposable();

    public ContactManagementPresenter(IContactsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void viewIsReady() {
        getView().initialization();
    }

    @Override
    public void saveButtonClicked() {
        //Checking correct filling Phone and Name fields.
        if (getView().checkCorrectFilling()) {
            //Checks, if contact has id, then it already exists and we must run update, else add contact.
            if (getView().hasID()) {
                disposables.add(repository.updateContact(getView().getCurrentContact())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Integer>() {
                            @Override
                            public void onSuccess(Integer count) {
                                Log.i(TAG, "Updated " + count + " records.");
                                getView().closeActivity();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, e.getMessage(), e);
                            }
                        }));
            } else {
                disposables.add(repository.addContact(getView().getCurrentContact())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Long>() {
                            @Override
                            public void onSuccess(Long id) {
                                Log.i(TAG, "RowId for the added contact is: " + id);
                                getView().closeActivity();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, e.getMessage(), e);
                            }
                        }));

            }
        } else {
            getView().showToast();
        }
    }

    @Override
    public void destroy() {
        disposables.dispose();
    }
}