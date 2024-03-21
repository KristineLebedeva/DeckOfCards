Feature: checks cards value and count in the deck

  Scenario Outline: draw X cards from the deck
    Given create new deck
    When draw "<X>" cards from the deck
    Then in deck should be "<remaining>" cards

    Examples:
      | X  | remaining |
      | 52 | 0         |
      | 0  | 52        |
      | 53 | 0         |
      | -1 | 52        |

  Scenario: only aces in deck
    Given create a deck from Aces
    When draw "52" cards from the deck
    Then validate only aces are drawn
