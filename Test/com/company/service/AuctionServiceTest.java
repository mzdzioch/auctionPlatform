package com.company.service;

import com.company.model.Auction;
import com.company.model.Bid;
import com.company.repository.AuctionsRegistry;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class AuctionServiceTest {

    String filename;
    Auction auction1;
    Auction auction2;
    Auction auction3;
    List<Bid> bid1, bid2, bid3;
    AuctionsRegistry auctionRegistry;
    AuctionService auctionService;

    @Before
    public void setUp() throws Exception{
        filename = "auctionSrvTst.txt";
        bid1 = new ArrayList<>();
        bid2 = new ArrayList<>();
        bid3 = new ArrayList<>();
        auction1 = new Auction(1, true, "Fiat Doblo", new BigDecimal(5000), 22, "bla bla bla", "tomek", bid1);
        auction2 = new Auction(2, true,"Toyota Yaris", new BigDecimal(4000), 22, "bla bla bla", "bartek", bid2);
        auction3 = new Auction(3, true, "Apple", new BigDecimal(20), 11, "Nowy laptop", "misiek", bid3);
        auctionRegistry = new AuctionsRegistry(filename);
        auctionService = new AuctionService(auctionRegistry);
    }

    @Test
    public void shouldBeEqualsTwoMapsOfAuctions() throws Exception {
        Map<Integer, Auction> expectedMap = new HashMap<>();
        expectedMap.put(1, auction1);
        expectedMap.put(2, auction2);

        auctionRegistry.writeAuction(auction1);
        auctionRegistry.writeAuction(auction2);

        assertEquals(expectedMap, auctionService.getListOfAuctions());

    }

    @Test
    public void shouldReturnTrueIfCategoryNumberIsCorrect() throws Exception {

        assertTrue(auctionService.validateCategoryNumber(11));
        assertTrue(auctionService.validateCategoryNumber(21));
        assertTrue(auctionService.validateCategoryNumber(24));
        assertTrue(auctionService.validateCategoryNumber(31));
        assertTrue(auctionService.validateCategoryNumber(33));
    }

    @Test
    public void shouldReturnFalseIfCategoryNumberIsNotCorrect() throws Exception {
        assertFalse(auctionService.validateCategoryNumber(14));
        assertFalse(auctionService.validateCategoryNumber(25));
        assertFalse(auctionService.validateCategoryNumber(34));
    }

    @Test
    public void shouldReturnTrueIfAuctionsBelongToCategory() throws Exception {
        auctionRegistry.writeAuction(auction3);

        assertTrue(auctionService.validateAuctionToMakeBid(2, 1));
        assertTrue(auctionService.validateAuctionToMakeBid(2, 2));
        assertTrue(auctionService.validateAuctionToMakeBid(1, 3));
    }

    @Test
    public void shouldReturnFalseIfAuctionsNotBelongToCategory() throws Exception {

        assertFalse(auctionService.validateAuctionToMakeBid(3, 1));
        assertFalse(auctionService.validateAuctionToMakeBid(1, 2));
        assertFalse(auctionService.validateAuctionToMakeBid(3, 3));
    }

    @Test
    public void shouldReturnTrueIfBidIsHigherThanActualPrice() throws Exception {

        assertTrue(auctionService.validateBid(new BigDecimal(5001.0), 1));
        assertTrue(auctionService.validateBid(new BigDecimal(4001.0), 2));
        assertTrue(auctionService.validateBid(new BigDecimal(21.0), 3));
    }

    @Test
    public void shouldReturnTrueIfBidIsHigherThanLatestPrice() throws Exception {

        auction1.getListBids().add(new Bid("michal", new BigDecimal(5001)));
        auction2.getListBids().add(new Bid("michal", new BigDecimal(4001)));
        auction3.getListBids().add(new Bid("michal", new BigDecimal(21)));

        assertTrue(auctionService.validateBid(new BigDecimal(5002.0), 1));
        assertTrue(auctionService.validateBid(new BigDecimal(4002.0), 2));
        assertTrue(auctionService.validateBid(new BigDecimal(22.0), 3));
    }

    @Test
    public void shouldReturnFalseIfBidIsLowerThanActualPrice() throws Exception {

        assertFalse(auctionService.validateBid(new BigDecimal(4999.9), 1));
        assertFalse(auctionService.validateBid(new BigDecimal(3999.9), 2));
        assertFalse(auctionService.validateBid(new BigDecimal(19.0), 3));
    }

    @Test
    public void shouldReturnFalseIfBidIsLowerThanLatestPrice() throws Exception {

        auctionService.makeWinningBid(1, new BigDecimal(5001.0), "michal");
        auctionService.makeWinningBid(2, new BigDecimal(4001.0), "michal");
        auctionService.makeWinningBid(3, new BigDecimal(21.0), "michal");

        assertThat(auctionService.validateBid(new BigDecimal(5001.0), 1)).isFalse();
        assertThat(auctionService.validateBid(new BigDecimal(4001.0), 2)).isFalse();
        assertThat(auctionService.validateBid(new BigDecimal(21.0), 3)).isFalse();
        //assertFalse(auctionService.validateBid(5001.0, 1));
        //assertFalse(auctionService.validateBid(4001.0, 2));
        //assertFalse(auctionService.validateBid(21.0, 3));
    }

    @Test
    public void shouldReturnSingleAuction() throws Exception {

        assertThat(auctionService.getSingleAuction(1)).isEqualToComparingOnlyGivenFields(auction1);
    }

    @Test
    public void makeWinningBid() throws Exception {
    }

//    @Test
//    public void removeAuction() throws Exception {
//    }
//
//    @Test
//    public void addAuction() throws Exception {
//    }

}