package auctionManagment;

import dataStructures.SearchableDoubleList;
import dataStructures.SearchableList;

import java.io.Serial;

abstract class AbstractUserClass implements PrivateUser {

    /**
     * Serial Version UID
     */
    @Serial
    private static final long serialVersionUID = 0L;

    //Instance variables.
    final String userID;

    final String username;

    final int age;

    final String email;

    SearchableList<Bid> activeBids;

    /**
     * Initializes the instance variables of this class.
     *
     * @param userID   the unique identifier of the user.
     * @param username the name of the user.
     * @param age      the age of the user.
     * @param email    the email of the user.
     */
    AbstractUserClass(String userID, String username, int age, String email) {
        this.userID = userID;
        this.username = username;
        this.age = age;
        this.email = email;
        this.activeBids = new SearchableDoubleList<>();
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
    public int getAge() {
        return age;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public boolean hasActiveBids() {
        return !activeBids.isEmpty();
    }

    @Override
    public void insBid(Bid b) {
        activeBids.addLast(b);
    }

    @Override
    public void remBid(Bid b) {
        activeBids.remove(activeBids.find(b));
    }
}
