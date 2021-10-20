package com.example.snucms;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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
Notificaitions
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}