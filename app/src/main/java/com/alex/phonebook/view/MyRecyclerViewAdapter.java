package com.alex.phonebook.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alex.phonebook.R;
import com.alex.phonebook.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<Contact> contactsList = new ArrayList<>();
    private Listener listener;

    public interface Listener {
        void onClick(long id);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        View currentView = holder.itemView;
        holder.bind(contactsList.get(position));
        currentView.setOnClickListener(view -> {
            if (listener != null) {
                long id = contactsList.get(position).getID();
                listener.onClick(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public void setContacts(List<Contact> contacts) {
        contactsList.clear();
        contactsList.addAll(contacts);
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView myTextViewName;
        private TextView myTextViewLastName;
        private View itemView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            myTextViewName = (TextView) itemView.findViewById(R.id.text_view_name);
            myTextViewLastName = (TextView) itemView.findViewById(R.id.text_view_last_name);
        }

        void bind(Contact contact) {
            myTextViewName.setText(contact.getName());
            myTextViewLastName.setText(contact.getLastName());
        }
    }
}
