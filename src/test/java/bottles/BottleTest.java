package bottles;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BottleTest {
    private Tub tub;

    @Before
    public void setUp() throws Exception {
        tub = new Tub();
    }

    // --- Basic Functionality Tests --- //
    @Test
    public void fillFromTubThenEmpty() {
        Bottle bottle = new Bottle(4);
        Assert.assertTrue(bottle.getCurrentVolume() == 0);
        bottle.fill(tub.getWater());
        Assert.assertTrue(bottle.getCurrentVolume() == 4);
        bottle.empty();
        Assert.assertTrue(bottle.getCurrentVolume() == 0);
    }

    @Test
    public void fillSmallerBottleFromLargerLeavesTheRightRemainder() {
        Bottle small = new Bottle(3);
        Bottle large = new Bottle(5);
        large.fill(tub.getWater());
        small.fill(large.pour(small.getRemainingCapacity()));
        Assert.assertTrue(small.getCurrentVolume() == small.getMaxCapacity());
        Assert.assertTrue(large.getCurrentVolume() < small.getCurrentVolume() && large.getCurrentVolume() == 2);
    }

    /**
     * The Actual code-test problem solved
     * depending on the definition of a step it can be argued that the problem can be solved in either 4 steps
     * or 8 steps, if each step has "one pour action and one fill or empty action" then it can done in 4 steps,
     * however if each action is counted as a step then it would take 8 steps.
     * */
    @Test
    public void theCodeTestActual() {
        Bottle threeLiter = new Bottle(3);
        Bottle fiveLiter = new Bottle(5);

        threeLiter.fill(tub.getWater());
        fiveLiter.fill(threeLiter.pour(fiveLiter.getRemainingCapacity()));

        threeLiter.fill(tub.getWater());
        fiveLiter.fill(threeLiter.pour(fiveLiter.getRemainingCapacity()));

        // First goal reached, The Three liter bottle contains exactly 1 liter of water
        Assert.assertTrue(threeLiter.getCurrentVolume() == 1);

        fiveLiter.empty();
        fiveLiter.fill(threeLiter.pour(fiveLiter.getRemainingCapacity()));

        threeLiter.fill(tub.getWater());
        fiveLiter.fill(threeLiter.pour(fiveLiter.getRemainingCapacity()));

        // Second goal reached, The Five liter bottle contains exactly 4 liters of water
        Assert.assertTrue(fiveLiter.getCurrentVolume() == 4);
    }

}