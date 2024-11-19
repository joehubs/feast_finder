package com.techelevator.dao.jdbcdao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.dao.EateryVotersDao;
import com.techelevator.model.Eatery_Voter;

@Component
public class JdbcEateryVotersDao implements EateryVotersDao {
    private JdbcTemplate template;

    public JdbcEateryVotersDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public Eatery_Voter addEateryVote(Eatery_Voter eateryVoter) {
        String sql = "INSERT INTO eatery_voter (eatery_id, voter_id, eatery_response) VALUES (?, ?, ?) RETURNING eatery_id;";
        int id = template.queryForObject(sql, Integer.class, eateryVoter.getEatery_id(), eateryVoter.getVoter_id(), eateryVoter.getEatery_response());
        return getEateryVoteById(id);
    }

    @Override
    public int getEateryTrueVotes(int eateryId) {
        String sql = "SELECT COUNT(*) FROM eatery_voter WHERE eatery_response = true AND eatery_id = ?;";
        return template.queryForObject(sql, Integer.class, eateryId);
    }

    @Override
    public int getEateryFalseVotes(int eateryId) {
        String sql = "SELECT COUNT(*) FROM eatery_voter WHERE eatery_response = false AND eatery_id = ?;";
        return template.queryForObject(sql, Integer.class, eateryId);
    }

    private Eatery_Voter getEateryVoteById(int id) {
        String sql = "SELECT * FROM eatery_voter WHERE eatery_id = ?;";
        SqlRowSet results = template.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowToEateryVoter(results);
        } else {
            return null;
        }
    }

    private Eatery_Voter mapRowToEateryVoter(SqlRowSet results) {
        Eatery_Voter eateryVoter = new Eatery_Voter();
        eateryVoter.setEatery_id(results.getInt("eatery_id"));
        eateryVoter.setVoter_id(results.getInt("voter_id"));
        eateryVoter.setEatery_response(results.getBoolean("eatery_response"));
        return eateryVoter;
    }
}
