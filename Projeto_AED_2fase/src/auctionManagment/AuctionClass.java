package auctionManagment;

import dataStructures.*;

import java.io.Serial;

class AuctionClass implements PrivateAuction {
    /**
     * Serial Version UID
     */
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Default size to initialize the HashTables.
     */
    static final int DEFAULT_SIZE = 50;

    final String auctionID;

    final Dictionary<String, Artwork> artworks;

    final Dictionary<String, Integer> minValByArtwork;
    final List<Artwork> artworksByInsertionOrder;
    final List<Entry<String, List<Bid>>> bidsByArtwork;

    AuctionClass(String auctionID) {
        this.auctionID = auctionID;
        this.artworks = new SepChainHashTable<>(DEFAULT_SIZE);
        this.minValByArtwork = new SepChainHashTable<>(DEFAULT_SIZE);
        this.bidsByArtwork = new DoubleList<>();
        this.artworksByInsertionOrder = new DoubleList<>();
    }

    @Override
    public void insArtwork(String artworkID, Artwork a, int minVal) {
        if (artworks.find(artworkID.toLowerCase()) == null) {
            artworks.insert(artworkID.toLowerCase(), a);
            minValByArtwork.insert(artworkID.toLowerCase(), minVal);
            bidsByArtwork.addLast(new EntryClass<>(artworkID.toLowerCase(), new DoubleList<>()));
            artworksByInsertionOrder.addLast(a);
        }
    }

    @Override
    public boolean hasArtwork(String artworkID) {
        return artworks.find(artworkID.toLowerCase()) != null;
    }

    @Override
    public int minValOfArtwork(String artworkID) {
        return minValByArtwork.find(artworkID);
    }

    @Override
    public void insBid(String artworkID, Bid b) {
        Iterator<Entry<String, List<Bid>>> it = bidsByArtwork.iterator();
        while (it.hasNext()) {
            Entry<String, List<Bid>> next = it.next();
            if (next.getKey().equals(artworkID)) {
                List<Bid> temp = next.getValue();
                temp.addLast(b);
                next.setValue(temp);
            }
        }

    }

    @Override
    public Iterator<Entry<String, List<Bid>>> getBids() {
        return bidsByArtwork.iterator();
    }

    @Override
    public Iterator<Artwork> getArtworksByInsertionOrder() {
        return artworksByInsertionOrder.iterator();
    }

    @Override
    public boolean isAuctionEmpty() {
        return artworks.isEmpty();
    }

    @Override
    public String getAuctionID() {
        return auctionID;
    }
}
