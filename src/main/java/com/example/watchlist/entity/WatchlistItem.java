package com.example.watchlist.entity;


import com.example.watchlist.annotations.GoodMovie;
import com.example.watchlist.annotations.Priority;
import com.example.watchlist.annotations.Raiting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@GoodMovie

public class WatchlistItem {



    private Integer id;


    @NotBlank( message="Please enter the title")
    private String title;
    @Raiting

    private Integer rating;



    @Priority
    private String priority;

    @Size(max=50,  message="Comment should be maximum 50 characters")
    private String comment;

    public static int index = 0;

    public WatchlistItem() {
        this.id = index ++;
    }

    public WatchlistItem(String title, Integer rating, String priority, String comment) {
        super();
        this.id = index ++;
        this.title = title;
        this.rating = rating;
        this.priority = priority;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}