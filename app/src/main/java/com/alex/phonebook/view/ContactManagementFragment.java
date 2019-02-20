package com.alex.phonebook.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.alex.phonebook.R;
import com.alex.phonebook.model.Contact;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactManagementFragment extends Fragment {
    private EditText etName, etLastName, etPhone;
    private Spinner spPhoneType;
    private ArrayAdapter<CharSequence> adapter;
    private onButtonClickListener listener;

    public interface onButtonClickListener {
        void onSaveButtonClicked();
    }

    public void setOnButtonClickListener(onButtonClickListener listener) {
        this.listener = listener;
    }

    public ContactManagementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_management, container, false);
        if (view != null) {
            etName = (EditText) view.findViewById(R.id.etName);
            etLastName = (EditText) view.findViewById(R.id.etLastName);
            etPhone = (EditText) view.findViewById(R.id.etPhone);
            spPhoneType = (Spinner) view.findViewById(R.id.spPhoneType);

            adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.phoneTypesArray, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spPhoneType.setAdapter(adapter);

            view.findViewById(R.id.btnSave).setOnClickListener(v -> {
                if (listener != null) {
                    listener.onSaveButtonClicked();
                }
            });
        }
        return view;
    }

    public void setContactData(Contact contact) {
        etName.setText(contact.getName());
        etLastName.setText(contact.getLastName());
        etPhone.setText(String.valueOf(contact.getPhoneNumber()));
        spPhoneType.setSelection(adapter.getPosition(contact.getPhoneType()));
    }

    public Contact getData() {
        String name = etName.getText().toString();
        String lastName = etLastName.getText().toString();
        long phoneNumber = Long.valueOf(etPhone.getText().toString());
        String phoneType = spPhoneType.getSelectedItem().toString();

        return new Contact(name, lastName, phoneNumber, phoneType);
    }

    public boolean checkFilling() {
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();

        return !((name.equalsIgnoreCase("")) || (String.valueOf(phone).equalsIgnoreCase("")));
    }
}