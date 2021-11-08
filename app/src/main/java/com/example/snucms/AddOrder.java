package com.example.snucms;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;

public class AddOrder extends AppCompatActivity {

    ArrayList<String> shops = new ArrayList<String>(){{
        add("Navin Tea Stall");
        add("Kaathi Rolls");
        add("Anna's cafe");
    }};
    String[][] items = {
        {"Badam milk", "Tea", "Coffee"},
        {"Aloo roll", "Cheese roll", "Paneer roll"},
        {"Idly", "Plain Dosa", "Masala Dosa", "Filter coffee"}
    };
    String currentShop, orderString = "";

    HashMap<String, Integer> orders = new HashMap<>();

//    Spinner spinnerItem, spinnerRemoveItem;
//    ArrayAdapter<String> itemAdapter, removeAdapter;
    ItemViewAdapter itemViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_order);

        Spinner spinnerShop = findViewById(R.id.spinnerShop);
        ArrayAdapter<String> adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                shops.toArray()
        );
        spinnerShop.setAdapter(adapter);
        spinnerShop.setSelection(0);
        currentShop = spinnerShop.getSelectedItem().toString();
        for(int i=0;i<items[0].length;i++){
            orders.put(items[0][i], 0);
        }
        spinnerShop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if(!shops.get(pos).equals(currentShop)) {
                    orders.clear();
                    for(int i=0;i<items[pos].length;i++){
                        orders.put(items[pos][i], 0);
                    }
                    itemViewAdapter.updateLists();
                    itemViewAdapter.notifyDataSetChanged();
                    currentShop = shops.get(pos);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        itemViewAdapter = new ItemViewAdapter(this, orders);
        recyclerView.setAdapter(itemViewAdapter);
        itemViewAdapter.notifyDataSetChanged();

        Button btnPlaceOrder, btnCancel;
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        btnCancel = findViewById(R.id.btnCancel);

        btnPlaceOrder.setOnClickListener(view -> {
            OrderClass orderClass = new OrderClass();
            orderClass.name = "test1";
            orderClass.rollno = "0001";
            orderClass.shop = currentShop;
            orderClass.genTime = Timestamp.now();
            orderClass.delivered = false;
            ArrayList<String> temp = new ArrayList<>();
            orders.forEach((s, integer) -> temp.add(s + " - " + integer));
            orderClass.order = temp;
            firebaseHelper.addOrder(orderClass);
            finish();
        });

        btnCancel.setOnClickListener(view -> finish());
    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_order);

        TextView textViewOrders = findViewById(R.id.textViewOrders);

        Spinner spinnerShop = findViewById(R.id.spinnerShop);
        ArrayAdapter<String> adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                shops.toArray()
        );
        spinnerShop.setAdapter(adapter);
        spinnerShop.setSelection(0);
        currentShop = spinnerShop.getSelectedItem().toString();
        spinnerShop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if(!shops.get(pos).equals(currentShop)) {
                    itemAdapter.clear();
                    itemAdapter.addAll(items[pos]);
                    removeAdapter.clear();
                    orders.clear();
                    orderString = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerItem = findViewById(R.id.spinnerItem);
        itemAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                items[shops.indexOf(currentShop)]
        );
        spinnerItem.setAdapter(itemAdapter);
        spinnerItem.setSelection(0);

        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(view -> {
            orders.put(spinnerItem.getSelectedItem().toString(), numberPicker.getValue());
            setOrderString();
            textViewOrders.setText(orderString);
            removeAdapter.addAll(orders.keySet());
        });

        spinnerRemoveItem = findViewById(R.id.spinnerRemoveItem);
        removeAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                orders.keySet().toArray()
        );
        spinnerRemoveItem.setAdapter(removeAdapter);

        Button btnRemoveItem = findViewById(R.id.btnRemoveItem);
        btnRemoveItem.setOnClickListener(view -> {
            orders.remove(spinnerRemoveItem.getSelectedItem().toString());
            setOrderString();
            textViewOrders.setText(orderString);
        });

    }

    void setOrderString() {
        orderString = "";
        orders.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                orderString = orderString + s + " - " + integer.toString() + "\n";
            }
        });
    }*/
}
