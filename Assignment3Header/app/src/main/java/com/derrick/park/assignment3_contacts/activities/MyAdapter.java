package com.derrick.park.assignment3_contacts.activities;


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;
import com.shuhart.stickyheader.StickyAdapter;

import java.util.ArrayList;

public class MyAdapter extends StickyAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder> {

    private ArrayList<Contact> mContactList;


    public MyAdapter( ArrayList<Contact> contactList) {
        this.mContactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 0) {
            return new HeaderViewholder(inflater.inflate(R.layout.recycler_view_header_item, parent, false));
        }
        return new ItemViewHolder(inflater.inflate(R.layout.layout_contacts, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //  String currentFirst_letter = mContactList.get(i).getName().getFirst().substring(0,1).toUpperCase();

        if (mContactList.get(i).isHeader()) {
            ((HeaderViewholder) viewHolder).textView.setText(mContactList.get(i).getName().getFirst().substring(0,1).toUpperCase());
        } else {
            ((ItemViewHolder) viewHolder).textView.setText(mContactList.get(i).getName().toString());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mContactList.get(position).isHeader())
            return 0;
        else
            return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return createViewHolder(parent, 0);
    }

    public static class HeaderViewholder extends RecyclerView.ViewHolder {
        TextView textView;

        HeaderViewholder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_name);
        }
    }



    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        if (mContactList.get(itemPosition).isHeader()){
            return (mContactList.get(itemPosition).getSectionHeaderId());
        }
        return mContactList.get(itemPosition).getSectionItemid();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int headerPosition) {
        ((HeaderViewholder) holder).textView.setText("Header " + mContactList.get(headerPosition).getName().getFirst().substring(0,1).toUpperCase());
    }



 // class WordViewHolder extends RecyclerView.ViewHolder {

 //     public final TextView mName;
 //     public final TextView mPhoneNumber;
 //     public final TextView mFirstLetter;
 //     final MyAdapter mAdapter;

 //     public WordViewHolder(View itemView, MyAdapter adapter) {
 //         super(itemView);
 //         mName = itemView.findViewById(R.id.item_name);
 //         mPhoneNumber = itemView.findViewById(R.id.item_phone_number);
 //         mFirstLetter = itemView.findViewById(R.id.first_Letter);
 //         this.mAdapter = adapter;
 //     }
 // }




}