package com.techelevator.dao.dao;

import java.util.List;

import com.techelevator.model.Eatery;

public interface EateryDao {
    Eatery getEatery(int id);
    Eatery addEatery(Eatery eatery, int voteId);
    List<Eatery> getEateries(int voteId);
}
