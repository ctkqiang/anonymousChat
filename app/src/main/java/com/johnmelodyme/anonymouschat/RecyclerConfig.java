package com.johnmelodyme.anonymouschat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerConfig {
    private static Context context;
    private messageList.ClientAdapter haha;

    static class messageList extends RecyclerView.ViewHolder{
        private TextView message;
        public String key;

        public messageList(ViewGroup parent){
            super(LayoutInflater.from(context)
            .inflate(R.layout.list, parent, false));

            message = (TextView)itemView.findViewById(R.id.msg);
        }

        public void bind(ConversationData cd, String key){
            message.setText(cd.getMessages());
            this.key = key;
        }

        static class ClientAdapter extends RecyclerView.Adapter<messageList>{
            private List<ConversationData> conversationData;
            private List<String> key;

            public ClientAdapter(List<ConversationData> conversationData, List<String> key){
                this.conversationData = conversationData;
                this.key = key;
            }

            @NonNull
            @Override
            public messageList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new messageList(parent);
            }

            @Override
            public void onBindViewHolder(@NonNull messageList holder, int position) {
                holder.bind(conversationData.get(position), key.get(position));
            }

            @Override
            public int getItemCount() {
                return conversationData.size();
            }
        }
    }

    public void setConfig(RecyclerView rv ,  Context c ,
                          List<ConversationData> cd, List<String> key) {
        context = c;
        haha = new messageList.ClientAdapter(cd, key);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(haha);
    }
}
