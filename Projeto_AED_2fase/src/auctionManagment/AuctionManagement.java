package auctionManagment;

import java.io.Serializable;

import dataStructures.Entry;
import dataStructures.Iterator;
import exceptions.*;

public interface AuctionManagement extends Serializable {
    /**
     * Adds a new user into the system.
     *
     * @param userID    the unique identifier of the user.
     * @param username  the name of the user.
     * @param userAge   the age of the user.
     * @param userEmail the email of the user.
     * @throws UserAlreadyExistsException if the unique identifier already belongs to another user.
     * @throws UserUnderageException      if the age of the user is under the minimum age.
     */
    void insUser(String userID, String username, int userAge, String userEmail) throws UserAlreadyExistsException,
            UserUnderageException;

    /**
     * Adds a new artist into the system.
     *
     * @param userID       the unique identifier of the user.
     * @param username     the name of the user.
     * @param artisticName the artistic name of the artist.
     * @param userAge      the age of the user.
     * @param userEmail    the email of the user.
     * @throws UserAlreadyExistsException if the unique identifier already belongs to another user.
     * @throws UserUnderageException      if the age of the user is under the minimum age.
     */
    void insArtist(String userID, String username, String artisticName, int userAge, String userEmail) throws
            UserAlreadyExistsException, UserUnderageException;

    /**
     * Removes a user from the system.
     *
     * @param userID the unique identifier of the user.
     * @throws UserDoesntExistException           if the userID does not belong to a user in the system.
     * @throws UserHasActiveBidsException         if the user has active bids in auctions.
     * @throws ArtistHasArtworkInAuctionException if the Artist has an artwork in an auction.
     */
    void remUser(String userID) throws UserDoesntExistException, UserHasActiveBidsException,
            ArtistHasArtworkInAuctionException;

    /**
     * Adds a new artwork into the system.
     *
     * @param artworkID   the unique identifier of the artwork.
     * @param authorID    the unique identifier of the author.
     * @param artworkYear the year the artwork was made.
     * @param artworkName the name of the artwork.
     * @throws ArtworkAlreadyExistsException if the unique identifier of the author already belongs to another artwork.
     * @throws UserDoesntExistException      if the unique identifier of the author does not belong to a user in the
     *                                       system.
     * @throws UserIsNotAnArtistException    if the unique identifier of the author does not belong to an artist in the
     *                                       system.
     */
    void insArtwork(String artworkID, String authorID, int artworkYear, String artworkName) throws
            ArtworkAlreadyExistsException, UserDoesntExistException, UserIsNotAnArtistException;

    /**
     * Returns the user with the given userID.
     *
     * @param userID the unique identifier of the user.
     * @return the user with the given userID.
     * @throws UserDoesntExistException if the unique identifier does not belong to a user in the system.
     */
    User infoCollector(String userID) throws UserDoesntExistException;

    /**
     * Returns the Artist with the given userID.
     *
     * @param userID the unique identifier od the artist.
     * @return returns the artist with the given unique identifier.
     * @throws UserDoesntExistException   if the unique identifier does not belong to a user in the system.
     * @throws UserIsNotAnArtistException if the unique identifier of the author does not belong to an artist in the
     *                                    system.
     */
    Artist infoArtist(String userID) throws UserDoesntExistException, UserIsNotAnArtistException;

    /**
     * Returns the artwork with the given artworkID.
     *
     * @param artworkID the unique identifier of the artwork.
     * @return the artwork with the given identifier.
     * @throws ArtworkDoesntExistException if the unique identifier does not belong to an artwork in the system.
     */
    Artwork infoArtwork(String artworkID) throws ArtworkDoesntExistException;

    /**
     * Adds a new auction into the system.
     *
     * @param auctionID the unique identifier of the auction.
     * @throws AuctionAlreadyExistsException if the unique identifier already belongs to another auction in the system.
     */
    void insertAuction(String auctionID) throws AuctionAlreadyExistsException;

    /**
     * Inserts a new artwork into the auction.
     *
     * @param auctionID
     * @param artworkID
     * @param minVal
     * @throws AuctionDoesntExistException
     * @throws ArtworkDoesntExistException
     */
    void insArtworkInAuction(String auctionID, String artworkID, int minVal) throws AuctionDoesntExistException,
            ArtworkDoesntExistException;

    void insBid(String AuctionID, String artworkID, String userID, int val) throws UserDoesntExistException,
            AuctionDoesntExistException, ArtworkDoesntExistException, ArtworkDoesntExistInAuctionException,
            BidBelowMinimumValueException;

    Iterator<Entry<Artwork, Bid>> closeAuction(String auctionID) throws AuctionDoesntExistException;

    Iterator<Artwork> listAuctionWorks(String auctionID) throws AuctionDoesntExistException,
            AuctionEmptyException;
    Iterator<Entry<String, Artwork>> listArtistWorks(String artistID) throws UserDoesntExistException,
            UserIsNotAnArtistException, ArtistHasNoWorksException;

    Iterator<Bid> listBidsWork(String auctionID, String artworkID) throws
            AuctionDoesntExistException, ArtworkDoesntExistInAuctionException, ArtworkHasNoBidsException;

    Iterator<Artwork> listWorksByValue() throws NoSoldWorksException;
}
