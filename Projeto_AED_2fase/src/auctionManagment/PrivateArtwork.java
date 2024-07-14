package auctionManagment;

import java.io.Serializable;

interface PrivateArtwork extends Artwork, Serializable {
    void insAuction(Auction a);

    void sell(int soldVal, Auction a);
}
