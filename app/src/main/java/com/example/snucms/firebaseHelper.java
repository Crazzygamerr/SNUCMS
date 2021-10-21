package com.example.snucms;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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

    /*public static void checkSlot(long rollno) {
        boolean exists = false;
        db.collection("ISC").whereArrayContains("rollno", rollno).get().addOnSuccessListener(
                new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        //if(queryDocumentSnapshots.size() == 1)

                    }
                }
        );
    }*/

    public static void getSlot(long rollno) {
        db.collection("ISC").get().addOnSuccessListener(
                queryDocumentSnapshots -> {
                    //System.out.println(queryDocumentSnapshots.size());
                    //Map<String, Object> slot = Collections.emptyMap();
                    ArrayList<SlotClass> allSlots = new ArrayList<>();
                    DocumentSnapshot documentSnapshot;
                    for(int i=0;i< queryDocumentSnapshots.size();i++) {

                        documentSnapshot = queryDocumentSnapshots.getDocuments().get(i);
                        SlotClass slot = documentSnapshot.toObject(SlotClass.class).setSlotName(documentSnapshot.getId());

                        if(!slot.rollno.contains(rollno)) {
                            allSlots.add(slot);
                        } else {
                            allSlots.removeAll(allSlots);
                            allSlots.add(slot);
                            break;
                        }

                    }
                    //System.out.println(allSlots.size());
                }
        );
    }

    public void addSlot() {
        Map<String, Object> docData = new HashMap<>();
        docData.put("slotName", "Gym slot");
        docData.put("timing", "MWF 6am");
        docData.put("totalSlots", 8);
        docData.put("remainingSlots", 6);
        docData.put("names", Arrays.asList("test1", "test2"));
        docData.put("rollno", Arrays.asList(0000, 0001));
        db.collection("ISC").document("MWF 6AM").set(docData);
    }

}
