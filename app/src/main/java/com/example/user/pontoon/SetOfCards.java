package com.example.user.pontoon;
import java.util.*;

/**
 * Created by user on 12/11/2016.
 */

public class SetOfCards {


    public ArrayList<Card> set;

    public SetOfCards() {
        this.set = new ArrayList<Card>();
    }

    public ArrayList<Card> getSet() {
        return this.set;
    }

    public void addCard(Card card) {
        this.set.add(card);
    }

    public int countCards() {
        return this.set.size();
    }

    public void addCardsFrom(SetOfCards otherSet) {
        ArrayList<Card> cardsFromOtherSet = otherSet.getSet();
        this.set.addAll(cardsFromOtherSet);
    }

    public void addCardsFrom(ArrayList<Card> cardArrayList) {
        this.set.addAll(cardArrayList);
    }

    public void shuffle() {
        Collections.shuffle(this.set);
    }

    public void cut() {
        Random randomThing = new Random();
        // set cutDistance to int between 1 and 1 less than set.size()
        // this means the cut will involve a minimum of a 1-card shift
        int cutDistance = 1 + randomThing.nextInt(this.set.size() - 1);
        Collections.rotate(this.set, cutDistance);
    }

    public Card getCardWithIndex(int index) {
        return this.set.get(index);
    }

    public Card removeCardWithIndex(int index) {
        return this.set.remove(index);
    }

    public Card getFirstCard() {
        return this.set.get(0);
    }

    public Card removeFirstCard() {
        return this.set.remove(0);
    }

    public void removeAllCards() {
        this.set.clear();
    }

}
