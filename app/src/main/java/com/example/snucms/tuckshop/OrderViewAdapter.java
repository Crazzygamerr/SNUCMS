package com.example.snucms.tuckshop;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snucms.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewAdapter.OrderViewHolder> {

    Context context;
    ArrayList<OrderClass> arrayList;

    public OrderViewAdapter(Context context, ArrayList<OrderClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.order_item,parent,false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderClass orderClass = arrayList.get(position);
        holder.bind(orderClass, context);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView orderId, shopView, dateView, statusView, orderList;
        //LinearLayout linearLayout;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId = itemView.findViewById(R.id.orderId);
            shopView = itemView.findViewById(R.id.shopView);
            dateView = itemView.findViewById(R.id.dateView);
            statusView = itemView.findViewById(R.id.statusView);
            orderList = itemView.findViewById(R.id.orderList);
            //linearLayout = itemView.findViewById(R.id.linearLayoutOrder);

        }

        private void bind(OrderClass orderClass, Context context) {
            SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            orderId.setText(orderClass.id);
            /*ArrayAdapter<String> arr = new ArrayAdapter<>(
                    context,
                    R.layout.order_item,
                    (orderClass.order == null)?new ArrayList<>():orderClass.order
            );*/
            /*for(int i=0;i<orderClass.order.size();i++){
                View v = context.getLayoutInflater().inflate(R.layout.order_list_item);
                linearLayout.addView(v);
            }*/
            String temp = "";
            for(int i=0;i<orderClass.order.size();i++){
                temp = temp + orderClass.order.get(i) + "\n";
            }
            orderList.setText(temp);
            shopView.setText(orderClass.shop);

            dateView.setText((orderClass.genTime == null)?"":sfd.format(orderClass.genTime.toDate()));
            if(orderClass.delivered) {
                statusView.setText(R.string.delivered);
                statusView.setTextColor(Color.GREEN);
            } else {
                statusView.setText(R.string.pending);
                statusView.setTextColor(Color.RED);
            }
        }

    }
}
