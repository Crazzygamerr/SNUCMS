package com.example.snucms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SlotViewAdapter extends RecyclerView.Adapter<SlotViewAdapter.SlotViewHolder> {

    Context context;
    ArrayList<String> arrayList;

    public SlotViewAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.slot_item,parent,false);

        return new SlotViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SlotViewHolder holder, int position) {

        String s = arrayList.get(position);
        holder.textView.setText(s);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class SlotViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public SlotViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
        }
    }

}