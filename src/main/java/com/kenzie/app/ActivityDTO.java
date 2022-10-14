package com.kenzie.app;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivityDTO {

    @JsonProperty("accessibility")
    public double accessibility;
    @JsonProperty("key")
    public String key;
    @JsonProperty("link")
    public String link;
    @JsonProperty("price")
    public double price;
    @JsonProperty("participants")
    public int participants;
    @JsonProperty("type")
    public String type;
    @JsonProperty("activity")
    public String activity;

//    getters and setters
    public double getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(double accessibility) {
        this.accessibility = accessibility;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
