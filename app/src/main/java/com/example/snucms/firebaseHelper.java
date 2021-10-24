package com.example.snucms;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class firebaseHelper {
    public static  FirebaseFirestore db = FirebaseFirestore.getInstance();

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

    public static void getSlots(String rollno) {
        db.collection("ISC").get().addOnSuccessListener(
                queryDocumentSnapshots -> {
                    //System.out.println(queryDocumentSnapshots.size());
                    //Map<String, Object> slot = Collections.emptyMap();
                    ArrayList<SlotClass> allSlots = MainActivity.allSlots;
                    DocumentSnapshot documentSnapshot;
                    for(int i=0;i< queryDocumentSnapshots.size();i++) {

                        documentSnapshot = queryDocumentSnapshots.getDocuments().get(i);
                        System.out.println(documentSnapshot.getReference());
                        SlotClass slot = documentSnapshot
                                .toObject(SlotClass.class)
                                .setSlot(documentSnapshot.getId());
                        if(!slot.rollno.contains(rollno)) {
                            allSlots.add(slot);
                        } else {
                            allSlots.clear();
                            allSlots.add(slot);
                            break;
                        }

                    }
                    //System.out.println(allSlots.get(1));
                }
        );
    }

    public static void addSlot(SlotClass slotClass, String name, String rollno) {
        //slotClass.documentReference.set();
        slotClass.documentReference.update(
                "names", FieldValue.arrayUnion(name),
                "rollno", FieldValue.arrayUnion(rollno),
                "remainingSlots", FieldValue.increment(-1)
        );
    }

    public static void populateSlots() {
        System.out.println("populate");
        SlotClass slotClass = new SlotClass(
                db.collection("ISC").document("MWF 7AM"),
                "Gym slot",
                "MWF 7AM",
                10,
                9,
                new ArrayList<>(
                        Arrays.asList(
                                "test3"
                        )
                ),
                new ArrayList<>(
                        Arrays.asList(
                                "0005"
                        )
                )
        );
        db.collection("ISC")
                .document("MWF 7AM")
                .set(slotClass);
    }

}
