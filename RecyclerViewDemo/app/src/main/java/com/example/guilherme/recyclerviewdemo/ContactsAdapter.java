package com.example.guilherme.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContacViewHolder>{

    private Context mContext;
    private String[] mNames;
    private String[] mNumbers;

    public ContactsAdapter(Context context,String[] names,String[] numbers){
        mContext = context;
        mNames = names;
        mNumbers = numbers;
    }

    @NonNull
    @Override
    public ContacViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.contact_row,viewGroup,false);
        return new ContacViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContacViewHolder contacViewHolder, int i) {
        // bind data with ViewHolder
        contacViewHolder.mNameTextView.setText(mNames[i]);
        contacViewHolder.mNumberTextView.setText(mNumbers[i]);

    }

    // represent the number of items we want to show
    @Override
    public int getItemCount() {
        return mNames.length;
    }

    public class ContacViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;
        private TextView mNumberTextView;

        public ContacViewHolder(View itemView){
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.name_tv);
            mNumberTextView = itemView.findViewById(R.id.phone_tv);
        }
    }
}
