package com.alex.phonebook.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alex.phonebook.R;
import com.alex.phonebook.di.ThePhoneBookApplication;
import com.alex.phonebook.di.component.DaggerMainActivityComponent;
import com.alex.phonebook.di.component.MainActivityComponent;
import com.alex.phonebook.di.module.MainActivityModule;
import com.alex.phonebook.model.Contact;
import com.alex.phonebook.presenter.IContactsListPresenter;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IMainActivityView {
    private TextView numberOfContacts;

    @Inject  MyRecyclerViewAdapter myAdapter;
    @Inject  IContactsListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creatingMainElements();
        initViews();

        presenter.attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.viewIsReady();
    }

    private void initViews() {
        numberOfContacts = (TextView) findViewById(R.id.number_of_contacts);
        FloatingActionButton fab = findViewById(R.id.new_contact_fab);

        fab.setOnClickListener(view -> presenter.newContactClicked());

        RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setAdapter(myAdapter);

        myAdapter.setListener(id -> presenter.recyclerViewItemClicked(id));
    }

    //Creating elements with Dagger
    private void creatingMainElements() {
        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule())
                .applicationComponent(ThePhoneBookApplication.get(this).getThePhoneBookApplicationComponent())
                .build();
        component.inject(this);
    }

    public void showContacts(List<Contact> contacts) {
            myAdapter.setContacts(contacts);
            numberOfContacts.setText("Find contacts: " + myAdapter.getItemCount());
    }

    public void showContactInfo(Contact data) {
        Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
        intent.putExtra(ViewActivity.CONTACT_INFO, (Parcelable) data);
        startActivity(intent);
    }

    @Override
    public void addNewContact() {
        Intent intent = new Intent(getApplicationContext(), ContactManagementActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.destroy();
    }
}