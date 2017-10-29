package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AuctionsRegistry {

    private Map<Integer, Auction> listOfAuctions = new HashMap<Integer, Auction>();
    private String fileAuctionsName;

    public AuctionsRegistry(String fileAuctionsName) {
        this.fileAuctionsName = fileAuctionsName;
        File file = new File(fileAuctionsName);
        if (!file.exists()) {
            createAuctionsFile(fileAuctionsName);
        } else {
            readAuctionsRegistryToMemory(fileAuctionsName);
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
            String title;
            double price;
            int categoryID;
            String description;
            String login;

            String line = br.readLine();
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

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(fileAuctionsName, true);
            bw = new BufferedWriter(fw);
            bw.write(Integer.toString(auction.getAuctionID()) + "|"
                    + auction.getTitle() + "|"
                    + auction.getPrice() + "|"
                    + auction.getCategoryID() + "|"
                    + auction.getDescription() + "|"
                    + auction.getLogin()
                    + "\n");
            listOfAuctions.put(auction.getAuctionID(), auction);
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

    public Map<Integer, Auction> getListOfAuctions() {
        return listOfAuctions;
    }
}
