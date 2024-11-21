package com.techelevator.dao.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.dao.EateryDao;
import com.techelevator.model.Eatery;

@Component
public class JdbcEateryDao implements EateryDao {
    private JdbcTemplate template;

    public JdbcEateryDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }
    
    @Override
    public List<Eatery> getEateries(int voteId) {
        String sql = "SELECT * FROM eatery WHERE eatery_id IN (SELECT eatery_id FROM eatery_vote WHERE vote_id = ?);";
        try {
            SqlRowSet results = template.queryForRowSet(sql, voteId);
            List<Eatery> eateries = new ArrayList<>();
            while(results.next())
                eateries.add(mapRowToEatery(results));
            return eateries;
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return null;
    }

    @Override
    public Eatery getEatery(int id) {
        String sql = "SELECT * FROM eatery WHERE eatery_id = ?;";
        try {
            SqlRowSet results = template.queryForRowSet(sql, id);
            if(results.next())
                return mapRowToEatery(results);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return null;
    }

    @Override
    public Eatery addEatery(Eatery eatery, int voteId) {
        try {
            String sql = "INSERT INTO eatery (eatery_name, image_url, eatery_address, category, website, open_time, close_time, has_takeout, rating, phone, price, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING eatery_id;";
            int id = template.queryForObject(sql, 
                Integer.class, 
                    eatery.getEatery_name(), 
                    eatery.getImage_url(), 
                    eatery.getEatery_address(), 
                    eatery.getCategory(), 
                    eatery.getWebsite(), 
                    eatery.getOpen_time(), 
                    eatery.getClose_time(), 
                    eatery.isHas_takeout(), 
                    eatery.getRating(), 
                    eatery.getPhone(), 
                    eatery.getPrice(),
                    eatery.getCity());
            if(voteId != -1) {
                sql = "INSERT INTO eatery_vote(eatery_id, vote_id) VALUES (?,?);";
                template.update(sql, id, voteId);
            }
            return getEatery(id);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems" + e);
        }
        return null;
    }

    private Eatery mapRowToEatery(SqlRowSet results) {
        Eatery eatery = new Eatery();
        eatery.setEatery_id(results.getInt("eatery_id"));
        eatery.setEatery_name(results.getString("eatery_name"));
        eatery.setImage_url(results.getString("image_url"));
        eatery.setEatery_address(results.getString("eatery_address"));
        eatery.setCategory(results.getString("category"));
        eatery.setWebsite(results.getString("website"));
        eatery.setOpen_time(results.getString("open_time"));
        eatery.setClose_time(results.getString("close_time"));
        eatery.setHas_takeout(results.getBoolean("has_takeout"));
        eatery.setRating(results.getDouble("rating"));
        eatery.setPhone(results.getString("phone"));
        eatery.setPrice(results.getString("price"));
        eatery.setCity(results.getString("city"));
        return eatery;
    }
}
