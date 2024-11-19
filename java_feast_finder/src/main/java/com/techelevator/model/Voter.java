package com.techelevator.model;

public class Voter {
    private int voter_id;
    private String voter_name;
    private boolean user_response;
    private int user_id;

    public void setVoter_name() {
        this.voter_name = voter_name;
    }

    public String getVoter_name() {
        return voter_name;
    }

    public int getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(int voter_id) {
        this.voter_id = voter_id;
    }

    public boolean isUser_response() {
        return user_response;
    }

    public void setUser_response(boolean user_response) {
        this.user_response = user_response;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setVoter_name(String voter_name) {
       this.voter_name = voter_name;
    }
}
