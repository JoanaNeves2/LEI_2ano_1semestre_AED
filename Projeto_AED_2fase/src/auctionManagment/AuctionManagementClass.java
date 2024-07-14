package auctionManagment;

import dataStructures.*;
import exceptions.*;

import java.io.Serial;

public class AuctionManagementClass implements AuctionManagement {

    /**
     * Serial Version UID
     */
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Default size to initialize the HashTables.
     */
    static final int DEFAULT_SIZE = 50;

    static final int MINIMUM_AGE = 18;

    Dictionary<String, PrivateUser> users;
    Dictionary<String, PrivateArtwork> artworks;
    Dictionary<String, OrderedDictionary<String, PrivateArtwork>> artworksByArtistOrderedByName;
    Dictionary<String, PrivateAuction> auctions;

    public AuctionManagementClass() {
        this.users = new SepChainHashTable<>(DEFAULT_SIZE);
        this.artworks = new SepChainHashTable<>(DEFAULT_SIZE);
        this.artworksByArtistOrderedByName = new SepChainHashTable<>(DEFAULT_SIZE);
        this.auctions = new SepChainHashTable<>(DEFAULT_SIZE);
    }

    /**
     * @param userAge the age of the user.
     * @return true if the user is below the MINIMUM_AGE otherwise returns false.
     */
    private boolean isUserUnderage(int userAge) {
        return userAge < MINIMUM_AGE;
    }

    /**
     * Returns the user with the given user identifier.
     *
     * @param userID the user unique identifier.
     * @return the user with the given user identifier if the identifier does not belong to a user in the system
     * returns null.
     */
    private PrivateUser getUser(String userID) {
        return users.find(userID.toLowerCase());
    }

    @Override
    public void insUser(String userID, String username, int userAge, String userEmail) throws
            UserAlreadyExistsException, UserUnderageException {
        if (isUserUnderage(userAge)) throw new UserUnderageException();
        if ((getUser(userID)) != null) throw new UserAlreadyExistsException();
        users.insert(userID.toLowerCase(), new CollectorClass(userID, username, userAge, userEmail));
    }

    @Override
    public void insArtist(String userID, String username, String artisticName, int userAge, String userEmail) throws
            UserAlreadyExistsException, UserUnderageException {
        if (isUserUnderage(userAge)) throw new UserUnderageException();
        if ((getUser(userID)) != null) throw new UserAlreadyExistsException();
        users.insert(userID.toLowerCase(), new ArtistClass(userID, username, artisticName, userAge, userEmail));
        artworksByArtistOrderedByName.insert(userID.toLowerCase(), new AVLTree<>());
    }

    @Override
    public void remUser(String userID) throws UserDoesntExistException, UserHasActiveBidsException,
            ArtistHasArtworkInAuctionException {
        PrivateUser u;
        if ((u = getUser(userID)) == null) throw new UserDoesntExistException();
        if (u.hasActiveBids()) throw new UserHasActiveBidsException();
        if (u instanceof Artist) {
            if (((Artist) u).hasArtworksInAuction()) throw new ArtistHasArtworkInAuctionException();
            Iterator<Entry<String, Artwork>> it = ((Artist) u).getArtworks();
            while (it.hasNext()) {
                String artworkID = it.next().getKey();
                artworks.remove(artworkID.toLowerCase());
            }
            artworksByArtistOrderedByName.remove(userID.toLowerCase());
        }
        users.remove(u.getUserID().toLowerCase());
    }

    /**
     * Returns the artwork with the given user identifier.
     *
     * @param artworkID the unique identifier of the artwork.
     * @return the artwork with the given identifier if the identifier does not belong to an artwork in the system
     * returns null
     */
    private PrivateArtwork getArtwork(String artworkID) {
        return artworks.find(artworkID.toLowerCase());
    }

    @Override
    public void insArtwork(String artworkID, String authorID, int artworkYear, String artworkName) throws
            ArtworkAlreadyExistsException, UserDoesntExistException, UserIsNotAnArtistException {
        if (getArtwork(artworkID) != null) throw new ArtworkAlreadyExistsException();
        PrivateUser u = getUser(authorID);
        if (u == null) throw new UserDoesntExistException();
        if (u instanceof Collector) throw new UserIsNotAnArtistException();
        PrivateArtwork a = new ArtworkClass(artworkID, authorID, artworkYear, artworkName, u.getUsername());
        ((PrivateArtist) u).insArtwork(artworkID, a);
        artworks.insert(artworkID.toLowerCase(), a);
        OrderedDictionary<String, PrivateArtwork> temp =
                artworksByArtistOrderedByName.find(authorID.toLowerCase());
        if (temp == null) temp = new AVLTree<>();
        temp.insert(artworkName, a);
        artworksByArtistOrderedByName.insert(authorID.toLowerCase(), temp);
    }

    @Override
    public User infoCollector(String userID) throws UserDoesntExistException {
        User u = getUser(userID);
        if (u == null) throw new UserDoesntExistException();
        return u;
    }

    @Override
    public Artist infoArtist(String userID) throws UserDoesntExistException, UserIsNotAnArtistException {
        User u = getUser(userID);
        if (u == null) throw new UserDoesntExistException();
        if (u instanceof Collector) throw new UserIsNotAnArtistException();
        return (Artist) u;
    }

    @Override
    public Artwork infoArtwork(String artworkID) throws ArtworkDoesntExistException {
        Artwork a = getArtwork(artworkID);
        if (a == null) throw new ArtworkDoesntExistException();
        return a;
    }

    private PrivateAuction getAuction(String auctionID) {
        return auctions.find(auctionID.toLowerCase());
    }

    @Override
    public void insertAuction(String auctionID) throws AuctionAlreadyExistsException {
        if (getAuction(auctionID) != null) throw new AuctionAlreadyExistsException();
        auctions.insert(auctionID.toLowerCase(), new AuctionClass(auctionID));
    }

    @Override
    public void insArtworkInAuction(String auctionID, String artworkID, int minVal) throws AuctionDoesntExistException,
            ArtworkDoesntExistException {
        PrivateAuction auction;
        if ((auction = getAuction(auctionID)) == null) throw new AuctionDoesntExistException();
        PrivateArtwork artwork;
        if ((artwork = getArtwork(artworkID)) == null) throw new ArtworkDoesntExistException();
        PrivateArtist artist = (PrivateArtist) getUser(artwork.getAuthorID());
        auction.insArtwork(artworkID, artwork, minVal);
        artwork.insAuction(auction);
        artist.insArtworkInAuction(artwork, auction);
    }

    public void insBid(String auctionID, String artworkID, String userID, int val) throws UserDoesntExistException,
            AuctionDoesntExistException, ArtworkDoesntExistException, ArtworkDoesntExistInAuctionException,
            BidBelowMinimumValueException {
        PrivateUser u;
        if ((u = getUser(userID)) == null) throw new UserDoesntExistException();
        PrivateAuction auction;
        if ((auction = getAuction(auctionID)) == null) throw new AuctionDoesntExistException();
        if ((getArtwork(artworkID)) == null) throw new ArtworkDoesntExistException();
        if (!auction.hasArtwork(artworkID)) throw new ArtworkDoesntExistInAuctionException();
        if (auction.minValOfArtwork(artworkID) > val) throw new BidBelowMinimumValueException();

        Bid b = new BidClass(userID, artworkID, u.getUsername(), val);

        u.insBid(b);
        auction.insBid(artworkID, b);
    }

    public Iterator<Entry<Artwork, Bid>> closeAuction(String auctionID) throws AuctionDoesntExistException {
        PrivateAuction auction;
        if ((auction = getAuction(auctionID)) == null) throw new AuctionDoesntExistException();

        Iterator<Entry<String, List<Bid>>> it = auction.getBids();

        List<Entry<Artwork, Bid>> res = new DoubleList<>();

        while (it.hasNext()) {
            Entry<String, List<Bid>> entry = it.next();

            PrivateArtwork artwork = getArtwork(entry.getKey());

            Iterator<Bid> it2 = entry.getValue().iterator();

            Bid b = null;

            while (it2.hasNext()) {
                if (b == null) b = it2.next();
                else {
                    Bid b2 = it2.next();
                    if (b.getVal() < b2.getVal()) b = b2;
                }
            }

            res.addLast(new EntryClass<>(artwork, b));
            ((PrivateArtist) getUser(artwork.getAuthorID())).remArtworkFromAuction(artwork, auction);

            if (b != null) {
                artwork.sell(b.getVal(), auction);
            }

            it2.rewind();

            while (it2.hasNext()) {
                b = it2.next();
                users.find(b.getUserID()).remBid(b);
            }
        }
        auctions.remove(auctionID.toLowerCase());

        return res.iterator();
    }

    @Override
    public Iterator<Artwork> listAuctionWorks(String auctionID) throws AuctionDoesntExistException,
            AuctionEmptyException {
        PrivateAuction auction;
        if ((auction = getAuction(auctionID)) == null) throw new AuctionDoesntExistException();
        if (auction.isAuctionEmpty()) throw new AuctionEmptyException();
        return auction.getArtworksByInsertionOrder();
    }

    @Override
    public Iterator<Entry<String, Artwork>> listArtistWorks(String artistID) throws UserDoesntExistException,
            UserIsNotAnArtistException, ArtistHasNoWorksException {
        PrivateUser u;
        if ((u = getUser(artistID)) == null) throw new UserDoesntExistException();
        if (!(u instanceof Artist)) throw new UserIsNotAnArtistException();
        Iterator<Entry<String, Artwork>> it = ((Artist) u).getArtworksOrderedByName();
        if (!it.hasNext()) throw new ArtistHasNoWorksException();
        return it;
    }

    @Override
    public Iterator<Bid> listBidsWork(String auctionID, String artworkID) throws
            AuctionDoesntExistException, ArtworkDoesntExistInAuctionException, ArtworkHasNoBidsException {
        PrivateAuction auction;
        if ((auction = getAuction(auctionID)) == null) throw new AuctionDoesntExistException();
        if (!auction.hasArtwork(artworkID)) throw new ArtworkDoesntExistInAuctionException();

        Iterator<Entry<String, List<Bid>>> it = auction.getBids();
        Iterator<Bid> bidList = null;
        while (it.hasNext()) {
            Entry<String, List<Bid>> entry = it.next();
            if (entry.getKey().equals(artworkID)) bidList = entry.getValue().iterator();
        }
        assert bidList != null;
        if (!bidList.hasNext()) throw new ArtworkHasNoBidsException();
        return bidList;
    }

    @Override
    public Iterator<Artwork> listWorksByValue() throws NoSoldWorksException {
        DoubleListWithComparator<Artwork> temp = new DoubleListWithComparator<>();
        Iterator<Entry<String, PrivateArtwork>> it = artworks.iterator();

        while (it.hasNext()) {
            Artwork artwork = it.next().getValue();
            if (artwork.getHighestVal() > 0 ) temp.addWithComparator(artwork);
        }
        if (temp.isEmpty()) throw new NoSoldWorksException();
        return temp.iterator();
    }
}
