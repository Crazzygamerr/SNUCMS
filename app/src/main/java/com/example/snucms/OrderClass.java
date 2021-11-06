package com.example.snucms;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderClass {
    public DocumentReference documentReference;
    public String id, name, rollno, shop;
    Timestamp genTime;
    boolean pending;
    ArrayList<String> order;

    public OrderClass() {
    }

    public OrderClass(DocumentReference documentReference, String id, String name, String rollno, String shop, Timestamp genTime, boolean pending, ArrayList<String> order) {
        this.documentReference = documentReference;
        this.id = id;
        this.name = name;
        this.rollno = rollno;
        this.shop = shop;
        this.genTime = genTime;
        this.pending = pending;
        this.order = order;
    }

    public static OrderClass fromMap(Map<String, Object> data) {
        OrderClass temp = new OrderClass(
                (DocumentReference) data.get("documentReference"),
                (String) data.get("id"),
                (String) data.get("name"),
                (String) data.get("rollno"),
                (String) data.get("shop"),
                (Timestamp) data.get("genTime"),
                (boolean) data.get("pending"),
                (ArrayList<String>) data.get("order")
        );

        return temp;
    }

    public OrderClass setId(String id) {
        this.id = id;
        return this;
    }

    public DocumentReference getDocumentReference() {
        return documentReference;
    }

    public Timestamp getGenTime() {
        return genTime;
    }

    public boolean isPending() {
        return pending;
    }

    public ArrayList<String> getOrder() {
        return order;
    }

    public void setDocumentReference(DocumentReference documentReference) {
        this.documentReference = documentReference;
    }

    public void setGenTime(Timestamp genTime) {
        this.genTime = genTime;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public void setOrder(ArrayList<String> order) {
        this.order = order;
    }
}

