package com.derrick.park.assignment3_contacts.activities;


import android.support.annotation.NonNull;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;
import java.util.ArrayList;
import java.util.LinkedList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.WordViewHolder> {

    private ArrayList<Contact> mContactList;

    public MyAdapter( ArrayList<Contact> contactList) {
        this.mContactList = contactList;
    }

    @NonNull
    @Override
    public MyAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contacts, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.WordViewHolder holder, int position) {
        String mNameString = mContactList.get(position).getName().toString();
        String mPhoneNumberString = mContactList.get(position).getCell();
        holder.mName.setText(mNameString);
        holder.mPhoneNumber.setText(mPhoneNumberString);
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }


    class WordViewHolder extends RecyclerView.ViewHolder {

        public final TextView mName;
        public final TextView mPhoneNumber;
        final MyAdapter mAdapter;

        public WordViewHolder(View itemView, MyAdapter adapter) {
            super(itemView);
            mName = itemView.findViewById(R.id.item_name);
            mPhoneNumber = itemView.findViewById(R.id.item_phone_number);
            this.mAdapter = adapter;
        }
    }
}