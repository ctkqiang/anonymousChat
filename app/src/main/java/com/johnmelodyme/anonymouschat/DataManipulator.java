package com.johnmelodyme.anonymouschat;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataManipulator {
    private FirebaseDatabase messages;
    private DatabaseReference message_REF;
    private List<ConversationData> HACKER_MESSAGES = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<ConversationData> HACKER_MESSAGES, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public DataManipulator(){
        messages = FirebaseDatabase.getInstance();
        message_REF = messages.getReference("HACKER_MESSAGE");
    }

    public void readDATA(final DataStatus ds){
        message_REF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HACKER_MESSAGES.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keynode : dataSnapshot.getChildren()){
                    keys.add(keynode.getKey());
                    ConversationData data = keynode.getValue(ConversationData.class);
                    HACKER_MESSAGES.add(data);
                }
                ds.DataIsLoaded(HACKER_MESSAGES, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addData(ConversationData cd, final DataStatus dataStatus){
        String key = message_REF.push().getKey();
        message_REF.child(key).setValue(cd)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dataStatus.DataIsInserted();
                    }
                });
    }
}
