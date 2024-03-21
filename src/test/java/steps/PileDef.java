package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static api.GET.getPileAPI;
import static api.PUT.drawCardsFromThePileAPI;
import static handlers.EnvironmentAndUtils.*;
import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PileDef {
    @And("draw {string} from the {string} of the {string} pile")
    public void drawFromTheBottomOfThePile(String cards, String drawFrom, String pileName) {
        drawCardsFromThePileAPI(deckID, pileName, drawFrom, cards);
    }

    @Then("in the {string} pile should be {string} cards")
    public void inThePileShouldBeCards(String pileName, String cardsCount) {
        getPileAPI(deckID, pileName);
        assertEquals("Incorrect cards count in pile.", cardsCount, getParameterValueFromResponse(format("piles.{0}.remaining", pileName)));
    }

    @And("check that cards {string} aren't in the pile {string}")
    public void cardsArenTInThePile(String cards, String pileName) {
        getPileAPI(deckID, pileName);
        String cardsInThePile = getCardsInThePile(pileName);
        for (String card : cards.split(",")) {
            assertFalse(format("Card {0} shouldn't be in the pile.", card), cardsInThePile.contains(card));
        }
    }
}
