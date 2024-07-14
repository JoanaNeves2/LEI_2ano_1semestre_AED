package auctionManagment;

public interface Artwork extends Comparable<Artwork> {
    String getArtworkID();

    int getYear();

    String getAuthorID();

    String getArtworkName();

    String getAuthorName();

    int getHighestVal();
}
