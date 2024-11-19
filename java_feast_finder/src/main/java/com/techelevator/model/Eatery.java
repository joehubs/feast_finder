package com.techelevator.model;

public class Eatery {
    private int eatery_id;
    private String eatery_name;
    private String image_url;
    private String eatery_address;
    private String category;
    private String website;
    private String open_time;
    private String close_time;
    private boolean has_takeout;
    private double rating;
    private String phone;
    private String price;
    private boolean isClosed;
    private String city;

    public Eatery() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Eatery(String category, String close_time, String eatery_address, String eatery_id, String eatery_name, String has_takeout, String image_url, String open_time, String phone, String price, String rating, String website, String isClosed, String city) {
        this.category = category;
        this.close_time = close_time;
        this.eatery_address = eatery_address;
        if(eatery_id != null)
            this.eatery_id = Integer.parseInt(eatery_id);
        this.eatery_name = eatery_name;
        if(has_takeout != null)
            this.has_takeout = Boolean.parseBoolean(has_takeout);
        this.image_url = image_url;
        this.open_time = open_time;
        this.phone = phone;
        this.price = price;
        if(rating != null)
            this.rating = Double.parseDouble(rating);
        this.website = website;
        if(isClosed != null)
            this.isClosed = Boolean.parseBoolean(isClosed);
        this.city = city;
    }

    public int getEatery_id() {
        return eatery_id;
    }

    public void setEatery_id(int eatery_id) {
        this.eatery_id = eatery_id;
    }

    public String getEatery_name() {
        return eatery_name;
    }

    public void setEatery_name(String eatery_name) {
        this.eatery_name = eatery_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getEatery_address() {
        return eatery_address;
    }

    public void setEatery_address(String eatery_address) {
        this.eatery_address = eatery_address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public boolean isHas_takeout() {
        return has_takeout;
    }

    public void setHas_takeout(boolean has_takeout) {
        this.has_takeout = has_takeout;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Eatery{");
        sb.append("eatery_id=").append(eatery_id);
        sb.append(", eatery_name=").append(eatery_name);
        sb.append(", image_url=").append(image_url);
        sb.append(", eatery_address=").append(eatery_address);
        sb.append(", category=").append(category);
        sb.append(", website=").append(website);
        sb.append(", open_time=").append(open_time);
        sb.append(", close_time=").append(close_time);
        sb.append(", has_takeout=").append(has_takeout);
        sb.append(", rating=").append(rating);
        sb.append(", phone=").append(phone);
        sb.append(", price=").append(price);
        sb.append(", isClosed=").append(isClosed);
        sb.append('}');
        return sb.toString();
    }
    
}
