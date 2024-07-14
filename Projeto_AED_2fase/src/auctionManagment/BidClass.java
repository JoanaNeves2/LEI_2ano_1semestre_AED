package auctionManagment;

import java.io.Serial;

class BidClass implements PrivateBid{

    /**
     * Serial Version UID
     */
    @Serial
    private static final long serialVersionUID = 0L;

    final String userID;
    final String artworkID;
    final String username;
    final int val;
   BidClass(String userID, String artworkID, String username, int val) {
        this.userID = userID;
        this.artworkID = artworkID;
        this.username = username;
        this.val = val;
    }

    @Override
    public int getVal() {
        return val;
    }

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getArtworkID() {
        return artworkID;
    }
}
