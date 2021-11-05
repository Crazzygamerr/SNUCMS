package com.example.snucms;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.*;
import java.util.*;

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
                documentReference -> System.out.println("Success")
        );
    }

    public static void getSlots(String rollno) {
        db.collection("ISC").get().addOnSuccessListener(
                queryDocumentSnapshots -> {
                    //System.out.println(queryDocumentSnapshots.size());
                    //Map<String, Object> slot = Collections.emptyMap();
                    ArrayList<SlotClass> allSlots = MainActivity.allSlots;
                    DocumentSnapshot documentSnapshot;
                    for(int i=0;i< queryDocumentSnapshots.size();i++) {

                        documentSnapshot = queryDocumentSnapshots.getDocuments().get(i);
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
        SlotClass slotClass = new SlotClass(
                db.collection("ISC").document("MWF 7AM"),
                "Gym slot",
                "MWF 7AM",
                10,
                9,
                new ArrayList<>(
                        Arrays.asList(
                                "test3",
                                "test4"
                        )
                ),
                new ArrayList<>(
                        Arrays.asList(
                                "0005",
                                "0006"
                        )
                )
        );
        slotClass.documentReference.set(slotClass);
    }

    public static void setIssueListener(String rollno) {
        db.collection("callBob/"+rollno+"/issues").orderBy("genTime", Query.Direction.DESCENDING).addSnapshotListener(
                new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot queryDocumentSnapshots, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                        ArrayList<IssueClass> allIssues = new ArrayList<>();
                        DocumentSnapshot documentSnapshot;
                        for (int i = 0; i < queryDocumentSnapshots.size(); i++) {
                            documentSnapshot = queryDocumentSnapshots.getDocuments().get(i);
                            IssueClass issue = documentSnapshot
                                    .toObject(IssueClass.class)
                                    .setId(documentSnapshot.getId());

                            allIssues.add(issue);
                            //System.out.println(documentSnapshot.get("genTime"));
                        }
                        CallBob.allIssues.clear();
                        CallBob.allIssues.addAll(allIssues);
                        CallBob.myAdapter.notifyDataSetChanged();
                    }
                }
        );
    }

    public static void getIssues(String rollno) {
        db.collection("callBob/"+rollno+"/issues").get().addOnSuccessListener(
                new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<IssueClass> allIssues = CallBob.allIssues;
                        DocumentSnapshot documentSnapshot;
                        for (int i = 0; i < queryDocumentSnapshots.size(); i++) {
                            documentSnapshot = queryDocumentSnapshots.getDocuments().get(i);
                            IssueClass issue = documentSnapshot
                                    .toObject(IssueClass.class)
                                    .setId(documentSnapshot.getId());

                            allIssues.add(issue);
                        }
                        //System.out.println(allIssues.size());
                    }
                }
        );
    }

    public static void addIssue(IssueClass issue, String rollno) {
        String date = Year.now().toString() + MonthDay.now().getMonthValue(),
                day = String.valueOf(MonthDay.now().getDayOfMonth()),
                id;
        if(day.length() < 2)
            day = "0" + day;
        date = date + day;
        int temp = 0;
        for(IssueClass tempIssue : CallBob.allIssues) {
            int temp1 = Integer.parseInt(tempIssue.id.substring(8));
            temp = Math.max(temp, temp1);
        }
        temp++;
        id = String.valueOf(temp);
        while(id.length() < 3)
            id = "0" + id;
        issue.id = date + id;
        System.out.println(issue.id);
        issue.documentReference = db.collection("callBob/"+rollno+"/issues").document(String.valueOf(temp));
        db.collection("callBob/"+rollno+"/issues")
                .document(issue.id).set(issue);
    }

    public static void populateIssues() {
        String rollno = "0001";
        String issueno = "20210525001";
        Map<String, Object> data = new HashMap<>();
        data.put("documentReference", db.collection("callBob/"+rollno+"/issues").document(issueno));
        data.put("id", issueno);
        data.put("title", "test title1");
        data.put("description", "test desccccccccccccccccccccccccc");
        data.put("location", "test location1");
        data.put("genTime", Timestamp.now());
        data.put("fixTime", null);
        data.put("ack", false);
        data.put("studentVerify", false);
        data.put("callBobVerify", false);

        IssueClass issueClass = IssueClass.fromMap(data);
        /*IssueClass issueClass = new IssueClass(
                ,
                ,
                ,
                ,
                ,
                ,
                ,
                false,
                false,
                false
        );*/
        //issueClass.documentReference.set(issueClass);
        /*issueClass.documentReference.update(
                "gentime", Timestamp.now(),
                "fixTime", null,
                "ack", false,
                "studentVerify", false,
                "callBobVerify", false
        );*/
        issueClass.documentReference.set(data);
    }

    public static void verifyIssue(DocumentReference documentReference) {
        documentReference.update("studentVerify", true);
    }

    public static void addOrder(OrderClass orderClass) {
        db.collection("Tuckshop/"+orderClass.rollno+"/orders").add(orderClass).addOnSuccessListener(
                documentReference -> System.out.println("Success")
        );
    }

}
