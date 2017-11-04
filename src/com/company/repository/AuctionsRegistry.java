package com.company.repository;

import com.company.model.Auction;
import com.company.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionsRegistry {

    private Map<Integer, Auction> auctionsHashMap = new HashMap<Integer, Auction>();
    private String fileAuctionsName;

    public AuctionsRegistry(String fileAuctionsName) {
        this.fileAuctionsName = fileAuctionsName;
        File file = new File(fileAuctionsName);
        if (!file.exists()) {
            createAuctionsFile(fileAuctionsName);
        } else {
            readAuctionsRegistryToMemory();
        }
    }

    private void readAuctionsRegistryToMemory() {

        FileOperation fileOperation = new FileOperation();


        int auctionID;
        String title;
        double price;
        int categoryID;
        String description;
        String login;

        String line = fileOperation.readLineFromFile(fileAuctionsName);
        String[] auctionToArray;

        while (line != null) {
            auctionToArray = line.split("\\|");
            auctionID = Integer.parseInt(auctionToArray[0]);
            title = auctionToArray[1];
            price = Double.parseDouble(auctionToArray[2]);
            categoryID = Integer.parseInt(auctionToArray[3]);
            description = auctionToArray[4];
            login = auctionToArray[5];
            Auction auction = new Auction(auctionID, title, price, categoryID, description, login);
            auctionsHashMap.put(auctionID, auction);
            line = fileOperation.readLineFromFile(fileAuctionsName);

        }
//        } catch (IOException exception) {
//            exception.printStackTrace();
//
//        } finally {
//
//            try {
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
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

}
