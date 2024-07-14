package auctionManagment;

import dataStructures.*;

class ArtistClass extends AbstractUserClass implements PrivateArtist {

    final String artisticName;

    OrderedDictionary<String, Artwork> artworks;
    OrderedDictionary<String, Artwork> artworksOrderedByName;
    Dictionary<String, Dictionary<String, Auction>> artworksInAuction;

    /**
     * Constructs a new artist calling the super that initializes the instance variables.
     *
     * @param userID       the unique identifier of the user.
     * @param username     the name of the user.
     * @param artisticName the artistic name of the artist.
     * @param age          the age of the user.
     * @param email        the email of the user.
     */
    ArtistClass(String userID, String username, String artisticName, int age, String email) {
        super(userID, username, age, email);
        this.artisticName = artisticName;
        this.artworks = new AVLTree<>();
        this.artworksOrderedByName = new AVLTree<>();
        this.artworksInAuction = new SepChainHashTable<>();
    }

    @Override
    public Iterator<Entry<String, Artwork>> getArtworks() {
        return artworks.iterator();
    }

    @Override
    public boolean hasArtworksInAuction() {
        return !artworksInAuction.isEmpty();
    }

    @Override
    public void insArtwork(String artworkID, Artwork a) {
        artworks.insert(artworkID, a);
        artworksOrderedByName.insert(a.getArtworkName(), a);
    }

    @Override
    public String getArtisticName() {
        return artisticName;
    }

    @Override
    public void insArtworkInAuction(Artwork art, Auction a){
        Dictionary<String, Auction> temp = artworksInAuction.find(art.getArtworkID());
        if (temp == null) temp = new SepChainHashTable<>();
        temp.insert(a.getAuctionID(), a);
        artworksInAuction.insert(art.getArtworkID(), temp);
    }

    @Override
    public void remArtworkFromAuction(Artwork art, Auction a) {
        Dictionary<String, Auction> temp = artworksInAuction.find(art.getArtworkID());
        temp.remove(a.getAuctionID());
        if (temp.isEmpty()) artworksInAuction.remove(art.getArtworkID());
        else artworksInAuction.insert(art.getArtworkID(), temp);
    }

    @Override
    public Iterator<Entry<String, Artwork>> getArtworksOrderedByName(){
        return artworksOrderedByName.iterator();
    }
}
