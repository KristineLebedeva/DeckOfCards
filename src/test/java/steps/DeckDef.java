package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static api.GET.getDeckAPI;
import static api.PUT.*;
import static handlers.EnvironmentAndUtils.*;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.junit.Assert.assertEquals;

public class DeckDef {

    @When("create new deck")
    public void createNewDeck() {
        createNewDeckAPI();
    }

    @And("draw {string} cards from the deck")
    public void drawXCardsFromTheDeck(String cardsCount) {
        takeXcardsFromTheDeckAPI(deckID, cardsCount);
    }

    @Then("in deck should be {string} cards")
    public void inDeckShouldBeXCards(String cardsRemaining) {
        getDeckAPI(deckID);
        assertEquals("Incorrect number of cards are remained in deck", cardsRemaining, getParameterValueFromResponse("remaining"));
    }

    @When("create a deck from Aces")
    public void createADeckFromAces() {
        createNewDeckAPI("cards", repeat("AS,AD,AC,AH,", 13).replaceAll(".$", ""));
    }

    @Then("validate only aces are drawn")
    public void validateOnlyAcesAreDrawn() {
        for (int i = 0; i < 52; i++) {
            assertEquals("Only Aces were expected", "ACE", getParameterValueFromResponse("cards[" + i + "].value"));
        }
    }

    @When("move all cards to pile {string}")
    public void moveAllCardsToPile(String pileName) {
        takeXcardsFromTheDeckAPI(deckID, "52");
        moveCardsToPileAPI(deckID, pileName, ALL_CARDS);
    }
}
