package com.example.mycontacts;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontacts.RoomDatabase.Contact;

public class ContactListAdapter extends ListAdapter<Contact,ContactViewHolder> {
    public ContactListAdapter(@NonNull DiffUtil.ItemCallback<Contact> diffCallback, MainActivity mainActivity){
        super(diffCallback);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item,parent,false);
        ContactViewHolder contactViewHolder=new ContactViewHolder(view);
        contactViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),UpdateContact.class);
                intent.putExtra("id",""+contactViewHolder.id);
                intent.putExtra("firstname",contactViewHolder.FirstnameItemView.getText());
                intent.putExtra("lastname",contactViewHolder.LastnameItemView.getText());
                intent.putExtra("phone",contactViewHolder.phone);

                view.getContext().startActivity(intent);
            }
        });

//        Making a call
        contactViewHolder.makecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+contactViewHolder.phone));
                view.getContext().startActivity(callIntent);
            }
        });

//        Messaging someone
        contactViewHolder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.setData(Uri.parse("sms:" +contactViewHolder.phone));
                view.getContext().startActivity(smsIntent);
            }
        });

        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact current=getItem(position);
        holder.FirstnameItemView.setText(current.getFirstname());
        holder.LastnameItemView.setText(current.getLastname());
        holder.phone=current.getPhone();
        holder.id=current.getId();

    }
    static class ContactDiff extends DiffUtil.ItemCallback<Contact>{

        @Override
        public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem==newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            if (((oldItem.getFirstname().equals(newItem.getFirstname()))&&
                    (oldItem.getLastname().equals(newItem.getLastname())))
                    ||(oldItem.getPhone().equals(newItem.getPhone())))
                return true;
            else return false;
        }
    }
}
class ContactViewHolder extends RecyclerView.ViewHolder{
     final TextView FirstnameItemView;
     final TextView LastnameItemView;
     String phone;
     int id;
     ImageButton makecall;
     ImageButton message;
    ContactViewHolder(View itemview)
    {
        super(itemview);
        FirstnameItemView=itemview.findViewById(R.id.Firstname);
        LastnameItemView=itemview.findViewById(R.id.Lastname);
        makecall=itemview.findViewById(R.id.makecall);
        message=itemview.findViewById(R.id.makemessage);
    }
}