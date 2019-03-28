package com.scilonax.scilobot.models;

public class TelegramUpdate {
    private int updateId;
    private Message message;

    private TelegramUpdate(){}

    public static Builder newBuilder(){
        return new Builder();
    }

    public String getMessageText(){
        return this.message.getText();
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
