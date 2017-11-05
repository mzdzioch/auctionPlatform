package com.company.view;

import com.company.model.Auction;
import com.company.model.User;
import com.company.repository.AuctionsRegistry;
import com.company.helpers.CategoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuctionView {

    public AuctionsRegistry auctionsRegistry;
    private CategoryBuilder categoryBuilder;
    private List<Integer> categoriesList;
    private List<Auction> listUserAuction, listCategoryAuctions;
    private Map<Integer, Auction> listAllAuctions;


    public AuctionView(AuctionsRegistry auctionsRegistry) {
        this.auctionsRegistry = auctionsRegistry;
    }

    public void printAuctions(User user){

        listUserAuction = auctionsRegistry.getUserAuctions(user);
        for (Auction auction : listUserAuction) {
            System.out.println(auction.toString());
        }
    }

    public void printInactiveAuctions(){
        listAllAuctions = auctionsRegistry.getAllAuctions();

        for (Auction auction : listAllAuctions.values()) {
            if(auction.isActive())
                System.out.println(auction.toString());
        }

    }

    public void printActiveAuctions(){
        listAllAuctions = auctionsRegistry.getAllAuctions();

        for (Auction auction : listAllAuctions.values()) {
            if(!auction.isActive())
                System.out.println(auction.toString());
        }

    }


    public void printAllAuctionsUnderCategory(int idCategory){
        categoryBuilder = new CategoryBuilder();
        categoriesList = new ArrayList<>();
        listCategoryAuctions = new ArrayList<>();

        categoriesList = categoryBuilder.getCategoryAndSubcategoriesListId(idCategory);

        for (Integer subCategory : categoriesList) {

            listCategoryAuctions = auctionsRegistry.getAllAuctionsUnderCategory(idCategory);

            for (Auction listCategoryAuction : listCategoryAuctions) {
                System.out.println(listCategoryAuction.toString());
            }

        }

    }
}
