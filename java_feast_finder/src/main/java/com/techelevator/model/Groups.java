package com.techelevator.model;

public class Groups {
    private int group_id;
    private String group_name;
    private int vote_id;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
    
    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Groups{");
        sb.append("group_id=").append(group_id);
        sb.append(", group_name=").append(group_name);
        sb.append(", vote_id=").append(vote_id);
        sb.append('}');
        return sb.toString();
    }

}