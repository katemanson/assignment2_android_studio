#Pontoon

This was an individual project carried out in Week 8 of the immersive coding course at [CodeClan](https://codeclan.com/). 

The brief was to make an Android app of the card game [Pontoon](https://www.pagat.com/banking/pontoon.html) (a UK version of Blackjack). 

##How it works

###Pontoon rules used
1. Initial deal of two cards each.
2. Player (the user) plays their hand first, followed by dealer (the app).
3. If either has a hand worth less than 15, they have to twist.
4. If their hand is worth 15 or more they can choose to stick or to twist.
5. A Five Card Trick (a hand of five cards worth 21 or less) effectively ends their turn.
6. During play the initial deal remains face down; twist cards are face up.
7. Provided the dealer and the player finish their turns without going bust, they compare hands. If the hands are of equal value, the dealer wins.

###Play
####Soft hand
![soft hand](https://github.com/katemanson/Pontoon/raw/master/img/soft_hand.png)

####Dealer twists up to 16
![dealer twists to 16](https://github.com/katemanson/Pontoon/raw/master/img/dealer_16.png)

####Dealer twists up to 18 if player has twisted more than 6
![dealer twists to 18](https://github.com/katemanson/Pontoon/raw/master/img/dealer_18.png)

##Technical
* Java in Android Studio
* Tested with JUnit
* Debugged by logging and using breakpoints within Android Studio

##Highlight
Getting the app (as ‘dealer’) to strategise based on the user’s play. The logic is contained in the PontoonGame.appStrategyTwist() method: 
```
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

```

##Next steps
###Must
* Debug to avoid app crashing on the rules button click
* Use card images rather than text

###Should
* Refine dealer strategy
* Follow Pontoon rules more completely, e.g. switch dealer after a non-dealer has had Pontoon
* Log hands and save results

###Could
* Include instruction or strategy screens (or toasts)
* Introduce betting and the option to 'buy' cards
* Make it multiplayer

###Would
* Provide options to play by different sets of rules (lots of different versions of Pontoon)
* Provide theoretical (? or perhaps better to be experimental?) probabilities, to help with decision making

##Planning

![class diagram](https://github.com/katemanson/Pontoon/raw/master/img/class_diagram.png)
![Android setup](https://github.com/katemanson/Pontoon/raw/master/img/android_setup.png)

