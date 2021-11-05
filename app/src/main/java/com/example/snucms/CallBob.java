package com.example.snucms;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Timestamp;

import java.util.ArrayList;

public class CallBob extends AppCompatActivity {

    public static ArrayList<IssueClass> allIssues = new ArrayList<>();
    public static IssueViewAdapter myAdapter;
    EditText editTextTitle, editTextDesc, editTextLocation;


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

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(
                view -> add(this)
        );

    }

    private void add(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ViewGroup viewGroup = findViewById(R.id.content);
        View view = LayoutInflater.from(context).inflate(R.layout.add_issue, viewGroup, false);
        builder.setView(view);
        builder.setMessage("Add Issue");
        builder.setCancelable(true);
        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextDesc = view.findViewById(R.id.editTextDesc);
        editTextLocation = view.findViewById(R.id.editTextLocation);
        builder.setPositiveButton("Yes", null);
        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }
        );

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = editTextTitle.getText().toString(),
                                desc = editTextDesc.getText().toString(),
                                location = editTextLocation.getText().toString();
                        /*boolean tb = title.trim().equals(""),
                                db = desc.trim().equals(""),
                                lb = location.trim().equals("");
                        System.out.println(tb + "" + db + lb);*/
                        if(title.trim().equals("")){
                            editTextTitle.setError("Title cannot empty");
                        } else if(desc.trim().equals("")) {
                            editTextDesc.setError("Description cannot empty");
                        } else if(location.trim().equals("")){
                            editTextLocation.setError("Location cannot empty");
                        } else {
                            IssueClass issueClass = new IssueClass();
                            issueClass.genTime = Timestamp.now();
                            issueClass.title = title;
                            issueClass.description = desc;
                            issueClass.location = location;
                            firebaseHelper.addIssue(issueClass, "0001");
                            alertDialog.dismiss();
                        }
                    }
                }
        );
    }

}
