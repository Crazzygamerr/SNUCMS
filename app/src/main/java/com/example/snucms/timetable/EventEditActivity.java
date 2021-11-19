package com.example.snucms.timetable;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.snucms.R;
import com.example.snucms.jsonHelper;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.json.JSONException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class EventEditActivity extends AppCompatActivity
{
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;
    private SwitchMaterial repeatSwitch;

    int mYear, mMonth, mDay, mHour, mMinute, pos;
    boolean repeat = false;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);

        eventNameET = findViewById(R.id.eventName);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
        repeatSwitch = findViewById(R.id.repeatSwitch);

        repeatSwitch.setOnCheckedChangeListener((compoundButton, b) -> repeat = b);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            pos = extras.getInt("pos");
            event = CalendarUtils.eventsList.get(pos);

            eventNameET.setText(event.getName());

            mYear = event.getDate().getYear();
            mMonth = event.getDate().getMonthValue();
            mDay = event.getDate().getDayOfMonth();
            mHour = event.getTime().getHour();
            mMinute = event.getTime().getMinute();

            repeatSwitch.setChecked(event.isRepeat());
        } else {
            mYear = CalendarUtils.selectedDate.getYear();
            mMonth = CalendarUtils.selectedDate.getMonthValue();
            mDay = CalendarUtils.selectedDate.getDayOfMonth();
            mHour = LocalTime.now().getHour();
            mMinute = LocalTime.now().getMinute();
        }

        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(LocalTime.of(mHour, mMinute)));
    }

    public void saveEventAction(View view) throws IOException, JSONException {
        String eventName = eventNameET.getText().toString();
        Event newEvent = new Event(eventName, LocalDate.of(mYear, mMonth, mDay), LocalTime.of(mHour, mMinute), repeat);
        if(event == null)
            CalendarUtils.eventsList.add(newEvent);
        else
            CalendarUtils.eventsList.set(pos, newEvent);

        new jsonHelper(getApplicationContext()).writeJson();
        finish();
    }

    public void removeEventAction(View view) throws IOException, JSONException {
        CalendarUtils.eventsList.remove(event);
        new jsonHelper(getApplicationContext()).writeJson();
        finish();
    }

    public void showTimePickerDialog(View v) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                        mMinute = minute;
                        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(LocalTime.of(mHour, mMinute)));
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void showDatePickerDialog(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(LocalDate.of(mYear, mMonth, mDay)));
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}