package com.techelevator.dao.dao;

import java.util.List;

import com.techelevator.model.Eatery;

public interface YelpDao {
    //check if search term is in database
    Boolean checkYelp(String searchTerm);
    //get count of search term
    int getYelpCount(String searchTerm);
    //get eateries associated with Yelp id
    List<Eatery> getEateries(String searchTerm);
    void addYelp(String searchTerm, List<Eatery> eateries);
}
