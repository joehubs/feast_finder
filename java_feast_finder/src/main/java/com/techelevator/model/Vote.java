package com.techelevator.model;

public class Vote {
    private int vote_id;
    private String vote_name;
    private String vote_description;
    private String event_date;
    private String vote_date;
    private int eatery_id;
    private boolean is_active;
    private int user_vote_id;

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getVote_date() {
        return vote_date;
    }

    public void setVote_date(String vote_date) {
        this.vote_date = vote_date;
    }

    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }

    public String getVote_name() {
        return vote_name;
    }

    public void setVote_name(String vote_name) {
        this.vote_name = vote_name;
    }

    public String getVote_description() {
        return vote_description;
    }

    public void setVote_description(String vote_description) {
        this.vote_description = vote_description;
    }

    public int getEatery_id() {
        return eatery_id;
    }

    public void setEatery_id(int eatery_id) {
        this.eatery_id = eatery_id;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public int getUser_vote_id() {
        return user_vote_id;
    }

    public void setUser_vote_id(int user_vote_id) {
        this.user_vote_id = user_vote_id;
    }
}
