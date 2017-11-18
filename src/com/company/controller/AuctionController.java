package com.company.controller;

import com.company.helpers.CategoryBuilder;
import com.company.model.Auction;
import com.company.model.User;
import com.company.repository.AuctionsRegistry;
import com.company.view.AuctionView;

import java.math.BigDecimal;

public class AuctionController {

    private AuctionsRegistry auctionsRegistry;
    private CategoryBuilder categoryBuilder;
    private AuctionView auctionView;

    public AuctionController(AuctionsRegistry auctionsRegistry) {
        this.auctionsRegistry = auctionsRegistry;
        this.categoryBuilder = new CategoryBuilder();
        this.auctionView = new AuctionView(auctionsRegistry);
    }

    public boolean validateCategoryNumber(int idCategory){
        return categoryBuilder.isParentExist(idCategory);
    }

    public boolean validateAuctionToMakeBid(int categoryNumber, int auctionNumber) {
        return auctionsRegistry.validateAuctionToMakeBid(categoryNumber, auctionNumber);
    }

    public boolean validateBid(BigDecimal bidValue, int auctionNumber) {
        return getSingleAuction(auctionNumber).validateBid(bidValue);
    }

    public Auction getSingleAuction(int auctionId) {
        return auctionsRegistry.getSingleAuction(auctionId);
    }

    public boolean makeWinningBid(int auctionId, BigDecimal price, String user) {
        return auctionsRegistry.makeWinningBid(auctionId, price, user);
    }

    public boolean removeAuction(int auctionId){
        return auctionsRegistry.removeAuction(auctionId);
    }

    public void addAuction(String auctionTitle, BigDecimal auctionPrice, int categoryNumber, String auctionDescription, String login) {
        Auction newAuction = new Auction(true, auctionTitle,  auctionPrice, categoryNumber,  auctionDescription, login);
        auctionsRegistry.writeAuction(newAuction);
    }

    public void printAuctions(User user){
        auctionView.printAuctions(user);
    }

    public void printInactiveAuctions(){
        auctionView.printInactiveAuctions();
    }

    public void printActiveAuctions() {
        auctionView.printActiveAuctions();
    }

    public void printAllAuctionsUnderCategory(int idCategory){
        auctionView.printAllAuctionsUnderCategory(idCategory);
    }

}
