package com.example.snucms;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class firebaseHelper {
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void addLibraryEntry(String name, String netid, long rollno, int token) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("token", token);
        entry.put("name", name);
        entry.put("netid", netid);
        entry.put("rollno", rollno);
        entry.put("time", Timestamp.now());
        db.collection("Library").add(entry).addOnSuccessListener(
                new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println("Success");
                    }
                }
        );
    }

    public static void getSlots(long rollno) {
        db.collection("ISC").whereArrayContains("rollno", rollno).get().addOnSuccessListener(
                new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        System.out.println(queryDocumentSnapshots.size());
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            System.out.println(documentSnapshot.getId());
                            System.out.println(documentSnapshot.getData());
                        }
                    }
                }
        );
    }


}
