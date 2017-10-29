package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AuctionRegistry {

    private Map<Integer, Auction> listOfAuctions = new HashMap<Integer, Auction>();
    private String fileName;

    public AuctionRegistry(String fileName) {
        this.fileName = fileName;
        File file = new File(fileName);
        if (!file.exists()) {
            createAuctionsFile(fileName);
        } else {
            readAuctionsRegistryToMemory(fileName);
        }
    }

    private void readAuctionsRegistryToMemory(String fileAuctionsName) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileAuctionsName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            int auctionID;
//            int counter;
            String title;
            double price;
            int categoryID;
            String description;
            String login;

            String line = br.readLine();
            String[] auctionToArray;

            while (line != null) {
                auctionToArray = line.split("|");
                auctionID = Integer.parseInt(auctionToArray[0]);
//            int counter;
                title = auctionToArray[1];
                price = Double.parseDouble(auctionToArray[2]);
                categoryID = Integer.parseInt(auctionToArray[3]);
                ;
                description = auctionToArray[4];
                login = auctionToArray[5];
                Auction auction = new Auction(auctionID, title, price, categoryID, description, login);
                listOfAuctions.put(auctionID, auction);
                line = br.readLine();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {

            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createAuctionsFile(String filename) {

        FileWriter fw = null;

        try {
            fw = new FileWriter(filename);
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

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(fileName, true);
            bw = new BufferedWriter(fw);
            bw.write(auction.getAuctionID() + "|"
//                    + auction.getCounter() + "|"
                    + auction.getTitle() + "|"
                    + auction.getPrice() + "|"
                    + auction.getCategoryID() + "|"
                    + auction.getDescription() + "|"
                    + auction.getLogin()
                    + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
