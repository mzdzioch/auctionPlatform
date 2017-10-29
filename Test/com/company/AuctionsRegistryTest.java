package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AuctionsRegistryTest {

    String fileAuctionsName;
    Auction auction1;
    Auction auction0;


    //    Auction auction2;
    AuctionsRegistry auctionsRegistry;

    @Before
    public void setUp() throws Exception {
        fileAuctionsName = "auctions.txt";
//        auction0 = new Auction("Toyota", 14000, 10, "bla bla bla", "bartek");

        auction1 = new Auction("Toyota", 14000, 10, "bla bla bla", "bartek");
//        auction2 = new Auction(2, "Fiat", 5000, 10, "bla bla bla", "tomek");
        auctionsRegistry = new AuctionsRegistry(fileAuctionsName);
    }

    @Test
    public void writeAuction() throws Exception {

        AuctionsRegistry auctionsRegistry = new AuctionsRegistry(fileAuctionsName);
        auctionsRegistry.writeAuction(auction1);
//        auctionsRegistry.writeAuction(auction2);
        assertTrue(auctionsRegistry.getListOfAuctions().get(auction1.getCategoryID()).equals(10));
//        assertTrue(auctionsRegistry.getListOfAuctions().get(2).getAuctionID() == 2);
    }


}
