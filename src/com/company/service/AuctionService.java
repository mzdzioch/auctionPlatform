package com.company.service;

import com.company.model.Auction;
import com.company.model.User;
import com.company.repository.AuctionsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuctionService {

    public AuctionsRegistry auctionsRegistry;


    public AuctionService(AuctionsRegistry auctionsRegistry) {
        this.auctionsRegistry = auctionsRegistry;
    }


    public void writeAuction(Auction auction){
        auctionsRegistry.writeAuction(auction);
    }

    public Map<Integer, Auction> getListOfAuctions(){
        return auctionsRegistry.getAllAuctions();
    }

    public boolean addAuction(String title, double price, int categoryID, String description, String login) {
        return auctionsRegistry.addAuction(title,price, categoryID, description, login);
    }

    public boolean removeAuction(int auctionId){
        return auctionsRegistry.removeAuction(auctionId);
    }

//    public void printAuctions(User user){
//        //auctionsRegistry.printUserAuctions(user);
//    }



}
