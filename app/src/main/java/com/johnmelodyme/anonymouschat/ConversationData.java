package com.johnmelodyme.anonymouschat;

public class ConversationData {

    private String messages;

    public ConversationData(){
    }

    public ConversationData(String messages){
        this.messages = messages;
    }

    public String getMessages(){
        return messages;
}

    public void  setMessages(String messages){
        this.messages = messages;
    }
}
