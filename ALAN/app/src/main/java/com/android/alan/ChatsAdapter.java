package com.android.alan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder> {

    //
    private ArrayList<Chats> chatsArrayList;

    //
    public ChatsAdapter(ArrayList<Chats> chatsArrayList) {
        //
        this.chatsArrayList = chatsArrayList;
    }

    //
    @NonNull
    @Override
    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //
        return new ChatsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chats, parent, false));
    }

    //
    @Override
    public void onBindViewHolder(@NonNull ChatsViewHolder holder, int position) {
        //
        holder.textView_chats_name.setText(chatsArrayList.get(position).getName());
        holder.textView_chats_chats.setText(chatsArrayList.get(position).getText());
    }

    //
    @Override
    public int getItemCount() {
        //
        return chatsArrayList.size();
    }

    //
    public class ChatsViewHolder extends RecyclerView.ViewHolder {

        //
        TextView textView_chats_name, textView_chats_chats;

        //
        public ChatsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_chats_name = itemView.findViewById(R.id.textView_chats_name);
            textView_chats_chats = itemView.findViewById(R.id.textView_chats_chats);
        }
    }
}
