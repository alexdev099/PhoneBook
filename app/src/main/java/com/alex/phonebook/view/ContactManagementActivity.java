package com.alex.phonebook.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alex.phonebook.R;
import com.alex.phonebook.di.ThePhoneBookApplication;
import com.alex.phonebook.di.component.ContactManagementActivityComponent;
import com.alex.phonebook.di.component.DaggerContactManagementActivityComponent;
import com.alex.phonebook.di.module.ContactManagementActivityModule;
import com.alex.phonebook.model.Contact;
import com.alex.phonebook.presenter.IContactManagementPresenter;

import javax.inject.Inject;

public class ContactManagementActivity extends AppCompatActivity implements IContactManagementView {
    public static final String CONTACT_INFO = "contactInfo";
    private Contact currentContact;

    private ContactManagementFragment contactManagementFragment;

    @Inject IContactManagementPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_management);

        creatingMainElements();

        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public void initialization(){
         contactManagementFragment = (ContactManagementFragment) getSupportFragmentManager().findFragmentById(R.id.contact_management_fragment);

        if (getIntent().getParcelableExtra(CONTACT_INFO) != null){
            currentContact = getIntent().getParcelableExtra(CONTACT_INFO);
            contactManagementFragment.setContactData(currentContact);
        }

        contactManagementFragment.setOnButtonClickListener(() -> presenter.saveButtonClicked());
    }

    //Creating elements with Dagger
    private void creatingMainElements(){
        ContactManagementActivityComponent component = DaggerContactManagementActivityComponent.builder()
                .contactManagementActivityModule(new ContactManagementActivityModule())
                .applicationComponent(ThePhoneBookApplication.get(this).getThePhoneBookApplicationComponent())
                .build();
        component.inject(this);
    }

    public boolean checkCorrectFilling(){
        return contactManagementFragment.checkFilling();
    }

    //Checks, if currentContact == null then we must add record to our database, else update record.
    public boolean hasID(){
        return currentContact != null;
    }

    public Contact getCurrentContact(){
        Contact contact = contactManagementFragment.getData();
        if (hasID()){
            contact.setID(currentContact.getID());
        }
        return contact;
    }

    @Override
    public void showToast() {
        Toast.makeText(getBaseContext(),R.string.save_toast,Toast.LENGTH_LONG).show();
    }

    public void closeActivity(){
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.destroy();
    }
}