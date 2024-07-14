package auctionManagment;

import java.io.Serial;

class CollectorClass extends AbstractUserClass implements PrivateCollector{

    /**
     * Serial Version UID
     */
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Constructs a new collector calling the super that initializes the instance variables.
     *
     * @param userID   the unique identifier of the user.
     * @param username the name of the user.
     * @param age      the age of the user.
     * @param email    the email of the user.
     */
    CollectorClass(String userID, String username, int age, String email) {
        super(userID, username, age, email);
    }
}
