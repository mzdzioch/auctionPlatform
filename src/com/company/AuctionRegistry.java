package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuctionRegistry {

    private Map<Integer, Auction> listOfAuctions = new HashMap<Integer, Auction>();
    private String fileName;

    public AuctionRegistry(Map<Integer, Auction> listOfAuctions) {
        this.listOfAuctions = listOfAuctions;
        File file = new File(fileName);
        if (!file.exists()) {
            createAuctionsFile(fileName);
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
                    + auction.getTitle() + "|"
                    + auction.getPrice() + "|"
                    + auction.getCategoryID() + "|"
                    + auction.getDescription() + "|"
//                    + auction.getLogin() +
                    + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw !=null)
                    bw.close();

                if (fw !=null)
                    fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
