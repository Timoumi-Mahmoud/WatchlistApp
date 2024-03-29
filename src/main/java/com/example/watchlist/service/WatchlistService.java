package com.example.watchlist.service;

import com.example.watchlist.domain.WatchlistItem;
import com.example.watchlist.exception.DuplicateTitleException;
import com.example.watchlist.repository.WatchlistRepository;

import java.util.List;

public class WatchlistService {

    WatchlistRepository watchlistRepository= new WatchlistRepository();
     MovieRatingService movieRatingService = new MovieRatingService();
    public List<WatchlistItem> getWatchlistItems(){
        List<WatchlistItem> watchlistItems= watchlistRepository.getList();
        for(WatchlistItem watchlistItem: watchlistItems)
        {
            String rating =movieRatingService.getMovieRating(watchlistItem.getTitle());
            if(rating != null){
                watchlistItem.setRating(rating);
            }
        }
        return watchlistItems;
    }

    public int getWatchlistItemsSize() {
        return watchlistRepository.getList().size();
    }

    public WatchlistItem findWatchlistItemById(Integer id) {
        return watchlistRepository.findById(id);
    }

    public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException {

        WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());

        if (existingItem == null) {
            if (watchlistRepository.findByTitle(watchlistItem.getTitle())!=null) {
                throw new DuplicateTitleException();
            }
            watchlistRepository.addItem(watchlistItem);
        } else {
            existingItem.setComment(watchlistItem.getComment());
            existingItem.setPriority(watchlistItem.getPriority());
            existingItem.setRating(watchlistItem.getRating());
            existingItem.setTitle(watchlistItem.getTitle());
        }
    }
}