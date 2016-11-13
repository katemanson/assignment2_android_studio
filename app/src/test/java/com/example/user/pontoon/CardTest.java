package com.example.user.pontoon;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Created by user on 12/11/2016.
 */

public class CardTest {

    Card queenOfHearts;

    @Before
    public void before(){
        queenOfHearts = new Card(Suit.HEARTS, Rank.QUEEN);
    }

    @Test
    public void canGetSuit(){
        assertEquals(Suit.HEARTS, queenOfHearts.getSuit());
    }

    @Test
    public void canGetRank(){
        assertEquals(Rank.QUEEN, queenOfHearts.getRank());
    }

}
