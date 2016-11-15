package com.example.user.pontoon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 12/11/2016.
 */

public class PontoonGameTest {

    PontoonGame game;
    Card twoOfHearts;
    Card kingOfClubs;
    Card aceOfDiamonds;
    Card sevenOfSpades;
    Card sixOfSpades;
    Card sixOfClubs;
    Hand hand;

    @Before
    public void before() {
        game = new PontoonGame();
        twoOfHearts = new Card(Suit.HEARTS, Rank.TWO);
        kingOfClubs = new Card(Suit.CLUBS, Rank.KING);
        aceOfDiamonds = new Card(Suit.DIAMONDS, Rank.ACE);
        sevenOfSpades = new Card(Suit.SPADES, Rank.SEVEN);
        sixOfSpades = new Card(Suit.SPADES, Rank.SIX);
        sixOfClubs = new Card(Suit.CLUBS, Rank.SIX);
        hand = new Hand();
    }

    @Test
    public void canGetAppPlayer() {
        assertEquals(true, game.getAppPlayer().getDealerStatus());
    }

    @Test
    public void canGetUserPlayer() {
        assertEquals(false, game.getUserPlayer().getDealerStatus());
    }

    @Test
    public void canGetDeck() {
        assertEquals(0, game.getDeck().countCards());
    }

    @Test
    public void canGetHandValuer() {
        hand.addCard(kingOfClubs);
        hand.addCard(aceOfDiamonds);
        assertEquals(HandValuer.class, game.getHandValuer()));
    }

    @Test
    public void canSetUpNewDeck() {
        game.setUpNewDeck();
        assertEquals(52, game.getDeck().countCards());
    }

    @Test
    public void canDeal() {
        game.setUpNewDeck();
        game.deal(2);
        assertEquals(48, game.getDeck().countCards());
        assertEquals(2, game.getAppPlayer().getHand().countCards());
        assertEquals(2, game.getUserPlayer().getHand().countCards());
    }

    @Test
    public void canShowAppHandText() {
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getAppPlayer().getHand().addCard(aceOfDiamonds);
        assertEquals("Dealer hand: \nKING of CLUBS\nACE of DIAMONDS", game.showAppHandText());
    }

    @Test
    public void canShowUserHandText() {
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        assertEquals("Your hand: \nSEVEN of SPADES\nTWO of HEARTS", game.showUserHandText());
    }

    @Test
    public void canCheckForAppPontoon_Pontoon() {
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getAppPlayer().getHand().addCard(aceOfDiamonds);
        assertEquals(true, game.checkForAppPontoon());
    }

    @Test
    public void canCheckForAppPontoon_NoPontoon() {
        game.getAppPlayer().getHand().addCard(sevenOfSpades);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        assertEquals(false, game.checkForAppPontoon());
    }

    @Test
    public void canCheckForUserPontoon_Pontoon() {
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(aceOfDiamonds);
        assertEquals(true, game.checkForUserPontoon());
    }

    @Test
    public void canCheckForUserPontoon_NoPontoon() {
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        assertEquals(false, game.checkForUserPontoon());
    }

    @Test
    public void canGetAppHandValue() {
        game.getAppPlayer().getHand().addCard(sevenOfSpades);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        assertEquals(13, game.getAppHandValue());
    }

    @Test
    public void canGetUserHandValue() {
        game.getUserPlayer().getHand().addCard(aceOfDiamonds);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        assertEquals(17, game.getUserHandValue());
    }

    @Test
    public void canGetAppHandSize() {
        game.getAppPlayer().getHand().addCard(aceOfDiamonds);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(aceOfDiamonds);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        assertEquals(4, game.getAppHandSize());
    }

    @Test
    public void canGetUserHandSize() {
        game.getUserPlayer().getHand().addCard(aceOfDiamonds);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        assertEquals(3, game.getUserHandSize());
    }

    @Test
    public void appCanTwist() {
        game.setUpNewDeck();
        game.deal(2);
        game.appTwist();
        assertEquals(3, game.getAppHandSize());
        assertEquals(47, game.getDeck().countCards());
    }

    @Test
    public void userCanTwist() {
        game.setUpNewDeck();
        game.deal(2);
        game.userTwist();
        assertEquals(3, game.getUserHandSize());
        assertEquals(47, game.getDeck().countCards());
    }

    @Test
    public void canCheckIfAppBust_NotBust() {
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getAppPlayer().getHand().addCard(aceOfDiamonds);
        assertEquals(false, game.checkIfAppBust());
    }

    @Test
    public void canCheckIfAppBust_Bust() {
        game.getAppPlayer().getHand().addCard(sevenOfSpades);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(aceOfDiamonds);
        assertEquals(true, game.checkIfAppBust());
    }

    @Test
    public void canCheckIfUserBust_NotBust() {
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(aceOfDiamonds);
        assertEquals(false, game.checkIfUserBust());
    }

    @Test
    public void canCheckIfUserBust_Bust() {
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        game.getUserPlayer().getHand().addCard(aceOfDiamonds);
        assertEquals(true, game.checkIfUserBust());
    }

    @Test
    public void canCheckForAppFCT_FCT() {
        game.getAppPlayer().getHand().addCard(sevenOfSpades);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(sevenOfSpades);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        assertEquals(true, game.checkForAppFCT());
    }

    @Test
    public void canCheckForAppFCT_NoFCT() {
        game.getAppPlayer().getHand().addCard(sevenOfSpades);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(aceOfDiamonds);
        game.getAppPlayer().getHand().addCard(sevenOfSpades);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        assertEquals(false, game.checkForAppFCT());
    }

    @Test
    public void canCheckForUserFCT_FCT() {
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        assertEquals(true, game.checkForUserFCT());
    }

    @Test
    public void canCheckForUserFCT_NoFCT() {
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        game.getUserPlayer().getHand().addCard(aceOfDiamonds);
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        assertEquals(false, game.checkForUserFCT());
    }

    @Test
    public void testCheckInitialDeal_BothPontoon() {
        game.getAppPlayer().getHand().addCard(aceOfDiamonds);
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(aceOfDiamonds);
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        assertEquals("You both have Pontoon. \nDealer wins this hand.", game.checkInitialDeal());
    }

    @Test
    public void testCheckInitialDeal_AppPontoon() {
        game.getAppPlayer().getHand().addCard(aceOfDiamonds);
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        assertEquals("You have 17. \nDealer has Pontoon. \nDealer wins this hand.", game.checkInitialDeal());
    }

    @Test
    public void testCheckInitialDeal_UserPontoon() {
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(aceOfDiamonds);
        assertEquals("You have Pontoon. \nDealer has 16. \nYou win this hand!", game.checkInitialDeal());
    }

    @Test
    public void testCheckInitialDeal_NeitherPontoon() {
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        game.getUserPlayer().getHand().addCard(aceOfDiamonds);
        assertEquals(null, game.checkInitialDeal());
    }

    @Test
    public void testCheckUserHand_HaveToTwist() {
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        assertEquals("Since your hand is worth less than 15, you have to twist.", game.checkUserHand());
    }

    @Test
    public void testCheckUserHand_BustInFive() {
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        assertEquals("You're bust. \nDealer wins this hand.", game.checkUserHand());
    }

    @Test
    public void testCheckUserHand_FiveCardTrick() {
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        assertEquals("You have a Five Card Trick! \nDealer's turn, now...", game.checkUserHand());
    }

    @Test
    public void testCheckUserHand_StickOrTwist() {
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        assertEquals("Stick or twist?", game.checkUserHand());
    }

    @Test
    public void testCheckUserHand_BustInFewerThanFive() {
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        game.getUserPlayer().getHand().addCard(kingOfClubs);
        assertEquals("You're bust. \nDealer wins this hand.", game.checkUserHand());
    }

    @Test
    public void testCheckAppHand_HaveToTwist() {
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        assertEquals("Since their hand is worth less than 15, Dealer must twist...", game.checkAppHand());
    }

    @Test
    public void testCheckAppHand_BustInFive() {
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        assertEquals("Dealer is bust! \nYou win this hand.", game.checkAppHand());
    }

    @Test
    public void testCheckAppHand_FiveCardTrick() {
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        assertEquals("Dealer has a Five Card Trick.", game.checkAppHand());
    }

    @Test
    public void testCheckAppHand_Twist() {
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(sevenOfSpades);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        assertEquals("Dealer twists...", game.checkAppHand());
    }

    @Test
    public void testCheckAppHand_Stick() {
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        assertEquals("Dealer sticks...", game.checkAppHand());
    }

    @Test
    public void testCheckAppHand_BustInFewerThanFive() {
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        game.getAppPlayer().getHand().addCard(kingOfClubs);
        assertEquals("Dealer is bust! \nYou win this hand.", game.checkAppHand());
    }

    @Test
    public void testCompareHands_AppFCT() {
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        assertEquals("You have 18. \nDealer has a Five Card Trick. \nDealer wins this hand.", game.compareHands());
    }

    @Test
    public void testCompareHands_BothFCT() {
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        assertEquals("You both have a Five Card Trick. \nDealer wins this hand.", game.compareHands());
    }

    @Test
    public void testCompareHands_UserFCT() {
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        assertEquals("You have a Five Card Trick. \nDealer has 18. \nYou win this hand!", game.compareHands());

    }

    @Test
    public void testCompareHands_SameValue() {
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(sevenOfSpades);
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        assertEquals("You have 15. \nDealer has the same. \nDealer wins this hand.", game.compareHands());
    }

    @Test
    public void testCompareHands_AppHigher() {
        game.getAppPlayer().getHand().addCard(twoOfHearts);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(sevenOfSpades);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        assertEquals("You have 13. \nDealer has 15. \nDealer wins this hand.", game.compareHands());
    }

    @Test
    public void testCompareHands_UserHigher() {
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(sevenOfSpades);
        assertEquals("You have 15. \nDealer has 13. \nYou win this hand!", game.compareHands());
    }

    @Test
    public void canSetUpDeckAfterPontoon_TestNumberOfCards() {
        game.setUpNewDeck();
        game.deal(2);
        game.userTwist();
        game.appTwist();
        assertEquals(46, game.getDeck().countCards());
        assertEquals(3, game.getAppHandSize());
        assertEquals(3, game.getUserHandSize());
        game.setUpExistingDeck();
        assertEquals(52, game.getDeck().countCards());
        assertEquals(0, game.getAppHandSize());
        assertEquals(0, game.getUserHandSize());
    }

    @Test
    public void canSetUpExistingDeck_TestCards() {
        game.setUpNewDeck();
        game.getUserPlayer().getHand().addCard(twoOfHearts);
        game.getUserPlayer().getHand().addCard(sixOfSpades);
        game.getUserPlayer().getHand().addCard(sevenOfSpades);
        game.getAppPlayer().getHand().addCard(sixOfSpades);
        game.getAppPlayer().getHand().addCard(sevenOfSpades);
        game.setUpExistingDeck();
        Card card = game.getDeck().getCardWithIndex(game.getDeck().countCards() - 1);
        assertEquals(Suit.SPADES, card.getSuit());
        assertEquals(Rank.SEVEN, card.getRank());
    }

}
