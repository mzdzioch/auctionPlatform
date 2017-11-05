package com.company.repository;

import com.company.helpers.FileOperation;
import com.company.model.Auction;
import com.company.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionsRegistry {

    private final Map<Integer, Auction> idToAuctionMap = new HashMap<>();
    private final String fileAuctionsName;

    public AuctionsRegistry(String fileAuctionsName) {
        this.fileAuctionsName = fileAuctionsName;
        File file = new File(fileAuctionsName);
        if (!file.exists()) {
            createAuctionsFile(fileAuctionsName);
        } else {
            readAuctionsRegistryToMemory();
        }
    }

    public Map<Integer, Auction> getAllAuctions() {
        return idToAuctionMap;
    }

    public boolean addAuction(boolean active, String title, double price, int categoryID, String description, String login) {
        addAuction(new Auction(active, title, price, categoryID, description, login));
        return true;
    }

    public boolean addAuction(Auction auction) {
        new FileOperation().addLineToFile(fileAuctionsName, auctionToString(auction));
        idToAuctionMap.put(auction.getAuctionID(), auction);
        return true;
    }

    public List<Auction> getUserAuctions(User user) {
        List<Auction> userAuctions = new ArrayList<>();
        for (Auction auction : idToAuctionMap.values()) {
            if (auction.getLogin().equals(user.getLogin())) {
                userAuctions.add(auction);
            }
        }

        return userAuctions;
    }

    public List<Auction> getAllAuctionsUnderCategory(int categoryID) {
        List<Auction> categoryAuctions = new ArrayList<>();
        for (Auction auction : idToAuctionMap.values()) {
            if (auction.getCategoryID() == categoryID) {
                categoryAuctions.add(auction);
            }
        }

        return categoryAuctions;
    }

    public ArrayList<Auction> getUserFinishedAuctionList(User user) {
        ArrayList<Auction> listUserAuctions = new ArrayList<>();
        readAuctionsRegistryToMemory();
        for (Auction auction : idToAuctionMap.values()) {
            if ((auction.getLogin().equals(user.getLogin()) && (auction.isActive()))) {
                listUserAuctions.add(auction);
            }
        }
        return listUserAuctions;
    }

    public boolean removeAuction(int auctionID) {
        for (Auction auction : idToAuctionMap.values()) {
            if (auction.getAuctionID() == auctionID) {
                idToAuctionMap.remove(auction);
                //TODO auction must be also removed from file here
                System.out.println("Auction " + auctionID + "removed");
            }
            return true;
        }
        System.out.println("Incorrect auctionID");
        return false;
    }

    private void readAuctionsRegistryToMemory() {
        try {
            List<String> auctionsFromFile = new FileOperation().readFile(fileAuctionsName);
            for (String auctionLine : auctionsFromFile) {
                Auction auction = parseAuction(auctionLine);
                idToAuctionMap.put(auction.getAuctionID(), auction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Auction parseAuction(String auctionLine) {
        String[] auctionToArray = auctionLine.split("\\|");

        int auctionID = Integer.parseInt(auctionToArray[0]);
        boolean active = Boolean.parseBoolean(auctionToArray[1]);
        String title = auctionToArray[2];
        double price = Double.parseDouble(auctionToArray[3]);
        int categoryID = Integer.parseInt(auctionToArray[4]);
        String description = auctionToArray[5];
        String login = auctionToArray[6];

        return new Auction(auctionID, active, title, price, categoryID, description, login);
    }

    private void createAuctionsFile(String fileName) {
        System.out.println("Trying to create file " + fileName);
        new FileOperation().createNewFile(fileName);
    }

    private String auctionToString(Auction auction) {
        return Integer.toString(auction.getAuctionID()) + "|"
                + auction.isActive() + "|"
                + auction.getTitle() + "|"
                + auction.getPrice() + "|"
                + auction.getCategoryID() + "|"
                + auction.getDescription() + "|"
                + auction.getLogin() + "|"
                + "\n";
    }


}