package com.techelevator.dao.dao;

import com.techelevator.model.Eatery_Voter;

public interface EateryVotersDao {
    int getEateryFalseVotes(int eateryId);
    int getEateryTrueVotes(int eateryId);
    Eatery_Voter addEateryVote(Eatery_Voter eateryVoter);
}
