package com.techelevator.model;

public class Eatery_Voter {
    private boolean eatery_response;
    private int eatery_id;
    private int voter_id;

    public int getEatery_id() {
        return eatery_id;
    }

    public void setEatery_id(int eatery_id) {
        this.eatery_id = eatery_id;
    }

    public int getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(int voter_id) {
        this.voter_id = voter_id;
    }

    public boolean getEatery_response() {
        return eatery_response;
    }

    public void setEatery_response(boolean eatery_response) {
        this.eatery_response = eatery_response;
    }


}
