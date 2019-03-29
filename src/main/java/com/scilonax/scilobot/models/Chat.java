package com.scilonax.scilobot.models;

public class Chat {
    private int id;
    private String title;
    private String type;
    private Boolean allMembersAreAdministrators;

    public Chat(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAllMembersAreAdministrators() {
        return allMembersAreAdministrators;
    }

    public void setAllMembersAreAdministrators(Boolean allMembersAreAdministrators) {
        this.allMembersAreAdministrators = allMembersAreAdministrators;
    }

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
