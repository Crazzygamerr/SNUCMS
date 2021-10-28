package com.example.snucms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IssueViewAdapter extends RecyclerView.Adapter<IssueViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<IssueClass> arrayList;

    public IssueViewAdapter(Context context, ArrayList<IssueClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.issue_item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueViewAdapter.MyViewHolder holder, int position) {

        IssueClass s = arrayList.get(position);
        holder.idTextView.setText(s.id);
        holder.titleTextView.setText(s.title);
        holder.descTextView.setText(s.description);

    }

     //private void setUp

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView idTextView, titleTextView, descTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.id);
            titleTextView = itemView.findViewById(R.id.title);
            descTextView = itemView.findViewById(R.id.desc);
        }
    }

}