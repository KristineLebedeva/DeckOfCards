package api;

import io.restassured.response.Response;

import static api.EndPoints.*;
import static handlers.EnvironmentAndUtils.getRequest;


public class GET {
    public static Response getDeckAPI(String deckID) {
        return getRequest(GET_DECK, "deck_id", deckID, deckID);
    }

    public static Response getPileAPI(String deckID, String pileName) {
        return getRequest(GET_PILE, "", "", deckID, pileName);
    }
}
