package com.techelevator.dao.dao;

import com.techelevator.model.Voter;


public interface VoterDao {
    Voter addVoter(String voter_name, int vote_id);
    Voter getVoter(int id);
}
