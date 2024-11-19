package com.techelevator.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Eatery;


@Component
public class YelpService {
    @Value("${yelp.api.key}")
    private String apiKey;
    private final int limit = 50;
    @Value("${yelp.api.url}")
    private String apiUrl;

     public List<Eatery> getEateries(String searchString){
        String url = this.apiUrl + "?" + searchString + "&limit=" + this.limit;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.apiKey);
        headers.set("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        return buildEateryList(restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody());
    };
    
    private List<Eatery> buildEateryList(String jsonResponseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        List <Eatery> eateryList = new ArrayList<>();
        try {
            jsonNode = objectMapper.readTree(jsonResponseBody);
            JsonNode root = jsonNode.path("businesses");

            for(int i = 0; i < root.size(); i++){
                eateryList.add(mapEatery(root,i));
            }
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
        return eateryList;
    }

    private Eatery mapEatery(JsonNode root, int i) {
                String name = root.path(i).path("name").asText();
                String imageUrl = root.path(i).path("image_url").asText();
                String address =  buildAsset(root.path(i).path("location"));
                JsonNode categories = root.path(i).path("categories");
                String category = "";
                for(JsonNode cat : categories) {
                    category += cat.path("title").asText() + " ";
                }
                String openTime = "";
                String closeTime = "";
                for(JsonNode time : root.path(i).path("business_hours").path(0).path("open")) {
                    openTime += time.path("start").asText() + " ";
                    closeTime += time.path("end").asText() + " ";
                }
                String hasTakeout = buildAsset(root.path(i).path("transactions"));
                String price = root.path(i).path("price").asText();
                String rating = root.path(i).path("rating").asText();
                String phoneNumber = root.path(i).path("display_phone").asText();
                String website = root.path(i).path("attributes").path("menu_url").asText();
                String isClosed = root.path(i).path("is_closed").asText();
                String city = root.path(i).path("location").path("city").asText();
                return new Eatery(category, closeTime, address, null, name, hasTakeout, imageUrl, openTime, phoneNumber, price, rating, website, isClosed, city);
    }
    private String buildAsset(JsonNode assetArray) {
        String asset = "";
        for(JsonNode add : assetArray) {
            asset += add.asText() + " ";
        }
        return asset.trim();
    }
}
