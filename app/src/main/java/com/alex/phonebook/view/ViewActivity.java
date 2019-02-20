package com.alex.phonebook.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import com.alex.phonebook.R;
import com.alex.phonebook.di.ThePhoneBookApplication;
import com.alex.phonebook.di.component.DaggerViewActivityComponent;
import com.alex.phonebook.di.component.ViewActivityComponent;
import com.alex.phonebook.di.module.ViewActivityModule;
import com.alex.phonebook.model.Contact;
import com.alex.phonebook.presenter.IViewContactPresenter;

import javax.inject.Inject;

public class ViewActivity extends AppCompatActivity implements IViewContact {
    public static final String CONTACT_INFO = "contactInfo";
    private Contact currentContact;
    private ViewContactInfoFragment viewContactInfoFragment;

    @Inject IViewContactPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        creatingMainElements();

        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public void initialization() {

        currentContact = (Contact) getIntent().getParcelableExtra(CONTACT_INFO);

        viewContactInfoFragment = (ViewContactInfoFragment) getSupportFragmentManager().findFragmentById(R.id.view_contact_info_fragment);
        setContactDataForFilling(currentContact);

        viewContactInfoFragment.setOnButtonClickListener(new ViewContactInfoFragment.onButtonClickListener() {
            @Override
            public void onEditButtonClick() {
                presenter.editContactButtonClicked();
            }

            @Override
            public void onDeleteButtonClick() {
                presenter.deleteContactButtonClicked(currentContact);
            }
        });
    }

    //Creating elements with Dagger
    private void creatingMainElements() {
        ViewActivityComponent component = DaggerViewActivityComponent.builder()
                .viewActivityModule(new ViewActivityModule())
                .applicationComponent(ThePhoneBookApplication.get(this).getThePhoneBookApplicationComponent())
                .build();
        component.inject(this);
    }

    public void setContactDataForFilling(Contact contact) {
        currentContact = contact;
        viewContactInfoFragment.setContactData(currentContact);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getUpdatedContactInformation(currentContact.getID());
    }

    public void editContact() {
        Intent intent = new Intent(getApplicationContext(), ContactManagementActivity.class);
        intent.putExtra(ContactManagementActivity.CONTACT_INFO, (Parcelable) currentContact);
        startActivity(intent);
    }

    public void closeActivity() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.destroy();
    }
}