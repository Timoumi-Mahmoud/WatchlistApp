package com.example.watchlist.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
public class MovieRatingService {

    String apiUrl="http://www.omdbapi.com/?apikey=2288031e&t=";

//"ctrl+alt+t" -> "6" :: try catch

    public String getMovieRating(String title) {

        try {
            //RestTemplate:  htttp clietn like webBrower to hit apiUrl

            RestTemplate template = new RestTemplate();
//has method getForEntity ===> send a get request to url
            //Object.Node store json object
            ResponseEntity<ObjectNode> response =
                    template.getForEntity(apiUrl + title, ObjectNode.class);

            ObjectNode jsonObject = response.getBody();

            return jsonObject.path("imdbRating").asText();
        } catch (Exception e) {
            System.out.println("Something went wrong while calling OMDb API" + e.getMessage());
            return null;
        }
    }

}