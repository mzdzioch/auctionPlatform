package com.company.service;

import com.company.exceptions.CredentialsToShortException;
import com.company.exceptions.LoginNullException;
import com.company.model.Auction;
import com.company.model.User;
import com.company.repository.AuctionsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuctionService {

    public AuctionsRegistry auctionsRegistry;
    private CategoryBuilder categoryBuilder;
    private List<Integer> categoriesList;

    public void writeAuction(Auction auction){
        auctionsRegistry.writeAuction(auction);
    }

    public Map<Integer, Auction> getListOfAuctions(){
        return auctionsRegistry.getListOfAuctions();
    }

    public boolean addAuction(String title, double price, int categoryID, String description, String login) {
        return auctionsRegistry.addAuction(title,price, categoryID, description, login);
    }

    public boolean removeAuction(int auctionId){
        return auctionsRegistry.removeAuction(auctionId);
    }

    public void printAuctions(User user){
        auctionsRegistry.printUserAuctions(user);
    }

    public void printAllAuctionsUnderCategory(int categoryId){
        categoryBuilder = new CategoryBuilder();
        categoriesList = new ArrayList<>();

        categoriesList = categoryBuilder.getCategoryAndSubcategoriesListId(categoryId);

        for (Integer subCategory : categoriesList) {
            //System.out.println("Auctions of Category: " + subCategory);
            auctionsRegistry.printAllAuctionsUnderCategory(subCategory);
        }

    }

}
