package enums;


/**
 * Enum with all the outputs used in this implementation.
 *
 * @author Joana Simões Neves (65441) js.neves@campus.fct.unl.pt
 * @author Rui Xavier (65815) ra.xavier@campus.fct.unl.pt
 */
public enum Output {

    REGISTER_USER_SUCCESS("\nRegisto de utilizador executado.\n"),
    USER_UNDERAGE("\nIdade inferior a 18 anos.\n"),
    USER_ALREADY_EXISTS("\nUtilizador existente.\n"),
    REGISTER_ARTIST_SUCCESS("\nRegisto de artista executado.\n"),
    REMOVE_USER("\nRemocao de utilizador executada.\n"),
    USER_DOESNT_EXIST("\nUtilizador inexistente.\n"),
    USER_HAS_ACTIVE_BIDS("\nUtilizador com propostas submetidas.\n"),
    ARTIST_HAS_ARTWORK_IN_AUCTION("\nArtista com obras em leilao."),
    REGISTER_ARTWORK_SUCCESS("\nRegisto de obra executado.\n"),
    ARTWORK_ALREADY_EXISTS("\nObra existente.\n"),
    ARTIST_DOESNT_EXIST("\nArtista inexistente.\n"),
    ARTIST_HAS_NO_ARTWORKS("\nArtista sem obras.\n"),
    INFO_USER("\n%s %s %d %s\n"),
    INFO_ARTIST("\n%s %s %s %d %s\n"),
    INFO_WORK("\n%s %s %s %d %s %s\n"),
    ARTWORK_DOESNT_EXIST("\nObra inexistente.\n"),
    REGISTER_AUCTION_SUCCESS("\nRegisto de leilao executado.\n"),
    AUCTION_ALREADY_EXISTS("\nLeilao existente.\n"),
    ADD_ARTWORK_TO_AUCTION_SUCCESS("\nObra adicionada ao leilao.\n"),
    AUCTION_DOESNT_EXIST("\nLeilao inexistente.\n"),
    BID("\nProposta aceite.\n"),
    ARTWORK_DOESNT_EXIST_IN_AUCTION("\nObra inexistente no leilao.\n"),
    BID_BELOW_MINIMUM_VALUE("\nValor proposto abaixo do valor minimo.\n"),
    CLOSE_AUCTION("\nLeilao encerrado."),
    CLOSE_AUCTION_OUTPUT1("\n%s %s %s %s %d"),
    CLOSE_AUCTION_OUTPUT2("\n%s %s sem propostas de venda."),
    LIST_AUCTION_ARTWORKS("%s %s %s %s %s %s\n"),
    AUCTION_HAS_NO_ARTWORKS("\nLeilao sem obras.\n"),
    LIST_ARTWORK_BIDS("%s %s %s\n"),
    LIST_ARTIST_WORKS("%s %s %d %d\n"),
    ARTWORK_HAS_NO_BIDS("\nObra sem propostas.\n"),
    LIST_WORKS_BY_VALUE("%s %s %d %d %s %s\n"),
    NO_SOLD_WORKS("\nNao existem obras ja vendidas em leilao.\n"),
    QUIT("\nObrigado. Ate a proxima.");

    private final String message;

    /**
     * Constructor.
     */
    Output(String message) {
        this.message = message;
    }

    /**
     * Returns the output message associated with the constant.
     *
     * @return the output message.
     */
    public String getOutput() {
        return message;
    }
}
