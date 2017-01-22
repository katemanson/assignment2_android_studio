#Pontoon

This was an individual project carried out in Week 8 of the immersive coding course at [CodeClan](https://codeclan.com/). 

The brief was to make an Android app of the card game Pontoon (a UK version of Blackjack). 

##How it works

![soft hand](https://github.com/katemanson/Pontoon/raw/master/img/soft_hand.png)

##Technical
* Java in Android Studio
* Tested with JUnit
* Debugged by logging and using breakpoints within Android Studio

##Pontoon rules used
1. Initial deal of two cards each.
2. Player (the user) plays their hand first, followed by the dealer (the app).
3. If either has a hand worth less than 15, they have to twist.
4. If their hand is worth 15 or more they can choose to stick or to twist.
5. A Five Card Trick effectively ends their turn.
6. During play the initial deal remains face down; twist cards are face up.
7. Provided the dealer and the player finish their turns without going bust, they compare hands. If the hands are of equal value, the dealer wins.