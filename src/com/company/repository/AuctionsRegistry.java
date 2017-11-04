package com.company.repository;

import com.company.helpers.FileOperation;
import com.company.model.Auction;
import com.company.model.User;

import java.io.File;
import java.io.FileWriter;
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

    public boolean addAuction(String title, double price, int categoryID, String description, String login) {
        addAuction(new Auction(title, price, categoryID, description, login));
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

    public boolean removeAuction(int auctionID) {
        for (Auction auction : idToAuctionMap.values()) {
            if (auction.getAuctionID() == auctionID) {
                idToAuctionMap.remove(auction);
                //auction must be also removed from file here
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
            System.out.println(auctionsFromFile);

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
        String title = auctionToArray[1];
        double price = Double.parseDouble(auctionToArray[2]);
        int categoryID = Integer.parseInt(auctionToArray[3]);
        String description = auctionToArray[4];
        String login = auctionToArray[5];

        return new Auction(auctionID, title, price, categoryID, description, login);
    }

    private void createAuctionsFile(String fileName) {
        FileWriter fw = null;
        System.out.println("Trying to create file " + fileName);

        try {
            fw = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String auctionToString(Auction auction) {
        return Integer.toString(auction.getAuctionID()) + "|"
                + auction.getTitle() + "|"
                + auction.getPrice() + "|"
                + auction.getCategoryID() + "|"
                + auction.getDescription() + "|"
                + auction.getLogin()
                + "\n";
    }
}
