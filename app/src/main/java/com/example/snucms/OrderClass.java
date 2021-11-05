package com.example.snucms;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public class OrderClass {
    public DocumentReference documentReference;
    public String id, name, rollno, shop;
    Timestamp genTime;
    boolean pending;
    List<Item> orders;

    class Item {
        String s;
        int i;
    }

}
