package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;


/**
 * Created by JordiM on 25/03/2017.
 */
@RunWith(JUnitQuickcheck.class)
public class DeveloperProerties {

    private static String ANY_NAME = "Pedro";
    private static int ANY_NUMBER_OF_MAXIBONS = 1;

    @Property
    public void theNumberOfMaxibonsAssignedIsPositiveOrZero(int numberOfMaxibons) {
        System.out.println("Number of maxibons initial" + Integer.toString(numberOfMaxibons));
        Developer developer = new Developer(ANY_NAME, numberOfMaxibons);
        System.out.println(developer);
        assertTrue(developer.getNumberOfMaxibonsToGrab() >= 0);
    }
}
