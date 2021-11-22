package com.example.snucms.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.snucms.R;
import com.example.snucms.callbob.CallBob;
import com.example.snucms.gymslot.GymSlot;
import com.example.snucms.timetable.CalendarMainActivity;
import com.example.snucms.tuckshop.Tuckshop;

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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getDrawable(R.drawable.img_1));

        RelativeLayout btnTimetable = findViewById(R.id.btnTimetable);
        RelativeLayout btnGymSLot = findViewById(R.id.btnGymSlot);
        RelativeLayout btnTuckshop = findViewById(R.id.btnTuckshop);
        RelativeLayout btnCallBob = findViewById(R.id.btnCallBob);
        RelativeLayout btnLibrary = findViewById(R.id.btnLibrary);
        RelativeLayout btnLogout = findViewById(R.id.btnLogout);

        btnTimetable.setOnClickListener(
                view -> startActivity(new Intent(MainActivity.this, CalendarMainActivity.class))
        );

        btnGymSLot.setOnClickListener(
                view -> MainActivity.this.startActivity(new Intent(MainActivity.this, GymSlot.class))
        );

        btnTuckshop.setOnClickListener(
                view -> startActivity(new Intent(MainActivity.this, Tuckshop.class))
        );

        btnCallBob.setOnClickListener(
                view -> startActivity(new Intent(MainActivity.this, CallBob.class))
        );

        btnLibrary.setOnClickListener(
                view -> startActivity(new Intent(MainActivity.this, Library.class))
        );

        btnLogout.setOnClickListener(view -> {
            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.pref_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(MainActivity.this, LoginPage.class));
        });

    }
}