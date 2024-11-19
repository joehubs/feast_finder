package com.techelevator.dao.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.dao.VoteDao;
import com.techelevator.model.Vote;

@Component
public class JdbcVoteDao implements VoteDao{

    private JdbcTemplate template;

    public JdbcVoteDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }
    
    @Override
    public Vote getVote(int id) {
        String sql = "SELECT * FROM vote WHERE vote_id = ?";
        SqlRowSet results = template.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowToVote(results);
        } else {
            return null;
        }
    }

    @Override
    public Vote addVote(Vote vote, String name) {
        String sql = "INSERT INTO vote(vote_name, vote_description, vote_start_date, vote_end_date, eatery_id, user_vote_id) VALUES (?, ?, ?, ?, ?, ?) RETURNING vote_id";
        int voteId = template.queryForObject(sql, Integer.class, vote.getVote_name(), vote.getVote_description(), vote.getVote_date(), vote.getEvent_date(), vote.getEatery_id(), getUserIdWithName(name));
        vote.setVote_id(voteId);
        return vote;
    }

    private int getUserIdWithName(String name) {
        String sql = "SELECT user_id FROM users WHERE username = ?";
        return template.queryForObject(sql, Integer.class, name);
    }

    @Override
    public List<Vote> getAllVotes(String name) {
        List<Vote> votes = new ArrayList<>();
        String sql = "SELECT * FROM vote WHERE user_vote_id = ?;";
        SqlRowSet results = template.queryForRowSet(sql, getUserIdWithName(name));
        while (results.next()) {
            votes.add(mapRowToVote(results));
        }
        return votes;
    }

    public Vote mapRowToVote(SqlRowSet results) {
        Vote vote = new Vote();
        vote.setVote_id(results.getInt("vote_id"));
        vote.setVote_name(results.getString("vote_name"));
        vote.setVote_description(results.getString("vote_description"));
        vote.setVote_date(results.getString("vote_start_date"));
        vote.setEvent_date(results.getString("vote_end_date"));
        vote.setEatery_id(results.getInt("eatery_id"));
        vote.setIs_active(results.getBoolean("is_active"));
        vote.setUser_vote_id(results.getInt("user_vote_id"));
        return vote;
    }
}
