package com.example.snucms;

import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.Timestamp;

public class IssueClass {

    public DocumentReference documentReference;
    public String id, title, description, location;
    Timestamp genTime, fixTime;
    boolean ack, studentVerify, callBobVerufy;

    public IssueClass() {
    }

    public IssueClass(DocumentReference documentReference, String id, String title, String description, String location, Timestamp genTime, Timestamp fixTime, boolean ack, boolean studentVerify, boolean callBobVerufy) {
        this.documentReference = documentReference;
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.genTime = genTime;
        this.fixTime = fixTime;
        this.ack = ack;
        this.studentVerify = studentVerify;
        this.callBobVerufy = callBobVerufy;
    }

    public IssueClass setId(String id) {
        this.id = id;
        return this;
    }

}
