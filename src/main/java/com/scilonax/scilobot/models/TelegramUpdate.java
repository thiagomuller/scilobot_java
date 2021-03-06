package com.scilonax.scilobot.models;

public class TelegramUpdate {
    private int updateId;
    private Message message;

    public TelegramUpdate(){}

    public static Builder newBuilder(){
        return new Builder();
    }

    public String getMessageText(){
        if(this.message != null){
            return this.message.getText();
        } else{
            return "";
        }
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public static final class Builder{
        private int updateId;
        private Message message;

        public Builder withUpdateId(int updateId){
            this.updateId = updateId;
            return this;
        }

        public Builder withMessage(Message message){
            this.message = message;
            return this;
        }

        public TelegramUpdate build(){
            TelegramUpdate telegramUpdate = new TelegramUpdate();
            telegramUpdate.updateId = this.updateId;
            telegramUpdate.message = this.message;
            return telegramUpdate;
        }
    }

}
