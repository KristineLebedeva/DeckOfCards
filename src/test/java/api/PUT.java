package api;

import io.restassured.response.Response;

import static api.EndPoints.*;
import static handlers.EnvironmentAndUtils.*;

public class PUT {
    public static Response takeXcardsFromTheDeckAPI(String deckID, String cardsCount) {
        return putRequest(DRAW_CARDS, "count", cardsCount, deckID);
    }

    public static Response createNewDeckAPI() {
        Response res = createNewDeckAPI("deck_count", "1");
        deckID = getParameterValueFromResponse("deck_id");
        return res;
    }

    public static Response createNewDeckAPI(String paramName, String paramValue) {
        Response res = putRequest(CREATE_NEW_DECK, paramName, paramValue, "");
        deckID = getParameterValueFromResponse("deck_id");
        return res;
    }

    public static Response moveCardsToPileAPI(String decID, String pileName, String cards) {
        return putRequest(MOVE_CARDS_TO_PILE, "cards", cards, decID, pileName);
    }

    public static Response drawCardsFromThePileAPI(String decID, String pileName, String drawFrom, String cards) {
        return putRequest(DRAW_CARDS_FROM_PILE, "cards", cards, decID, pileName, drawFrom);
    }
}
