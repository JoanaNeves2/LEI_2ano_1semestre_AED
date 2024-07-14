package auctionManagment;

import dataStructures.Entry;
import dataStructures.Iterator;

import java.io.Serializable;

interface PrivateArtist extends Artist, Serializable {
    void insArtwork(String artworkID, Artwork a);

    void insArtworkInAuction(Artwork art, Auction a);

    void remArtworkFromAuction(Artwork art, Auction a);
}
