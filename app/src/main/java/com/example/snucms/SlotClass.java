package com.example.snucms;

import com.google.firebase.firestore.DocumentId;

import java.util.ArrayList;

public class SlotClass {
    @DocumentId
    public String slotName, timing;
    int totalSlots, remainingSlots;
    public ArrayList<String> names;
    public ArrayList<Long> rollno;

    SlotClass() {
    }

    SlotClass(String slotName, String timing, int totalSlots, int remainingSlots, ArrayList<String> names, ArrayList<Long> rollno) {
        this.slotName = slotName;
        this.timing = timing;
        this.totalSlots = totalSlots;
        this.remainingSlots = remainingSlots;
        this.names = names;
        this.rollno = rollno;
    }

    public SlotClass setSlotName(String slotName) {
        this.slotName = slotName;
        return this;
    }

    /*public SlotClass fromSnapshot(QueryDocumentSnapshot doc) {
        Map<String, Object> slotMap = doc.getData();
        ArrayList<String> namesList =
        SlotClass slot = new SlotClass(
                doc.getId(),
                slotMap.get("timing").toString(),
                Integer.parseInt(slotMap.get("totalSlots").toString()),
                Integer.parseInt(slotMap.get("remainingSlots").toString()),
                slotMap.get("names"),
                slotMap.get("rollno")
        );
        slot.name = doc.getId();
        slot.timing = timing;
        slot.totalSlots = totalSlots;
        slot.remainingSlots = remainingSlots;
        slot.names = names;
        slot.rollno = rollno;
        
    }*/



}
