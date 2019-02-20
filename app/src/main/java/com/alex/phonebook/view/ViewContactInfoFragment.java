package com.alex.phonebook.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alex.phonebook.R;
import com.alex.phonebook.model.Contact;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewContactInfoFragment extends Fragment {
    private TextView tvName, tvLastName, tvPhone, tvType;
    private onButtonClickListener listener;

    public interface onButtonClickListener {
        void onEditButtonClick();

        void onDeleteButtonClick();
    }

    public void setOnButtonClickListener(onButtonClickListener listener) {
        this.listener = listener;
    }

    public ViewContactInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_contact_info, container, false);
        View.OnClickListener buttonClickListener = obtainButtonsClickListener();

        if (view != null) {
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvLastName = (TextView) view.findViewById(R.id.tv_last_name);
            tvPhone = (TextView) view.findViewById(R.id.tv_phone);
            tvType = (TextView) view.findViewById(R.id.tv_type);

            Button editButton = view.findViewById(R.id.btnEdit);
            editButton.setOnClickListener(buttonClickListener);
            Button deleteButton = view.findViewById(R.id.btnDelete);
            deleteButton.setOnClickListener(buttonClickListener);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void setContactData(Contact contact) {
        tvName.setText(contact.getName());
        tvLastName.setText(contact.getLastName());
        tvPhone.setText(String.valueOf(contact.getPhoneNumber()));
        tvType.setText(contact.getPhoneType());
    }

    private View.OnClickListener obtainButtonsClickListener() {
        return view -> {
            switch (view.getId()) {
                case R.id.btnEdit:
                    if (listener != null) {
                        listener.onEditButtonClick();
                    }
                    break;
                case R.id.btnDelete:
                    if (listener != null) {
                        listener.onDeleteButtonClick();
                    }
                    break;
            }
        };
    }
}