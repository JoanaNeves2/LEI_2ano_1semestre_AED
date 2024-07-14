package auctionManagment;

public interface User {
    /**
     * @return returns the unique identifier of the user.
     */
    String getUserID();

    /**
     * @return returns the name of the user.
     */
    String getUsername();

    /**
     * @return returns the age of the user.
     */
    int getAge();

    /**
     * @return returns the email of the user.
     */
    String getEmail();

    /**
     * @return returns true if the user has active bids otherwise returns false.
     */
    boolean hasActiveBids();
}
