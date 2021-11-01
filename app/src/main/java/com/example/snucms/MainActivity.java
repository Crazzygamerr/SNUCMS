package com.example.snucms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;

/*
Timetable with Google Calendar Integration and notifications for classes, assignments, ISC slots and other events
Call bob: Issue tracking, history and more accountability
ISC slot booking and digital sign in
Library Digital Sign in using QR Code
Tuckshop Food Reservation
*/

/*
Required components:
Firebase database
    Callbob
    ISC slots
    Library entries
    Tuckshop entries
Notifications
QR code scanner
 */

/*
Screens:
Login
Main
Timetable
    Today's events
    Add/remove events
Callbob
    Add issue
    Issue history (remove issues)
    Issue tracking
ISC slots
    List of slots
        Each item has name, time, total capacity
    //shows confirmation on click
    //only displays your slot on booking
Library
    QR scanner with token entry
Tuckshop
    Show menu
    Pending orders
    Completed orders
 */

/*
Class for event
    Name
    Time
    Repeat
    Reminder time
    function to cancel notification
 */

/*
Class for issue
    Id
    Title
    Desciption
    Location
    Tracking
        Acknowledged
        * estimated verification time
        * Verified - User
        estimated fix time
        Issue resolved - User and call
 */

/*
ISC slots:
Collections for each slot
Reset collections every week
Able to add only one slot
 */

/*
Library:
Simple database design for library
Only appends user data, with the token number
 */

/*
Tuckshop:
Two tables, one for pending and finished orders
Add new orders to pending
Finished orders are moved
Both are displayed seperately per user
 */

public class MainActivity extends AppCompatActivity {

    public static ArrayList<SlotClass> allSlots = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button btnGymSlot = findViewById(R.id.btnGymSlot);
        Button btnCallBob = findViewById(R.id.btnCallBob);
        Button btnGetIssue = findViewById(R.id.btnGetIssue);
        Button btnSetIssue = findViewById(R.id.btnSetIssue);
        Button btnPopulateIssue = findViewById(R.id.btnPopulateIssue);

        EditText editText = findViewById(R.id.editTextNumber);
        EditText editName = findViewById(R.id.editText);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        SlotViewAdapter myAdapter = new SlotViewAdapter(this, new ArrayList<>(Arrays.asList("test1", "test2", "test3")));
        recyclerView.setAdapter(myAdapter);

        button1.setOnClickListener(
                view -> firebaseHelper.addLibraryEntry("name", "netid", 1234, 2)
        );

        button2.setOnClickListener(
                view -> {
                    String s = editText.getText().toString();
                    firebaseHelper.getSlots(s);
                }
        );

        button3.setOnClickListener(
                view -> {
                    firebaseHelper.populateSlots();
                }
        );

        button4.setOnClickListener(
                view -> {
                    String roll = editText.getText().toString();
                    String name = editName.getText().toString();
                    firebaseHelper.addSlot(allSlots.get(0), name, roll);
                }
        );

        /*btnGymSlot.setOnClickListener(
                view -> {
                    Intent intent = new Intent(MainActivity.this, .class);
                    startActivity(intent);
                }
        );*/

        btnCallBob.setOnClickListener(
                view -> {
                    Intent intent = new Intent(MainActivity.this, CallBob.class);
                    startActivity(intent);
                }
        );

        btnGetIssue.setOnClickListener(
                view -> {
                    firebaseHelper.getIssues("0001");
                }
        );

        btnSetIssue.setOnClickListener(
                view -> {
                    firebaseHelper.populateIssues();
                }
        );

        btnPopulateIssue.setOnClickListener(
                view -> {
                    firebaseHelper.populateIssues();
                }
        );

    }
}