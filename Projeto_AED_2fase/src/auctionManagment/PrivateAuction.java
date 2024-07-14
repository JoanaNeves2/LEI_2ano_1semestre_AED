package auctionManagment;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.List;

import java.io.Serializable;

interface PrivateAuction extends Auction, Serializable {
    void insArtwork(String artworkID, Artwork a, int minVal);

    void insBid(String artworkID, Bid b);
}
