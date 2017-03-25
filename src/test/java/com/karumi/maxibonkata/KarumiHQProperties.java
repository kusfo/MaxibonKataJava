package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by JordiM on 25/03/2017.
 */
@RunWith(JUnitQuickcheck.class)
public class KarumiHQProperties {

    private Chat chat;
    private KarumiHQs karumiHQs;

    @Before
    public void setUp() throws Exception {
        chat = mock(ConsoleChat.class);
        karumiHQs = new KarumiHQs(chat);
    }

    @Property
    public void theNumberOfMaxibonsInFridgeIsAlwaysAtLeastTwoWhenDeveloperCameAlone(
        @From(DevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);

        assertTrue(karumiHQs.getMaxibonsLeft() >= 2);
    }

    @Property
    public void theNumberOfMaxibonsInFridgeIsAlwaysAtLeastTwoWhenDeveloperCameInGroup(
            List<@From(DevelopersGenerator.class) Developer>  developerList) {

        karumiHQs.openFridge(developerList);

        assertTrue(karumiHQs.getMaxibonsLeft() >= 2);
    }

    @Property
    public void givenHungryDevelopersWhenOpenFridgeThenChatMessageSend(
            @From(HungryDevelopersGenerator.class) Developer  developer) {

        karumiHQs.openFridge(developer);

        verify(chat).sendMessage("Hi guys, I'm " + developer.getName() + ". We need more maxibons!");
    }

    @Property
    public void givenNotSoHungryDevelopersWhenOpenFridgeThenChatMessageNotSend(
            @From(NotSoHungryDevelopersGenerator.class) Developer  developer) {

        karumiHQs.openFridge(developer);

        verify(chat, never()).sendMessage(any());
    }
}
