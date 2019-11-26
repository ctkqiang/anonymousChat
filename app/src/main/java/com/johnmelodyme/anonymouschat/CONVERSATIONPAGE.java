package com.johnmelodyme.anonymouschat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class CONVERSATIONPAGE extends AppCompatActivity {

    EditText messageBox;
    Button sendButton;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        messageBox = findViewById(R.id.messagebar);
        sendButton = findViewById(R.id.send);
        rv = findViewById(R.id.recyclerView);

        new DataManipulator().readDATA(new DataManipulator.DataStatus() {
            @Override
            public void DataIsLoaded(List<ConversationData> HACKER_MESSAGES, List<String> keys) {
                new RecyclerConfig().setConfig(rv, CONVERSATIONPAGE.this,
                        HACKER_MESSAGES, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConversationData cd;
                cd = new ConversationData();
                cd.setMessages(messageBox.getText().toString());

                new DataManipulator().addData(cd, new DataManipulator.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<ConversationData> HACKER_MESSAGES, List<String> keys) {
                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });


    }
}
