package com.techelevator.dao.dao;

import java.util.List;

import com.techelevator.model.Vote;

public interface VoteDao {
    Vote getVote(int id);
    Vote addVote(Vote vote, String name);
    List<Vote> getAllVotes(String name);
}
