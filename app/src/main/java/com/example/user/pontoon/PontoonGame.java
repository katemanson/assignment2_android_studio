package com.example.user.pontoon;
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

    public HandValuer getHandValuer() {
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

        String appHand = "Dealer hand: " + appCardsText;
        return appHand;
    }

    public String showAppTwists() {
        ArrayList<Card> appCards = this.appPlayer.getHand().getSet();
        ArrayList<Card> appTwists = new ArrayList<Card>(appCards.subList(2, appCards.size()));

        StringBuilder twistsText = new StringBuilder();
        if ( appCards.size() > 2 ) {
            for ( Card card : appTwists ) {
                twistsText.append("\n" + card.getRank() + " of " + card.getSuit());
            }
        }

        String appHand = "Dealer hand: \n*Card face down*\n*Card face down*" + twistsText;
        return appHand;
    }

    public String hideAppCards() {
        ArrayList<Card> appCards = this.appPlayer.getHand().getSet();

        StringBuilder appCardsText = new StringBuilder();
        for ( Card card : appCards ) {
            appCardsText.append("\n*Card face down*");
        }

        String appHandHidden = "Dealer hand: " + appCardsText;
        return appHandHidden;
    }

    public String showUserCards() {
        ArrayList<Card> userCards = this.userPlayer.getHand().getSet();

        StringBuilder userCardsText = new StringBuilder();
        for ( Card card : userCards ) {
            userCardsText.append("\n" + card.getRank() + " of " + card.getSuit());
        }

        String userHand = "Your hand: " + userCardsText;
        return userHand;
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

        String userHand = "Your hand: \n*Card face down*\n*Card face down*" + twistsText;
        return userHand;
    }

    public String hideUserCards() {
        ArrayList<Card> userCards = this.userPlayer.getHand().getSet();

        StringBuilder userCardsText = new StringBuilder();
        for ( Card card : userCards ) {
            userCardsText.append("\n*Card face down*");
        }

        String userHandHidden = "Your hand: " + userCardsText;
        return userHandHidden;
    }


    public boolean checkForAppPontoon() {
        Hand appHand = this.appPlayer.getHand();
        boolean pontoon = this.handValuer.checkForPontoon(appHand);
        return pontoon;
    }

    public boolean checkForUserPontoon() {
        Hand userHand = this.userPlayer.getHand();
        boolean pontoon = this.handValuer.checkForPontoon(userHand);
        return pontoon;
    }

    public int getAppHandValue() {
        Hand appHand = this.appPlayer.getHand();
        int value = this.handValuer.getHandValue(appHand);
        return value;
    }

    public int getUserHandValue() {
        Hand userHand = this.userPlayer.getHand();
        int value = this.handValuer.getHandValue(userHand);
        return value;
    }

    public int getAppHandSize() {
        Hand appHand = this.appPlayer.getHand();
        int size = appHand.countCards();
        return size;
    }

    public int getUserHandSize() {
        Hand userHand = this.userPlayer.getHand();
        int size = userHand.countCards();
        return size;
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
        boolean bust = this.handValuer.checkIfBust(appHand);
        return bust;
    }

    public boolean checkIfUserBust() {
        Hand userHand = this.userPlayer.getHand();
        boolean bust = this.handValuer.checkIfBust(userHand);
        return bust;
    }

    // FCT - Five Card Trick
    public boolean checkForAppFCT() {
        Hand appHand = this.appPlayer.getHand();
        boolean fiveCardTrick = this.handValuer.checkForFiveCardTrick(appHand);
        return fiveCardTrick;
    }

    // FCT - Five Card Trick
    public boolean checkForUserFCT() {
        Hand userHand = this.userPlayer.getHand();
        boolean fiveCardTrick = this.handValuer.checkForFiveCardTrick(userHand);
        return fiveCardTrick;
    }

    public boolean appHasToTwist() {
        Hand appHand = this.appPlayer.getHand();
        boolean hasToTwist = this.handValuer.checkIfMustTwist(appHand);
        return hasToTwist;
    }

    public boolean userHasToTwist() {
        Hand userHand = this.userPlayer.getHand();
        boolean hasToTwist = this.handValuer.checkIfMustTwist(userHand);
        return hasToTwist;
    }

    public boolean appStrategyTwist() {
        Hand userHand = this.userPlayer.getHand();
        int userTwistValue = this.handValuer.getTwistValue(userHand);
        int userHandSize = getUserHandSize();
        int handSize = getAppHandSize();
        int handValue = getAppHandValue();

        if ( handSize < 5 ) {
            if ( (userHandSize == 2 || userTwistValue > 6) && (handValue >= 15 && handValue <= 18) ) {
                return true;
            }
            if ( handValue == 15 || handValue == 16 ) {
                return true;
            }
        return false;
    }

    public boolean appStrategyStick() {
        int handSize = getAppHandSize();
        int handValue = getAppHandValue();
        if ( handSize < 5 && (handValue >= 17 && handValue <= 21) ) {
            return true;
        }
        return false;
    }

    public boolean userCanStickOrTwist() {
        Hand userHand = this.userPlayer.getHand();
        boolean canStickOrTwist = this.handValuer.canStickOrTwist(userHand);
        return canStickOrTwist;
    }

    public String checkInitialDeal() {

        int appValue = getAppHandValue();
        int userValue = getUserHandValue();
        boolean appPontoon = checkForAppPontoon();
        boolean userPontoon = checkForUserPontoon();

        String resultBothPontoon = "You both have Pontoon. \nDealer wins this hand.";
        String resultAppPontoon = "You have " + userValue + ". \nDealer has Pontoon. \nDealer wins this hand.";
        String resultUserPontoon = "You have Pontoon. \nDealer has " + appValue + ". \nYou win this hand!";

        if ( appPontoon && userPontoon ) {
            return resultBothPontoon;
        }
        if ( appPontoon ) {
            return resultAppPontoon;
        }
        if ( userPontoon ) {
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

        String haveToTwist = "Since their hand is worth less than 15, Dealer must twist...";
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
        boolean appFCT = checkForAppFCT();
        boolean userFCT = checkForUserFCT();

        String resultBothFCT = "You both have a Five Card Trick. \nDealer wins this hand.";
        String resultAppFCT = "You have " + userValue + ". \nDealer has a Five Card Trick. \nDealer wins this hand.";
        String resultUserFCT = "You have a Five Card Trick. \nDealer has " + appValue + ". \nYou win this hand!";
        String resultEqualValues = "You have " + userValue + ". \nDealer has the same. \nDealer wins this hand.";
        String resultAppHigher = "You have " + userValue + ". \nDealer has " + appValue + ". \nDealer wins this hand.";
        String resultUserHigher = "You have " + userValue + ". \nDealer has " + appValue + ". \nYou win this hand!";

        if ( appFCT && userFCT ) {
            return resultBothFCT;
        }
        if ( appFCT ) {
            return resultAppFCT;
        }
        if ( userFCT ) {
            return resultUserFCT;
        }
        if ( appValue == userValue ) {
            return resultEqualValues;
        }
        if ( appValue > userValue ) {
            return resultAppHigher;
        }
        if ( appValue < userValue ) {
            return resultUserHigher;
        }
        return null;
    }

}
