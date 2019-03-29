package com.scilonax.scilobot.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    private int messageId;
    private @JsonProperty("from") User user;
    private Chat chat;
    private int date;
    private String text;

    public Message(){}

    public String getText(){
        return this.text;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static final class Builder{
        private int messageId;
        private @JsonProperty("from") User user;
        private Chat chat;
        private int date;
        private String text;

        public Builder withMessageId(int messageId){
            this.messageId = messageId;
            return this;
        }

        public Builder withUser(User user){
            this.user = user;
            return this;
        }

        public Builder withChat(Chat chat){
            this.chat = chat;
            return this;
        }

        public Builder withDate(int date){
            this.date = date;
            return this;
        }

        public Builder withText(String text){
            this.text = text;
            return this;
        }

        public Message build(){
            Message message = new Message();
            message.messageId = this.messageId;
            message.user = this.user;
            message.chat = this.chat;
            message.date = this.date;
            message.text = this.text;
            return message;
        }
    }
}
