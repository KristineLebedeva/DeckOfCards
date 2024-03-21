Feature: checks cards value and count in the pile

  Scenario: draw specific cards from the bottom of the pile
    Given create new deck
    When move all cards to pile "Test"
    And draw "AS,2D,0C,JH,AH" from the "bottom" of the "Test" pile
    Then in the "Test" pile should be "47" cards
    And check that cards "AS,2D,0C,JH,AH" aren't in the pile "Test"
