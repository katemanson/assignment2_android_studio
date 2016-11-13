package com.example.user.pontoon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 12/11/2016.
 */

public class PlayerTest {


    Player player;

    @Before
    public void before() {
        player = new Player("Player", true);
    }

    @Test
    public void canGetName() {
        assertEquals("Player", player.getName());
    }

    @Test
    public void canSetName() {
        player.setName("NewName");
        assertEquals("NewName", player.getName());
    }

    @Test
    public void canGetHand() {
        assertEquals(0, player.getHand().countCards());
    }

    @Test
    public void canGetDealerStatus() {
        assertEquals(true, player.getDealerStatus());
    }

    @Test
    public void canSetDealerStatus() {
        player.setDealerStatus(false);
        assertEquals(false, player.getDealerStatus());
    }

}
