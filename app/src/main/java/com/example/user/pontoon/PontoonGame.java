package com.example.user.pontoon;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 12/11/2016.
 */

public class PontoonGame {

    private Player appPlayer;
    private Player userPlayer;
    private Deck deck;
    private PontoonHandValuer handValuer;

    public PontoonGame () {
        this.appPlayer = new Player("AppPlayer", true);
        this.userPlayer = new Player("UserPlayer", false);
        this.deck = new OneStandardDeck();
        this.handValuer = new PontoonHandValuer();
    }

    public Player getAppPlayer() {
        return this.appPlayer;
    }

    public Player getUserPlayer() {
        return this.userPlayer;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public PontoonHandValuer getHandValuer() {
        return this.handValuer;
    }

    public void setUpNewDeck() {
        this.deck.buildDeck();
        this.deck.shuffle();
        this.deck.cut();
    }

    public void setUpExistingDeck() {

        // After a hand containing a Pontoon, new hand uses existing deck, with cards played in
        // previous hand moved to bottom
        Hand appHand = this.appPlayer.getHand();
        Hand userHand = this.userPlayer.getHand();

        this.deck.addCardsFrom(appHand);
        this.deck.addCardsFrom(userHand);

        appHand.removeAllCards();
        userHand.removeAllCards();
    }

    public void deal(int numberOfCards) {
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(this.appPlayer);
        playerList.add(this.userPlayer);
        for ( int i = 0; i < numberOfCards; i++ ) {
            for ( Player player : playerList ) {
                Card dealtCard = this.deck.removeFirstCard();
                player.getHand().addCard(dealtCard);
            }
        }
    }

    public String showAppCards() {
        ArrayList<Card> appCards = this.appPlayer.getHand().getSet();

        StringBuffer appCardsText = new StringBuffer();
        for ( Card card : appCards ) {
            appCardsText.append("\n" + card.getRank() + " of " + card.getSuit());
        }

        return "Dealer hand: " + appCardsText;
    }

    public String showAppTwists() {
        ArrayList<Card> appCards = this.appPlayer.getHand().getSet();
        ArrayList<Card> appTwists = new ArrayList<>(appCards.subList(2, appCards.size()));

        StringBuilder twistsText = new StringBuilder();
        if ( appCards.size() > 2 ) {
            for ( Card card : appTwists ) {
                twistsText.append("\n" + card.getRank() + " of " + card.getSuit());
            }
        }

        return "Dealer hand: \n*Card face down*\n*Card face down*" + twistsText;
    }

    public String hideAppCards() {
        ArrayList<Card> appCards = this.appPlayer.getHand().getSet();

        StringBuilder appCardsText = new StringBuilder();
        for ( Card card : appCards ) {
            appCardsText.append("\n*Card face down*");
        }

        return "Dealer hand: " + appCardsText;
    }

    public String showUserCards() {
        ArrayList<Card> userCards = this.userPlayer.getHand().getSet();

        StringBuilder userCardsText = new StringBuilder();
        for ( Card card : userCards ) {
            userCardsText.append("\n" + card.getRank() + " of " + card.getSuit());
        }

        return "Your hand: " + userCardsText;
    }

    public String showUserTwists() {
        ArrayList<Card> userCards = this.userPlayer.getHand().getSet();
        ArrayList<Card> userTwists = new ArrayList<Card>(userCards.subList(2, userCards.size()));

        StringBuilder twistsText = new StringBuilder();
        if ( userCards.size() > 2 ) {
            for ( Card card : userTwists ) {
                twistsText.append("\n" + card.getRank() + " of " + card.getSuit());
            }
        }

        return "Your hand: \n*Card face down*\n*Card face down*" + twistsText;
    }

    public String hideUserCards() {
        ArrayList<Card> userCards = this.userPlayer.getHand().getSet();

        StringBuilder userCardsText = new StringBuilder();
        for ( Card card : userCards ) {
            userCardsText.append("\n*Card face down*");
        }

        return "Your hand: " + userCardsText;
    }


    public boolean checkForAppPontoon() {
        Hand appHand = this.appPlayer.getHand();
        return this.handValuer.checkForPontoon(appHand);
    }

    public boolean checkForUserPontoon() {
        Hand userHand = this.userPlayer.getHand();
        return this.handValuer.checkForPontoon(userHand);
    }

    public int getAppHandValue() {
        Hand appHand = this.appPlayer.getHand();
        return this.handValuer.getHandValue(appHand);
    }

    public int getUserHandValue() {
        Hand userHand = this.userPlayer.getHand();
        return this.handValuer.getHandValue(userHand);
    }

    public int getAppHandSize() {
        Hand appHand = this.appPlayer.getHand();
        return appHand.countCards();
    }

    public int getUserHandSize() {
        Hand userHand = this.userPlayer.getHand();
        return userHand.countCards();
    }

    public void appTwist() {
        Card twistCard = this.deck.removeFirstCard();
        Hand appHand = this.appPlayer.getHand();
        appHand.addCard(twistCard);
    }

    public void userTwist() {
        Card twistCard = this.deck.removeFirstCard();
        Hand userHand = this.userPlayer.getHand();
        userHand.addCard(twistCard);
    }

    public boolean checkIfAppBust() {
        Hand appHand = this.appPlayer.getHand();
        return this.handValuer.checkIfBust(appHand);
    }

    public boolean checkIfUserBust() {
        Hand userHand = this.userPlayer.getHand();
        return this.handValuer.checkIfBust(userHand);
    }

    // FCT - Five Card Trick
    public boolean checkForAppFCT() {
        Hand appHand = this.appPlayer.getHand();
        return this.handValuer.checkForFiveCardTrick(appHand);
    }

    // FCT - Five Card Trick
    public boolean checkForUserFCT() {
        Hand userHand = this.userPlayer.getHand();
        return this.handValuer.checkForFiveCardTrick(userHand);
    }

    public boolean appHasToTwist() {
        Hand appHand = this.appPlayer.getHand();
        return this.handValuer.checkIfMustTwist(appHand);
    }

    public boolean userHasToTwist() {
        Hand userHand = this.userPlayer.getHand();
        return this.handValuer.checkIfMustTwist(userHand);
    }

    public boolean appStrategyTwist() {

        Hand userHand = this.userPlayer.getHand();
        int userTwistValue = this.handValuer.getTwistValue(userHand);

        if ( getAppHandSize() < 5 && userTwistValue > 6 && getAppHandValue() <= 18 ) {
            return true;
        }
        else if ( getAppHandSize() < 5 && getAppHandValue() <= 16 ) {
            return true;
        }
        return false;
    }

    public boolean appStrategyStick() {

        Hand userHand = this.userPlayer.getHand();
        int userTwistValue = this.handValuer.getTwistValue(userHand);

        if ( getAppHandSize() >= 5 ) {
            return true;
        }
        if ( getAppHandSize() < 5 && userTwistValue <= 6 && getAppHandValue() >=16 ) {
            return true;
        }
        return true;
    }

    public boolean userCanStickOrTwist() {
        Hand userHand = this.userPlayer.getHand();
        return this.handValuer.canStickOrTwist(userHand);
    }

    public String checkInitialDeal() {

        int appValue = getAppHandValue();
        int userValue = getUserHandValue();

        String resultBothPontoon = "You both have Pontoon. \nDealer wins this hand.";
        String resultAppPontoon = "You have " + userValue + ". \nDealer has Pontoon. \nDealer wins this hand.";
        String resultUserPontoon = "You have Pontoon. \nDealer has " + appValue + ". \nYou win this hand!";

        if ( checkForAppPontoon() && checkForUserPontoon() ) {
            return resultBothPontoon;
        }
        if ( checkForAppPontoon() ) {
            return resultAppPontoon;
        }
        if ( checkForUserPontoon() ) {
            return resultUserPontoon;
        }
        return null;
    }

    public String checkUserHand() {

        String haveToTwist = "Since your hand is worth less than 15, you have to twist.";
        String stickOrTwist = "Stick or twist?";
        String resultUserBust = "You're bust. \nDealer wins this hand.";
        String outcomeUserFCT = "You have a Five Card Trick! \nDealer's turn, now...";

        if ( userHasToTwist() ) {
            return haveToTwist;
        }
        if ( checkIfUserBust() ) {
            return resultUserBust;
        }
        if ( checkForUserFCT() ) {
            return outcomeUserFCT;
        }
        if ( userCanStickOrTwist() ) {
            return stickOrTwist;
        }
        return null;
    }

    public String checkAppHand() {

        String haveToTwist = "Dealer twists...";
        String resultAppBust = "Dealer is bust! \nYou win this hand.";
        String outcomeAppFCT = "Dealer has a Five Card Trick.";
        String outcomeTwist = "Dealer twists...";
        String outcomeStick = "Dealer sticks...";

        if ( appHasToTwist() ) {
            return haveToTwist;
        }
        else if ( checkIfAppBust() ) {
            return resultAppBust;
        }
        else if ( checkForAppFCT() ) {
            return outcomeAppFCT;
        }
        else if ( appStrategyTwist() ) {
            return outcomeTwist;
        }
        else if ( appStrategyStick() ) {
            return outcomeStick;
        }
        return null;
    }

    public String compareHands() {

        int appValue = getAppHandValue();
        int userValue = getUserHandValue();

        String resultBothFCT = "You both have a Five Card Trick. \nDealer wins this hand.";
        String resultAppFCT = "You have " + userValue + ". \nDealer has a Five Card Trick. \nDealer wins this hand.";
        String resultUserFCT = "You have a Five Card Trick. \nDealer has " + appValue + ". \nYou win this hand!";
        String resultEqualValues = "You have " + userValue + ". \nDealer has the same. \nDealer wins this hand.";
        String resultAppHigher = "You have " + userValue + ". \nDealer has " + appValue + ". \nDealer wins this hand.";
        String resultUserHigher = "You have " + userValue + ". \nDealer has " + appValue + ". \nYou win this hand!";

        if ( checkForAppFCT() && checkForUserFCT() ) {
            return resultBothFCT;
        }
        if ( checkForAppFCT() ) {
            return resultAppFCT;
        }
        if ( checkForUserFCT() ) {
            return resultUserFCT;
        }
        if ( getAppHandValue() == getUserHandValue() ) {
            return resultEqualValues;
        }
        if ( getAppHandValue() > getUserHandValue() ) {
            return resultAppHigher;
        }
        if ( getAppHandValue() < getUserHandValue() ) {
            return resultUserHigher;
        }
        return null;
    }

}
