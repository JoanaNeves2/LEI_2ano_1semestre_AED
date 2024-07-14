import auctionManagment.*;
import dataStructures.Entry;
import dataStructures.Iterator;
import enums.Command;
import enums.Output;
import exceptions.*;

import java.io.*;
import java.util.Scanner;

public class Main {


    //Name of the .dat file where the state of the program will be stored.
    private static final String DAT_FILE = "storeddata.dat";


    public static void main(String[] args) {
        Main.commandProcessor();
    }

    /**
     * Command interpreter.
     */
    private static void commandProcessor() {

        AuctionManagement am = load();

        Scanner in = new Scanner(System.in);

        Command c = getCommand(in);

        while (!c.equals(Command.QUIT)) {
            assert am != null;
            switch (c) {
                case ADDUSER:
                    addUser(in, am);
                    break;
                case ADDARTIST:
                    addArtist(in, am);
                    break;
                case REMOVEUSER:
                    removeUser(in, am);
                    break;
                case ADDWORK:
                    addWork(in, am);
                    break;
                case INFOUSER:
                    infoUser(in, am);
                    break;
                case INFOARTIST:
                    infoArtist(in, am);
                    break;
                case INFOWORK:
                    infoWork(in, am);
                    break;
                case CREATEAUCTION:
                    createAuction(in, am);
                    break;
                case ADDWORKAUCTION:
                    addWorkAuction(in, am);
                    break;
                case BID:
                    bid(in, am);
                    break;
                case CLOSEAUCTION:
                    closeAuction(in, am);
                    break;
                case LISTAUCTIONWORKS:
                    listAuctionWorks(in, am);
                    break;
                case LISTARTISTWORKS:
                    listArtistWorks(in, am);
                    break;
                case LISTBIDSWORK:
                    listBidsWork(in, am);
                    break;
                case LISTWORKSBYVALUE:
                    listWorksByValue(am);
                    break;
                default:
                    break;

            }
            c = getCommand(in);
            System.out.println();
        }
        quit();
        save(am);
        in.close();
    }

    /**
     * Adds a new collector to the system.
     *
     * @param in Scanner that reads the user's inputs
     * @param am Object of the AuctionManagement Class
     */
    private static void addUser(Scanner in, AuctionManagement am) {
        String login = in.next();
        String name = in.nextLine().trim();
        int age = in.nextInt();
        String email = in.next();
        in.nextLine();

        try {
            am.insUser(login, name, age, email);
            System.out.print(Output.REGISTER_USER_SUCCESS.getOutput());
        } catch (UserUnderageException e) {
            System.out.print(Output.USER_UNDERAGE.getOutput());
        } catch (UserAlreadyExistsException e) {
            System.out.print(Output.USER_ALREADY_EXISTS.getOutput());
        }
    }

    /**
     * Adds a new artist to the system.
     *
     * @param in Scanner that reads the user's inputs
     * @param am Object of the AuctionManagement Class
     */
    private static void addArtist(Scanner in, AuctionManagement am) {
        String login = in.next();
        String name = in.nextLine().trim();
        String artistName = in.nextLine();
        int age = in.nextInt();
        String email = in.next();
        in.nextLine();

        try {
            am.insArtist(login, name, artistName, age, email);
            System.out.print(Output.REGISTER_ARTIST_SUCCESS.getOutput());
        } catch (UserUnderageException e) {
            System.out.print(Output.USER_UNDERAGE.getOutput());
        } catch (UserAlreadyExistsException e) {
            System.out.print(Output.USER_ALREADY_EXISTS.getOutput());
        }
    }

    /**
     * Removes a certain user from the system
     *
     * @param in Scanner that reads the user's inputs
     * @param am Object of the AuctionManagement Class
     */
    private static void removeUser(Scanner in, AuctionManagement am) {
        String login = in.next();
        in.nextLine();

        try {
            am.remUser(login);
            System.out.print(Output.REMOVE_USER.getOutput());
        } catch (UserDoesntExistException e) {
            System.out.print(Output.USER_DOESNT_EXIST.getOutput());
        } catch (UserHasActiveBidsException e) {
            System.out.print(Output.USER_HAS_ACTIVE_BIDS.getOutput());
        } catch (ArtistHasArtworkInAuctionException e) {
            System.out.println(Output.ARTIST_HAS_ARTWORK_IN_AUCTION.getOutput());
        }
    }

    /**
     * Adds a new artwork to the system.
     *
     * @param in Scanner that reads the user's inputs
     * @param am Object of the AuctionManagement Class
     */
    private static void addWork(Scanner in, AuctionManagement am) {
        String idArtwork = in.next().trim();
        String loginAuthor = in.next();
        int year = in.nextInt();
        String name = in.nextLine().trim();

        try {
            am.insArtwork(idArtwork, loginAuthor, year, name);
            System.out.print(Output.REGISTER_ARTWORK_SUCCESS.getOutput());
        } catch (ArtworkAlreadyExistsException e) {
            System.out.print(Output.ARTWORK_ALREADY_EXISTS.getOutput());
        } catch (UserDoesntExistException e) {
            System.out.print(Output.USER_DOESNT_EXIST.getOutput());
        } catch (UserIsNotAnArtistException e) {
            System.out.print(Output.ARTIST_DOESNT_EXIST.getOutput());
        }
    }

    /**
     * Lists a collector's information.
     *
     * @param in Scanner that reads the user's inputs
     * @param am Object of the AuctionManagement Class
     */
    private static void infoUser(Scanner in, AuctionManagement am) {
        String login = in.next().trim();
        in.nextLine();

        try {
            User user = am.infoCollector(login);
            System.out.printf(Output.INFO_USER.getOutput(), user.getUserID(), user.getUsername(), user.getAge(),
                    user.getEmail());
        } catch (UserDoesntExistException e) {
            System.out.print(Output.USER_DOESNT_EXIST.getOutput());
        }
    }

    /**
     * Lists an artist's information.
     *
     * @param in Scanner that reads the user's inputs
     * @param am Object of the AuctionManagement Class
     */
    private static void infoArtist(Scanner in, AuctionManagement am) {
        String login = in.next().trim();
        in.nextLine();

        try {
            Artist artist = am.infoArtist(login);
            System.out.printf(Output.INFO_ARTIST.getOutput(), artist.getUserID().trim(), artist.getUsername(),
                    artist.getArtisticName(), artist.getAge(), artist.getEmail());
        } catch (UserDoesntExistException e) {
            System.out.print(Output.USER_DOESNT_EXIST.getOutput());
        } catch (UserIsNotAnArtistException e) {
            System.out.print(Output.ARTIST_DOESNT_EXIST.getOutput());
        }
    }

    private static void infoWork(Scanner in, AuctionManagement am) {
        String idArtwork = in.next().trim();
        in.nextLine();

        try {
            Artwork artWork = am.infoArtwork(idArtwork);
            System.out.printf(Output.INFO_WORK.getOutput(), artWork.getArtworkID(), artWork.getArtworkName(),
                    artWork.getYear(), artWork.getHighestVal(), artWork.getAuthorID(), artWork.getAuthorName());
        } catch (ArtworkDoesntExistException e) {
            System.out.print(Output.ARTWORK_DOESNT_EXIST.getOutput());
        }
    }

    /**
     * Adds a new auction to the system.
     *
     * @param in Scanner that reads the user's inputs
     * @param am Object of the AuctionManagement Class
     */
    private static void createAuction(Scanner in, AuctionManagement am) {
        String auctionID = in.next();
        in.nextLine();

        try {
            am.insertAuction(auctionID);
            System.out.print(Output.REGISTER_AUCTION_SUCCESS.getOutput());
        } catch (AuctionAlreadyExistsException e) {
            System.out.print(Output.AUCTION_ALREADY_EXISTS.getOutput());
        }
    }

    /**
     * Adds an artwork to an auction
     *
     * @param in Scanner that reads the user's inputs
     * @param am Object of the AuctionManagement Class
     */
    private static void addWorkAuction(Scanner in, AuctionManagement am) {
        String auctionId = in.next();
        String artworkId = in.next();
        int minimumBid = in.nextInt();
        in.nextLine();

        try {
            am.insArtworkInAuction(auctionId, artworkId, minimumBid);
            System.out.print(Output.ADD_ARTWORK_TO_AUCTION_SUCCESS.getOutput());
        } catch (AuctionDoesntExistException e) {
            System.out.print(Output.AUCTION_DOESNT_EXIST.getOutput());
        } catch (ArtworkDoesntExistException e) {
            System.out.print(Output.ARTWORK_DOESNT_EXIST.getOutput());
        }
    }

    /**
     * Adds a new bid to an artwork of a specific auction.
     *
     * @param in Scanner that reads the user's inputs
     * @param am Object of the AuctionManagement Class
     */
    private static void bid(Scanner in, AuctionManagement am) {
        String auctionId = in.next();
        String artworkId = in.next();
        String login = in.next();
        int value = in.nextInt();
        in.nextLine();

        try {
            am.insBid(auctionId, artworkId, login, value);
            System.out.print(Output.BID.getOutput());
        } catch (UserDoesntExistException e) {
            System.out.print(Output.USER_DOESNT_EXIST.getOutput());
        } catch (AuctionDoesntExistException e) {
            System.out.print(Output.AUCTION_DOESNT_EXIST.getOutput());
        } catch (ArtworkDoesntExistInAuctionException | ArtworkDoesntExistException e) {
            System.out.print(Output.ARTWORK_DOESNT_EXIST_IN_AUCTION.getOutput());
        } catch (BidBelowMinimumValueException e) {
            System.out.print(Output.BID_BELOW_MINIMUM_VALUE.getOutput());
        }
    }


    /**
     * Closes an auction.
     *
     * @param in Scanner that reads the user's inputs
     * @param am Object of the AuctionManagement Class
     */
    private static void closeAuction(Scanner in, AuctionManagement am) {
        String auctionId = in.next();
        try {
            Iterator<Entry<Artwork, Bid>> it = am.closeAuction(auctionId);
            System.out.print(Output.CLOSE_AUCTION.getOutput());
            while (it.hasNext()) {
                Entry<Artwork, Bid> e = it.next();

                Artwork next = e.getKey();

                if (e.getValue() != null) {
                    System.out.printf(Output.CLOSE_AUCTION_OUTPUT1.getOutput(),
                            next.getArtworkID(), next.getArtworkName(), e.getValue().getUserID(),
                            e.getValue().getUsername(), e.getValue().getVal());
                } else {
                    System.out.printf(Output.CLOSE_AUCTION_OUTPUT2.getOutput(),
                            next.getArtworkID(), next.getArtworkName());
                }
            }
            if (!it.hasNext())
                System.out.println();
        } catch (AuctionDoesntExistException e) {
            System.out.print(Output.AUCTION_DOESNT_EXIST.getOutput());
        }
    }

    /**
     * Lists the information of all artworks in a specific auction.
     *
     * @param in Scanner that reads the user's inputs
     * @param am Object of the AuctionManagement Class
     */
    private static void listAuctionWorks(Scanner in, AuctionManagement am) {
        String idAuction = in.next();
        in.nextLine();

        try {
            Iterator<Artwork> it = am.listAuctionWorks(idAuction);
            System.out.println();
            while (it.hasNext()) {
                Artwork next = it.next();
                System.out.printf(Output.LIST_AUCTION_ARTWORKS.getOutput(), next.getArtworkID(), next.getArtworkName(),
                        next.getYear(), next.getHighestVal(), next.getAuthorID(), next.getAuthorName());
            }
        } catch (AuctionDoesntExistException e) {
            System.out.print(Output.AUCTION_DOESNT_EXIST.getOutput());
        } catch (AuctionEmptyException e) {
            System.out.print(Output.AUCTION_HAS_NO_ARTWORKS.getOutput());
        }
    }

    private static void listArtistWorks(Scanner in, AuctionManagement am) {
        String login = in.next();
        try {
            Iterator<Entry<String, Artwork>> it = am.listArtistWorks(login);
            System.out.println();
            while (it.hasNext()) {
                Artwork next = it.next().getValue();
                System.out.printf(Output.LIST_ARTIST_WORKS.getOutput(),
                        next.getArtworkID(), next.getArtworkName(), next.getYear(), next.getHighestVal());
            }
        } catch (UserDoesntExistException e) {
            System.out.printf(Output.USER_DOESNT_EXIST.getOutput());
        } catch (UserIsNotAnArtistException e) {
            System.out.printf(Output.ARTIST_DOESNT_EXIST.getOutput());
        } catch (ArtistHasNoWorksException e) {
            System.out.printf(Output.ARTIST_HAS_NO_ARTWORKS.getOutput());
        }
    }

    private static void listBidsWork(Scanner in, AuctionManagement am) {
        String auctionID = in.next();
        String artID = in.next();

        try {
            Iterator<Bid> it = am.listBidsWork(auctionID, artID); //Falta uma exceção
            System.out.println();
            while (it.hasNext()) {
                Bid next = it.next();
                System.out.printf(Output.LIST_ARTWORK_BIDS.getOutput(),
                        next.getUserID(), next.getUsername(), next.getVal());
            }
        } catch (AuctionDoesntExistException e) {
            System.out.printf(Output.AUCTION_DOESNT_EXIST.getOutput());
        } catch (ArtworkDoesntExistInAuctionException e) {
            System.out.printf(Output.ARTWORK_DOESNT_EXIST_IN_AUCTION.getOutput());
        } catch (ArtworkHasNoBidsException e) {
            System.out.printf(Output.ARTWORK_HAS_NO_BIDS.getOutput());
        }
    }

    private static void listWorksByValue(AuctionManagement am) {
        try {
            Iterator<Artwork> it = am.listWorksByValue();
            System.out.println();
            while (it.hasNext()) {
                Artwork next = it.next();
                System.out.printf(Output.LIST_WORKS_BY_VALUE.getOutput(), next.getArtworkID(), next.getArtworkName(),
                        next.getYear(),
                        next.getHighestVal(), next.getAuthorID(), next.getAuthorName());
            }
        } catch (NoSoldWorksException e) {
            System.out.printf(Output.NO_SOLD_WORKS.getOutput());
        }
    }

    /**
     * Terminates the execution of the program
     */
    private static void quit() {
        System.out.println(Output.QUIT.getOutput());
    }

    /**
     * Loads the AuctionManagment.AuctionManagement from the dat file DAT_FILE.
     *
     * @return the loaded AuctionManagment.AuctionManagement.
     */
    @SuppressWarnings("unchecked")
    private static AuctionManagement load() {
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(DAT_FILE));
            AuctionManagement am = (AuctionManagement) file.readObject();
            file.close();
            return am;
        } catch (FileNotFoundException e) {
            return new AuctionManagementClass();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Saves the AuctionManagment.AuctionManagement in the dat file DAT_FILE.
     *
     * @param am AuctionManagment.AuctionManagement that is being saved.
     */
    private static void save(AuctionManagement am) {
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(DAT_FILE));
            file.writeObject(am);
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets a command.
     *
     * @param in Scanner that reads the user's inputs
     * @return the command
     */
    private static Command getCommand(Scanner in) {
        try {
            String c = in.next();
            return Command.valueOf(c.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }
}