package com.techelevator.dao.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.dao.dao.YelpDao;
import com.techelevator.model.Eatery;

@Component
public class JdbcYelpDao implements YelpDao {
    private JdbcTemplate template;

    public JdbcYelpDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Boolean checkYelp(String searchTerm) {
        String sql = "SELECT COUNT(*) FROM yelp WHERE search_term = ?;";
        return template.queryForObject(sql, Boolean.class, searchTerm);
    }

    @Override
    public void addYelp(String searchTerm, List<Eatery> eateries) {
        String sql = "INSERT INTO yelp (search_term, search_count, eateries) VALUES (?, ?, CAST(? AS JSONB));";
        
        // Convert List<Eatery> to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String eateriesJson = null;
        try {
            eateriesJson = objectMapper.writeValueAsString(eateries);  // Convert List to JSON
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception if JSON conversion fails
        }  
        // Execute the SQL insert
        if (eateriesJson != null) {
            template.update(sql, searchTerm, eateries.size(), eateriesJson);  // Pass JSON string as parameter
        }
    }

    @Override
    public int getYelpCount(String searchTerm) {
        String sql = "SELECT search_count FROM yelp WHERE search_term = ?;";
        return template.queryForObject(sql, Integer.class, searchTerm);
    }

    @Override
    public List<Eatery> getEateries(String searchTerm) {
        String sql = "SELECT eateries FROM yelp WHERE search_term = ?;";
        try {
            SqlRowSet results = template.queryForRowSet(sql, searchTerm);
            List<Eatery> eateries = new ArrayList<>();

            ObjectMapper objectMapper = new ObjectMapper();

            while (results.next()) {
                String eateriesJson = results.getString("eateries");
                if (eateriesJson != null && !eateriesJson.isEmpty()) {
                    List<Eatery> eateryList = objectMapper.readValue(eateriesJson,
                            objectMapper.getTypeFactory().constructCollectionType(List.class, Eatery.class));
                    eateries.addAll(eateryList);
                }
            }
            return eateries;
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error processing JSON or database");
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
