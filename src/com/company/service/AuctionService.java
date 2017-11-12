package com.company.service;

import com.company.helpers.CategoryBuilder;
import com.company.model.Auction;
import com.company.model.Bid;
import com.company.model.Category;
import com.company.model.User;
import com.company.repository.AuctionsRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionService {

    private AuctionsRegistry auctionsRegistry;
    private CategoryBuilder categoryBuilder;
    private List<Bid> bidList;

    private static final int MAX_NUMBER_OF_BIDS = 3;

    public AuctionService(AuctionsRegistry auctionsRegistry) {
        this.auctionsRegistry = auctionsRegistry;
        this.categoryBuilder = new CategoryBuilder();
        this.bidList = new ArrayList<>();
    }


    public Map<Integer, Auction> getListOfAuctions(){
        return auctionsRegistry.getAllAuctions();
    }

    public boolean validateCategoryNumber(int idCategory){
        
        return categoryBuilder.isParentExist(idCategory);
        
    }

    public boolean validateAuctionToMakeBid(int categoryNumber, int auctionNumber) {

        for (Auction auction : auctionsRegistry.getAllAuctionsUnderCategory(categoryNumber)) {
            if(auction.getAuctionID() == auctionNumber)
                return true;
        }

        return false;
    }

    public boolean validateBid(Double bidValue, int auctionNumber) {

        bidList = getBidList(getSingleAuction(auctionNumber));

        if(!bidList.isEmpty()) {
            if(bidValue > bidList.get(bidList.size() - 1).getBidPrice())
                return true;
            return false;
        } else if(bidValue > getSingleAuction(auctionNumber).getPrice() ) {
            return true;
        }

        return false;
    }

    public Auction getSingleAuction(int auctionId) {

        for (Auction auction : getListOfAuctions().values()) {
            if(auction.getAuctionID() == auctionId) {
                return auction;
            }
        }
        return null;
    }

    public boolean makeWinningBid(int auctionId, Double price, String user) {

        bidList = getBidList(getSingleAuction(auctionId));
        int numOfBids = bidList.size();

        if(numOfBids == MAX_NUMBER_OF_BIDS) {
            getSingleAuction(auctionId).setActive(false);
            bidList.add(new Bid(user, price));
            auctionsRegistry.updateAuction(getSingleAuction(auctionId));
            return true;
        }

        bidList.add(new Bid(user, price));
        auctionsRegistry.updateAuction(getSingleAuction(auctionId));
        return false;
    }

    public boolean removeAuction(int auctionId){
        return auctionsRegistry.removeAuction(auctionId);
    }

    public void addAuction(String auctionTitle, Double auctionPrice, int categoryNumber, String auctionDescription, String login) {
        Auction newAuction = new Auction(true, auctionTitle,  auctionPrice, categoryNumber,  auctionDescription, login);
        auctionsRegistry.writeAuction(newAuction);
    }

    private List<Bid> getBidList(Auction auction) {
        if(auction == null)
            return null;
        return auction.getListBids();
    }

}
