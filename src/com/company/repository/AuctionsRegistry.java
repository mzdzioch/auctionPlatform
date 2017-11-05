package com.company.repository;

import com.company.helpers.FileOperation;
import com.company.model.Auction;
import com.company.model.Bid;
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

    public boolean addAuction(boolean active, String title, double price, int categoryID, String description, String login, List<Bid> listBids) {
        addAuction(new Auction(active, title, price, categoryID, description, login, listBids));
        return true;
    }

    public boolean addAuction(Auction auction) {
        new FileOperation().addLineToFile(fileAuctionsName, auctionToString(auction));
        idToAuctionMap.put(auction.getAuctionID(), auction);
        return true;
    }

    public ArrayList<Auction> getUserAuctions(User user) {
        ArrayList<Auction> userAuctions = new ArrayList<>();
        for (Auction auction : idToAuctionMap.values()) {
            if (auction.getLogin().equals(user.getLogin())) {
                userAuctions.add(auction);
            }
        }

        return userAuctions;
    }

    public ArrayList<Auction> getAllAuctionsUnderCategory(int categoryID) {
        ArrayList<Auction> categoryAuctions = new ArrayList<>();
        for (Auction auction : idToAuctionMap.values()) {
            if ((auction.getCategoryID() == categoryID) && (auction.isActive())) {
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
        for (Integer key : idToAuctionMap.keySet()) {
            if (key == auctionID) {
                idToAuctionMap.remove(key);
                writeAuctionsRegistryToFile();
                return true;
            }
        }
        return false;
    }

    private void readAuctionsRegistryToMemory() {
        try {
            ArrayList<String> auctionsFromFile = new FileOperation().readFile(fileAuctionsName);
            for (String auctionLine : auctionsFromFile) {
                Auction auction = parseAuction(auctionLine);
                idToAuctionMap.put(auction.getAuctionID(), auction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeAuctionsRegistryToFile() {

        FileOperation fileOperation = new FileOperation();
        fileOperation.createFile(fileAuctionsName);
        for (Auction auction : idToAuctionMap.values()) {
            fileOperation.addLineToFile(fileAuctionsName, auctionToString(auction));
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
        List<Bid> listBids = new ArrayList<>();
        for (int i = 7; i < 10; i++) {
            String bidInString =  auctionToArray[i];
            String[] bidInStringToArray = bidInString.split(", ");
            double bidPrice = Double.parseDouble(bidInStringToArray[1]);
            Bid bid = new Bid(bidInStringToArray[0], bidPrice);
            listBids.add(bid);
        }
        return new Auction(auctionID, active, title, price, categoryID, description, login, listBids);
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
                + auction.getListBids().get(1).getUser() + ", " + auction.getListBids().get(1).getBidPrice() + "|"
                + auction.getListBids().get(2).getUser() + ", " + auction.getListBids().get(2).getBidPrice() + "|"
                + auction.getListBids().get(3).getUser() + ", " + auction.getListBids().get(3).getBidPrice() + "|"
                + "\n";
    }


}