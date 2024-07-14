package auctionManagment;

import dataStructures.DoubleList;
import dataStructures.List;

import java.io.Serial;
import java.util.Objects;

class ArtworkClass implements PrivateArtwork {

    /**
     * Serial Version UID
     */
    @Serial
    private static final long serialVersionUID = 0L;

    final String artworkID;
    final int year;
    final String authorID;
    final String artworkName;
    final String authorName;
    int highestVal;
    List<Auction> auctions;

    ArtworkClass(String artworkID, String authorID, int year, String artworkName, String authorName) {
        this.artworkID = artworkID;
        this.year = year;
        this.authorID = authorID;
        this.artworkName = artworkName;
        this.authorName = authorName;
        this.highestVal = 0;
        this.auctions = new DoubleList<>();
    }

    @Override
    public String getArtworkID() {
        return artworkID;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String getAuthorID() {
        return authorID;
    }

    @Override
    public String getArtworkName() {
        return artworkName;
    }

    @Override
    public String getAuthorName() {
        return authorName;
    }

    @Override
    public int getHighestVal() {
        return highestVal;
    }

    @Override
    public void insAuction(Auction a){
        auctions.addLast(a);
    }

    @Override
    public void sell(int soldVal, Auction a){
        if (soldVal > highestVal) highestVal = soldVal;
        auctions.remove(a);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtworkClass that = (ArtworkClass) o;
        return year == that.year && highestVal == that.highestVal && Objects.equals(artworkID, that.artworkID)
                && Objects.equals(authorID, that.authorID) && Objects.equals(artworkName, that.artworkName) &&
                Objects.equals(authorName, that.authorName) && Objects.equals(auctions, that.auctions);
    }

    @Override
    public int compareTo(Artwork o) {
        if (this.getHighestVal() == o.getHighestVal())
            return o.getArtworkName().compareToIgnoreCase(this.getArtworkName());
        return this.getHighestVal() - o.getHighestVal();
    }
}
