package com.company.repository;

import com.company.helpers.CategoryBuilder;
import com.company.helpers.FileOperation;
import com.company.model.Auction;
import com.company.model.Bid;
import com.company.model.User;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionsRegistry {

    private final Map<Integer, Auction> idToAuctionMap = new HashMap<>();
    private final String fileAuctionsName;
    private List<Bid> bidList;

    private static final int MAX_NUMBER_OF_BIDS = 2;

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

    public boolean writeAuction(Auction auction) {
        new FileOperation().addLineToFile(fileAuctionsName, auctionToString(auction));
        idToAuctionMap.put(auction.getAuctionID(), auction);
        return true;
    }

    public boolean addAuction(boolean isActive, String title, BigDecimal price, int categoryID, String description, String login) {
        Auction newAuction = new Auction(isActive, title, price, categoryID, description, login);
        return writeAuction(newAuction);
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
        CategoryBuilder categoryBuilder = new CategoryBuilder();
        List<Integer> categoryList = categoryBuilder.getCategoryAndSubcategoriesListId(categoryID);

        ArrayList<Auction> categoryAuctions = new ArrayList<>();

        for (Integer categoryNum : categoryList) {
            for (Auction auction : idToAuctionMap.values()) {
                if ((auction.getCategoryID() == categoryNum) && (auction.isActive())) {
                    categoryAuctions.add(auction);
                }
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

    public void updateAuction(Auction auction){

        for (Integer integer : idToAuctionMap.keySet()) {
            if(auction.getAuctionID() == integer)
                idToAuctionMap.replace(integer, auction);
        }

        writeAuctionsRegistryToFile();
    }

    public boolean validateAuctionToMakeBid(int categoryNumber, int auctionNumber) {

        for (Auction auction : getAllAuctionsUnderCategory(categoryNumber)) {
            if(auction.getAuctionID() == auctionNumber)
                return true;
        }

        return false;
    }

    public Auction getSingleAuction(int auctionId) {

        for (Auction auction : getAllAuctions().values()) {
            if(auction.getAuctionID() == auctionId) {
                return auction;
            }
        }
        return null;
    }

    public boolean makeWinningBid(int auctionId, BigDecimal price, String user) {

        bidList = (getSingleAuction(auctionId)).getListBids();
        int numOfBids = bidList.size();

        if(numOfBids == MAX_NUMBER_OF_BIDS) {
            getSingleAuction(auctionId).setActive(false);
            bidList.add(new Bid(user, price));
            updateAuction(getSingleAuction(auctionId));
            return true;
        }

        bidList.add(new Bid(user, price));
        updateAuction(getSingleAuction(auctionId));
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
//        for (String s : auctionToArray) {
//            System.out.println(s);
//        }
/*        String[] bidInStringToArrayHelper7 = auctionToArray[7].split(",");
        System.out.println("First bid");
        for (String s : bidInStringToArrayHelper7) {
            System.out.println(s);
        }


        String[] bidInStringToArrayHelper8 = auctionToArray[8].split(",");
        System.out.println("Second bid");
        for (String s : bidInStringToArrayHelper8) {
            System.out.println(s);
        }

        String[] bidInStringToArrayHelper9 = auctionToArray[9].split(",");
        System.out.println("Third bid");
        for (String s : bidInStringToArrayHelper9) {
            System.out.println(s);
        }*/


        int auctionID = Integer.parseInt(auctionToArray[0]);
        boolean active = Boolean.parseBoolean(auctionToArray[1]);
        String title = auctionToArray[2];
        BigDecimal price = new BigDecimal(auctionToArray[3]);
        int categoryID = Integer.parseInt(auctionToArray[4]);
        String description = auctionToArray[5];
        String login = auctionToArray[6];
        List<Bid> listBids = new ArrayList<>();

        for (int i = 7; i < 10; i++) {

            String bidInString =  auctionToArray[i];
            String[] bidInStringToArray = bidInString.split(",");

            //System.out.println("Table length " + bidInStringToArray.length);
            if (bidInStringToArray.length>0 && !bidInStringToArray[1].equals("")) {

                BigDecimal bidPrice = new BigDecimal(bidInStringToArray[1]);
                Bid bid = new Bid(bidInStringToArray[0], bidPrice);
                listBids.add(bid);
            }
        }
        return new Auction(auctionID, active, title, price, categoryID, description, login, listBids);
    }

    private void createAuctionsFile(String fileName) {
        System.out.println("Trying to create file " + fileName);
        new FileOperation().createNewFile(fileName);
    }

    private String auctionToString(Auction auction) {
        String auctionToLine;
        auctionToLine = Integer.toString(auction.getAuctionID()) + "|"
                + auction.isActive() + "|"
                + auction.getTitle() + "|"
                + auction.getPrice() + "|"
                + auction.getCategoryID() + "|"
                + auction.getDescription() + "|"
                + auction.getLogin() + "|";

        if (!auction.getListBids().isEmpty()) {
            if (auction.getListBids().size() > 0) {
                auctionToLine += auction.getListBids().get(0).getUser() + "," + auction.getListBids().get(0).getBidPrice() + "|";
                if (auction.getListBids().size() > 1) {
                    auctionToLine += auction.getListBids().get(1).getUser() + "," + auction.getListBids().get(1).getBidPrice() + "|";
                    if (auction.getListBids().size() > 2) {
                        auctionToLine += auction.getListBids().get(2).getUser() + "," + auction.getListBids().get(2).getBidPrice() + "|";
                    } else {
                        auctionToLine += ",|";
                    }
                } else {
                    auctionToLine += ",|,|";
                }
            } else {
                auctionToLine += ",|,|,|";
            }

        } else auctionToLine += ",|,|,|";

         auctionToLine += "\n";

        return auctionToLine;
    }
}