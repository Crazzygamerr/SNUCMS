package com.example.snucms.timetable;

import static com.example.snucms.timetable.CalendarUtils.daysInWeekArray;
import static com.example.snucms.timetable.CalendarUtils.monthYearFromDate;
import static com.example.snucms.timetable.CalendarUtils.daysInWeekArray;
import static com.example.snucms.timetable.CalendarUtils.monthYearFromDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snucms.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);

        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);

        setWeekView();
    }

    private void setWeekView()
    {
        monthYearText.setText(monthYearFromDate(com.example.snucms.timetable.CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(com.example.snucms.timetable.CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdpater();
    }


    public void previousWeekAction(View view)
    {
        com.example.snucms.timetable.CalendarUtils.selectedDate = com.example.snucms.timetable.CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view)
    {
        com.example.snucms.timetable.CalendarUtils.selectedDate = com.example.snucms.timetable.CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        com.example.snucms.timetable.CalendarUtils.selectedDate = date;
        setWeekView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setEventAdpater();
    }

    private void setEventAdpater()
    {
        ArrayList<Event> dailyEvents = Event.eventsForDate(com.example.snucms.timetable.CalendarUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }


    public void newEventAction(View view)
    {
        startActivity(new Intent(this, EventEditActivity.class));
    }

    public void dailyAction(View view)
    {
        startActivity(new Intent(this, DailyCalendarActivity.class));
    }
}