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

    private final Map<Integer, Auction> auctionsHashMap = new HashMap<Integer, Auction>();
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

    public void writeAuction(Auction auction) {
        FileOperation fileOperation = new FileOperation();
        String auctionToString = Integer.toString(auction.getAuctionID()) + "|"
                + auction.getTitle() + "|"
                + auction.getPrice() + "|"
                + auction.getCategoryID() + "|"
                + auction.getDescription() + "|"
                + auction.getLogin()
                + "\n";
        fileOperation.addLineToFile(fileAuctionsName, auctionToString);
        auctionsHashMap.put(auction.getAuctionID(), auction);
    }

    public Map<Integer, Auction> getAllAuctions() {
        return auctionsHashMap;
    }

    public boolean addAuction(String title, double price, int categoryID, String description, String login) {
        Auction addedAuction = new Auction(title, price, categoryID, description, login);
        writeAuction(addedAuction);
        auctionsHashMap.put(addedAuction.getAuctionID(), addedAuction);
        return true;
    }

    public List<Auction> getUserAuctions(User user) {
        List<Auction> listUserAuctions = new ArrayList<>();
        readAuctionsRegistryToMemory();
        for (Auction auction : auctionsHashMap.values()) {
            if (auction.getLogin().equals(user.getLogin())) {
                listUserAuctions.add(auction);
            }
        }

        return listUserAuctions;
    }

    public List<Auction> getAllAuctionsUnderCategory(int idCategory) {
        List<Auction> listAuctions = new ArrayList<>();
        readAuctionsRegistryToMemory();
        for (Auction auction : auctionsHashMap.values()) {
            if (auction.getCategoryID() == idCategory) {
                listAuctions.add(auction);
            }
        }

        return listAuctions;
    }

    public boolean removeAuction(int auctionID) {
        readAuctionsRegistryToMemory();
        for (Auction auction : auctionsHashMap.values()) {
            if (auction.getAuctionID() == auctionID) {
                auctionsHashMap.remove(auction);
                //auction must be also removed from file here
                System.out.println("Auction " + auctionID + "removed");
            }
            return true;
        }
        System.out.println("Incorrect auctionID");
        return false;
    }

    private void readAuctionsRegistryToMemory() {
        FileOperation fileOperation = new FileOperation();

        int auctionID;
        String title;
        double price;
        int categoryID;
        String description;
        String login;

        ArrayList<String> auctionsFromFile = new ArrayList<>();
        try {
            System.out.println(fileOperation.readFile(fileAuctionsName));
            auctionsFromFile = fileOperation.readFile(fileAuctionsName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : auctionsFromFile) {
            String[] auctionToArray = s.split("\\|");
            auctionID = Integer.parseInt(auctionToArray[0]);
            title = auctionToArray[1];
            price = Double.parseDouble(auctionToArray[2]);
            categoryID = Integer.parseInt(auctionToArray[3]);
            description = auctionToArray[4];
            login = auctionToArray[5];
            Auction auction = new Auction(auctionID, title, price, categoryID, description, login);
            auctionsHashMap.put(auctionID, auction);
        }
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
}
