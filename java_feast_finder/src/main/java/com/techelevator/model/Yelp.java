package com.techelevator.model;

public class Yelp {
    private int yelp_id;
    private int search_count;
    private String search_term;

    public int getYelp_id() {
        return yelp_id;
    }
    public void setYelp_id(int yelp_id) {
        this.yelp_id = yelp_id;
    }
    public int getSearch_count() {
        return search_count;
    }
    public void setSearch_count(int search_count) {
        this.search_count = search_count;
    }
    public String getSearch_term() {
        return search_term;
    }
    public void setSearch_term(String search_term) {
        this.search_term = search_term;
    }
}
