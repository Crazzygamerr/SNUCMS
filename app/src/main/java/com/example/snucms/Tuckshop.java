package com.example.snucms;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Tuckshop extends AppCompatActivity {
    public static ArrayList<OrderClass> allOrders = new ArrayList<>();
    public static OrderViewAdapter myAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuckshop);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        myAdapter = new OrderViewAdapter(this, allOrders);
        recyclerView.setAdapter(myAdapter);
        firebaseHelper.setOrderListener("0001");

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(
                view -> {
                    //startActivity(new Intent(Tuckshop.this, ));
                }
        );
    }
}
