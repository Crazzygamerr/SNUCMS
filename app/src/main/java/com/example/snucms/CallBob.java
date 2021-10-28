package com.example.snucms;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class CallBob extends AppCompatActivity {

    public static ArrayList<IssueClass> allIssues = new ArrayList<>();
    public static IssueViewAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_bob);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewIssue);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        myAdapter = new IssueViewAdapter(this, allIssues);
        recyclerView.setAdapter(myAdapter);
        firebaseHelper.setIssueListener("0001");

    }

/*    void fetch() {
        FirebaseRecyclerOptions<IssueClass> options = new FirebaseRecyclerOptions.builder<IssueClass>().setQuery(
                firebaseHelper.getIssueQuery("0001"),
                new SnapshotParser<IssueClass>() {
                    @NonNull
                    @Override
                    public IssueClass parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new IssueClass(
                                snapshot.child("id").getValue().toString(),
                                snapshot.child("title").getValue().toString(),
                                snapshot.child("desc").getValue().toString()
                        );
                    }
                }
        ).build();
    }*/
}
