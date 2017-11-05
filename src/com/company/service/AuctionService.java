package com.company.service;

import com.company.model.Auction;
import com.company.model.Bid;
import com.company.model.User;
import com.company.repository.AuctionsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuctionService {

    private AuctionsRegistry auctionsRegistry;
    private
    private Auction auction;
    List<Bid> bidList;
    private int bidCounter = 0;
    private static final int MAX_NUMBER_OF_BIDS = 3;

    public AuctionService(AuctionsRegistry auctionsRegistry) {
        this.auctionsRegistry = auctionsRegistry;
    }

//    public void writeAuction(Auction auction){
//        auctionsRegistry.addAuction(auction);
//    }

    public Map<Integer, Auction> getListOfAuctions(){
        return auctionsRegistry.getAllAuctions();
    }

    public boolean validateCategoryNumber(int idCategory){

        return false;
    }



    public boolean addAuction(String title, double price, int categoryID, String description, String login) {
        return auctionsRegistry.addAuction(true, title, price, categoryID, description, login);
    }

    public boolean removeAuction(int auctionId){
        return auctionsRegistry.removeAuction(auctionId);
    }

    public boolean placeBid(int auctionId, Double price, User user) {

        auction = findAuction(auctionId);
        bidList = new ArrayList<>();
        bidList = auction.getListBids();

        if(bidList.isEmpty()) {
            bidCounter++;
            return bidList.add(new Bid(user, price));

        } else if(bidCounter <= MAX_NUMBER_OF_BIDS){
            bidCounter++;
            if(price > bidList.get((bidList.size() - 1)).getBidPrice()){
                return bidList.add(new Bid(user, price));
            }

        }

        return false;
    }

    public boolean checkIfPlaceToBid(){
        if(this.bidCounter <= MAX_NUMBER_OF_BIDS)
            return true;

        return false;
    }

    public boolean checkIfPriceHigherThan(double priceToBid) {
        if(priceToBid > getBestBidPrice())
    }

    private double getBestBidPrice(){
        if(getLastBid() != null)
        return getLastBid().getBidPrice();
        else return 0;
    }

    private Bid getLastBid(){
        return bidList.get(bidList.size()-1);
    }

    private List<Bid> getBidList(Auction auction) {
        return auction.getListBids();
    }

    private Auction findAuction(int auctionId){
        for (Auction auction : getListOfAuctions().values()) {
            if(auction.getAuctionID() == auctionId) {
                return auction;
            }
        }
        return null;
    }


}
