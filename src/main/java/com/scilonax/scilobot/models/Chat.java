package com.scilonax.scilobot.models;

public class Chat {
    private int id;
    private String title;
    private String type;
    private Boolean allMembersAreAdministrators;

    private Chat(){}


    public static final class Builder{
        private int id;
        private String title;
        private String type;
        private Boolean allMembersAreAdministrators;

        public Builder withId(int id){
            this.id = id;
            return this;
        }

        public Builder withTitle(String title){
            this.title = title;
            return this;
        }

        public Builder withType(String type){
            this.type = type;
            return this;
        }

        public Builder withAllMembersAreAdministrators(Boolean allMembersAreAdministrators){
            this.allMembersAreAdministrators = allMembersAreAdministrators;
            return this;
        }

        public Chat build(){
            Chat chat = new Chat();
            chat.id = this.id;
            chat.title = this.title;
            chat.type = this.type;
            chat.allMembersAreAdministrators = this.allMembersAreAdministrators;

            return chat;
        }
    }
}
