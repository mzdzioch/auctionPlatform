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
        List<Auction> listUserAuctions = new ArrayList<>();
        readAuctionsRegistryToMemory();
        for (Auction auction : idToAuctionMap.values()) {
            if ((auction.getLogin().equals(user.getLogin()) && (auction.isActive()))) {
                listUserAuctions.add(auction);
            }
        }
        return (ArrayList<Auction>) listUserAuctions;
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
//            System.out.println(auctionsFromFile);

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
/*
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

        ArrayList<String> auctionsFromFile = new ArrayList<>();
        try {
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
                //TODO auction must be also removed from file here
                System.out.println("Auction " + auctionID + "removed");
            }
            return true;
        }
        System.out.println("Incorrect auctionID");
        return false;
    }

    public ArrayList<Auction> getUserFinishedAuctionList(User user) {
        List<Auction> listUserAuctions = new ArrayList<>();
        readAuctionsRegistryToMemory();
        for (Auction auction : auctionsHashMap.values()) {
            if ((auction.getLogin().equals(user.getLogin()) && (auction.getIsActive()))) {
                listUserAuctions.add(auction);
            }
        }
        return (ArrayList<Auction>) listUserAuctions;
    }
}
*/
