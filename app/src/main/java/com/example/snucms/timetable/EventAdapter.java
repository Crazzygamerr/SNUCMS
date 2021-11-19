package com.example.snucms.timetable;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.snucms.R;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends ArrayAdapter<com.example.snucms.timetable.Event> {

    Context context;

    public EventAdapter(@NonNull Context context, ArrayList<Event> events)
    {
        super(context, 0, events);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View itemView, @NonNull ViewGroup parent)
    {
        Event event = getItem(position);

        if(event.getDate().equals(CalendarUtils.selectedDate)
                || (event.isRepeat()
                && event.getDate().getDayOfWeek() == CalendarUtils.selectedDate.getDayOfWeek()))
        {
            if (itemView == null)
                itemView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

            TextView eventCellTV = itemView.findViewById(R.id.eventCellTV);
            String eventTitle = event.getName() + " " + CalendarUtils.formattedTime(event.getTime());
            eventCellTV.setText(eventTitle);

            itemView.findViewById(R.id.eventCellLL).setOnClickListener(view -> {
                context.startActivity(
                        new Intent(context, EventEditActivity.class)
                                .putExtra("pos", position)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                );
            });
            return itemView;
        } else
            return new Space(context);
    }



}
