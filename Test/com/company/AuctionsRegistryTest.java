package com.company;

import com.company.model.Auction;
import com.company.repository.AuctionsRegistry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AuctionsRegistryTest {

    String fileAuctionsName;
    Auction auction1;
    Auction auction0;
    AuctionsRegistry auctionsRegistry;

    @Before
    public void setUp() throws Exception {
        fileAuctionsName = "auctions.txt";
        auction0 = new Auction( "Fiat", 5000, 10, "bla bla bla", "tomek");
        auction1 = new Auction("Toyota", 14000, 10, "bla bla bla", "bartek");
        auctionsRegistry = new AuctionsRegistry(fileAuctionsName);
    }

    @Test
    public void writeAuction() throws Exception {

        AuctionsRegistry auctionsRegistry = new AuctionsRegistry(fileAuctionsName);
        auctionsRegistry.writeAuction(auction0);
        auctionsRegistry.writeAuction(auction1);
        assertTrue(auctionsRegistry.getAllAuctions().get(1).getCategoryID() == 10);
        assertTrue(auctionsRegistry.getAllAuctions().get(2).getCategoryID() == 10);
    }


}
