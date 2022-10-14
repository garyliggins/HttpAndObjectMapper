package com.kenzie.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {
    // Initialize the required constant variable here
    static final String GET_URL = "";

    //Formats URL query string with one property
    public static String formatURL(String URLString, String parameter, String value) {
        //TODO: Fill out method and update return value

        return URLString + "?" + parameter + "=" + value;
    }

    //Overload method: Formats URL query string with two properties
    public static String formatURL(String URLString, String parameter1, String value1, String parameter2, String value2) {
        //TODO: Fill out method and update return value
        return URLString + "?" + parameter1 + "=" + value1 + "&" + parameter2 + "=" + value2;
    }

    public static String getURLResponse(String URLString) throws IOException {
        //TODO: Fill out method and update return value
        //does an HTTP GET and returns the JSON response if successful
        return makeGETRequest(URLString);
    }

    ;

    public static String makeGETRequest(String url) {
        // This returns the string response from the request
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                return String.format("GET request failed: %d status code received", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    public static String formatActivityOutput(String jsonString) throws com.fasterxml.jackson.core.JsonProcessingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
//
        ActivityDTO act = objectMapper.readValue(jsonString, ActivityDTO.class);
        String value = "Activity: "+act.activity+"\n";
        value += "Type: ";
        value += act.type;
        value+="\nParticipants: "+act.participants;
        value+="\nPrice: "+act.price;
        return value;


    }



    public static String getActivityRandom(String URL) { //where is this being called
        //TODO: Fill out method and update return value
        return makeGETRequest(URL);
    }

    public static String getActivityType(String URL, String type) throws com.fasterxml.jackson.core.JsonProcessingException,IOException{
        //TODO: Fill out method and update return value
        return getURLResponse(formatURL(URL,"type", String.valueOf(type)));


    }

    public static String getActivityWithPrice(String URL, double price) throws com.fasterxml.jackson.core.JsonProcessingException,IOException{
        //TODO: Fill out method and update return value
        return getURLResponse(formatURL(URL,"price", String.valueOf(price)));
    }

    public static String getActivityTypeForGroup(String URL, String type, int numParticipants) throws IOException {
        //TODO: Fill out method and update return value

        return getURLResponse(formatURL(URL,"type", String.valueOf(type),"numParticipants",String.valueOf(numParticipants)));

    }

    /** Do not modify main method **/
    public static void main(String[] args) throws IOException {
        String response;
        try {
            System.out.println("Are you feeling bored? Try these activities: ");
            
            //parse JSON string into formatted output
            System.out.println(formatActivityOutput(getActivityRandom(GET_URL)));
            System.out.println(formatActivityOutput(getActivityType(GET_URL, "education")));
            System.out.println(formatActivityOutput(getActivityWithPrice(GET_URL, 0)));
            System.out.println(formatActivityOutput(getActivityTypeForGroup(GET_URL, "recreational",4)));
            
            //Test for error checking: this last one does not have a return. Should print "No activity found"
            System.out.println(formatActivityOutput(getActivityType(GET_URL, "mayhem")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
