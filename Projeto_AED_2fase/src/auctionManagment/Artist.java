package auctionManagment;

import dataStructures.Entry;
import dataStructures.Iterator;

public interface Artist extends User{
    boolean hasArtworksInAuction();

    Iterator<Entry<String, Artwork>> getArtworks();

    String getArtisticName();

    Iterator<Entry<String, Artwork>> getArtworksOrderedByName();
}
