package auctionManagment;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.List;

public interface Auction {
    boolean hasArtwork(String artworkID);

    int minValOfArtwork(String artworkID);

    Iterator<Entry<String, List<Bid>>> getBids();

    Iterator<Artwork> getArtworksByInsertionOrder();

    boolean isAuctionEmpty();

    String getAuctionID();
}
