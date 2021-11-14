package com.example.snucms;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SlotViewAdapter extends RecyclerView.Adapter<SlotViewAdapter.SlotViewHolder> {

    Context context;
    ArrayList<SlotClass> arrayList;

    public SlotViewAdapter(Context context, ArrayList<SlotClass> arrayList) {
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
        SlotClass slotClass = arrayList.get(position);
        holder.bind(slotClass, context);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class SlotViewHolder extends RecyclerView.ViewHolder{

        TextView slotTitle, textViewTotal, textViewRemaining, textViewBooked;
        Button btnBookSlot;

        public SlotViewHolder(@NonNull View itemView) {
            super(itemView);
            slotTitle = itemView.findViewById(R.id.slotTitle);
            textViewTotal = itemView.findViewById(R.id.textViewTotal);
            textViewRemaining = itemView.findViewById(R.id.textViewRemaining);
            btnBookSlot = itemView.findViewById(R.id.btnBookSlot);
            textViewBooked = itemView.findViewById(R.id.textViewBooked);
        }

        private void bind(SlotClass slotClass, Context context) {

            slotTitle.setText(slotClass.slotName);

            String s = "Total: " + slotClass.totalSlots;
            textViewTotal.setText(s);

            s = "Remaining: " + slotClass.remainingSlots;
            textViewRemaining.setText(s);

            if(slotClass.remainingSlots > 0 && !slotClass.rollno.contains(firebaseHelper.rollno)) {
                btnBookSlot.setOnClickListener(view -> {
                    firebaseHelper.addSlot(slotClass);
                });
            } else {
                btnBookSlot.setVisibility(View.GONE);
                textViewBooked.setVisibility(View.VISIBLE);
            }
        }
    }

}