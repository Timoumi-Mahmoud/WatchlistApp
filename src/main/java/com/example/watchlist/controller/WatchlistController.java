package com.example.watchlist.controller;

import com.example.watchlist.domain.WatchlistItem;
import com.example.watchlist.exception.DuplicateTitleException;
import com.example.watchlist.service.WatchlistService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WatchlistController {
    private List<WatchlistItem> watchlistItems =new ArrayList<WatchlistItem>();
    private static int index =1;


/*
@GetMapping("/watchlist")
    //Handler methode return 2 thing method and  view name
    public ModelAndView getWatchlist(){
watchlistItems.clear();

    watchlistItems.add(new WatchlistItem(index++,"first", "8.5", "high", "first"));
    watchlistItems.add(new WatchlistItem(index++,"first", "8.5", "high", "first"));
    watchlistItems.add(new WatchlistItem(index++,"first", "8.5", "high", "first"));
    watchlistItems.add(new WatchlistItem(index++,"first", "8.5", "high", "first"));




      String viewName="watchlist";
    Map<String, Object> model   = new HashMap<String, Object>();
    model.put("numberOfMovies", watchlistItems.size());
    model.put("whatchlistItems", watchlistItems);
    return  new ModelAndView(viewName,model);
    }
*/

    /*

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist(@RequestParam(required = false) Integer id) {

        String viewName = "watchlist";
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("whatchlistItems", watchlistItems);
        model.put("numberOfMovies", watchlistItems.size());

        return new ModelAndView(viewName , model);
    }


    //  Show an empty  Form

 /*   public ModelAndView showWatchlistItemForm(@RequestParam(required=false) Integer id){

    String viewName="watchlistItemForm";
    Map<String, Object> model=new HashMap<String,Object>();
    WatchlistItem watchlistItem =findWatchlistItemById(id);
    if(watchlistItem == null){
        model.put("watchlistItem", new WatchlistItem());
    }else{
        model.put("watchlistItem", watchlistItem);
    }
    return new ModelAndView(viewName, model);
    }
*/

    /*
 @GetMapping("/watchlistItemForm")
 public ModelAndView showWatchlistItemForm(@RequestParam(required=false) Integer id) {
     Map<String, Object> model = new HashMap<String, Object>();

     WatchlistItem watchlistItem = findWatchlistItemById(id);
     if (watchlistItem == null) {
         model.put("watchlistItem", new WatchlistItem());
     } else {
         model.put("watchlistItem", watchlistItem);
     }
     return new ModelAndView("watchlistItemForm" , model);
 }

    private WatchlistItem findWatchlistItemById(Integer id) {
        for (WatchlistItem watchlistItem : watchlistItems) {
            if (watchlistItem.getId().equals(id)) {
                return watchlistItem;
            }
        }
        return null;
    }
    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {

     if(bindingResult.hasErrors()){
         return new ModelAndView("watchlistItemForm");
     }
if(itemAlreadyExists((watchlistItem.getTitle()))){
    bindingResult.rejectValue("title", "", "This title already exists on your whatch list !");
    return new ModelAndView("watchlistItemForm");

}






        WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());

        if (existingItem == null) {
            watchlistItem.setId(index++);
            watchlistItems.add(watchlistItem);
        } else {
            existingItem.setComment(watchlistItem.getComment());
            existingItem.setPriority(watchlistItem.getPriority());
            existingItem.setRating(watchlistItem.getRating());
            existingItem.setTitle(watchlistItem.getTitle());
        }

        RedirectView redirect = new RedirectView();
        redirect.setUrl("/watchlist");

        return new ModelAndView(redirect);
    }


    private boolean itemAlreadyExists(String title) {

        for (WatchlistItem watchlistItem : watchlistItems) {
            if (watchlistItem.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
/*
   private  WatchlistItem findWatchlistItemById(Integer id){
        for(WatchlistItem watchlistItem : watchlistItems) {
            if(watchlistItem.getId().equals(id)) {
                return watchlistItem;
            }
        }
   return null;

    }*/


    private WatchlistService watchlistService = new WatchlistService();

    @GetMapping("/watchlistItemForm")
    public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id) {

        String viewName = "watchlistItemForm";

        Map<String,Object> model = new HashMap<String,Object>();

        WatchlistItem watchlistItem = watchlistService.findWatchlistItemById(id);

        if (watchlistItem == null) {
            model.put("watchlistItem", new WatchlistItem());
        } else {
            model.put("watchlistItem", watchlistItem);
        }
        return new ModelAndView(viewName,model);
    }

    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("watchlistItemForm");
        }

        try {
            watchlistService.addOrUpdateWatchlistItem(watchlistItem);
        } catch (DuplicateTitleException e) {
            bindingResult.rejectValue("title", "", "This title already exists on your watchlist");
            return new ModelAndView("watchlistItemForm");
        }

        RedirectView redirect = new RedirectView();
        redirect.setUrl("/watchlist");

        return new ModelAndView(redirect);
    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {

        String viewName= "watchlist";

        Map<String,Object> model = new HashMap<String,Object>();

        model.put("watchlistItems", watchlistService.getWatchlistItems());
        model.put("numberOfMovies", watchlistService.getWatchlistItemsSize());

        return new ModelAndView(viewName,model);
    }
}

