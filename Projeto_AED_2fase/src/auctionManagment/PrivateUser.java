package auctionManagment;


import java.io.Serializable;

interface PrivateUser extends User, Serializable {
    void insBid(Bid b);

    void remBid(Bid b);
}
