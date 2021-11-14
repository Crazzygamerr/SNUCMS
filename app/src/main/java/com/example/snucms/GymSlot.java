package com.example.snucms;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArraySet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GymSlot extends AppCompatActivity {

    public static ArrayList<SlotClass> allSlots = new ArrayList<>();
    public static SlotViewAdapter slotViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gym_slot);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSlot);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        slotViewAdapter = new SlotViewAdapter(this, allSlots);
        recyclerView.setAdapter(slotViewAdapter);
        firebaseHelper.setSlotListener();

    }
}
