import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a Hunter.
 * Hunters age, move, eat foxes(rabbits), and die.
 * 
 * @author Jeremy Chu
 * @version 2021.05.26
 */
public class Hunter extends Animal
{
    // Characteristics shared by all Hunters (static fields).
    
    // The age at which a Hunter can start to breed.
    private static final int BREEDING_AGE = 50;
    // The age to which a Hunter can live.
    private static final int MAX_AGE = 450;
    // The likelihood of a Hunter breeding.
    private static final double BREEDING_PROBABILITY = 0.05;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a Hunter can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 4;
    // The food value of a single fox. In effect, this is the
    // number of steps a Hunter can go before it has to eat again.
    private static final int FOX_FOOD_VALUE = 10;
    // The food value of a single wolf. In effect, this is the
    // number of steps a Hunter can go before it has to eat again.
    private static final int WOLF_FOOD_VALUE = 15;
    // The food value of a single bear. In effect, this is the
    // number of steps a Hunter can go before it has to eat again.
    private static final int BEAR_FOOD_VALUE = 30;
    // A shared random number generator to control breeding.
    // A shared random number generator to control breeding.
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The Hunter's age.
    private int age;
    // The Hunter's food level, which is increased by eating foxes and rabbits.
    private int foodLevel;

    /**
     * Create a Hunter. A Hunter can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the Hunter will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Hunter(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE) + rand.nextInt(FOX_FOOD_VALUE) + rand.nextInt(WOLF_FOOD_VALUE) + rand.nextInt(BEAR_FOOD_VALUE);
        }
        else {
            age = 0;
            foodLevel = RABBIT_FOOD_VALUE + FOX_FOOD_VALUE + WOLF_FOOD_VALUE + BEAR_FOOD_VALUE;
        }
    }
    
    /**
     * This is what the Hunter does most of the time: it hunts for
     * bears, wolves, foxes and rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newHunters A list to add newly born Hunters to.
     */
    public void act(List<Animal> newHunters)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newHunters);            
            // Move towards a source of food if found.
            Location location = getLocation();
            Location newLocation = findFood(location);
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(location);
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Increase the age. This could result in the Hunter's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Make this Hunter more hungry. This could result in the Hunter's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Tell the Hunter to look for preys adjacent to its current location.
     * Only the first live prey is eaten.
     * @param location Where in the field it is located.
     * @return Where food was found, or null if it wasn't.
     */
     private Location findFood(Location location)
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
            if(animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if(fox.isAlive()) { 
                    fox.setDead();
                    foodLevel = FOX_FOOD_VALUE;
                    // Remove the dead fox from the field.
                    return where;
                }
            }
            if(animal instanceof Wolf) {
                Wolf wolf = (Wolf) animal;
                if(wolf.isAlive()) { 
                    wolf.setDead();
                    foodLevel = WOLF_FOOD_VALUE;
                    // Remove the dead wolf from the field.
                    return where;
                }
            }
            if(animal instanceof Bear) {
                Bear bear = (Bear) animal;
                if(bear.isAlive()) { 
                    bear.setDead();
                    foodLevel = BEAR_FOOD_VALUE;
                    // Remove the dead bear from the field.
                    return where;
                }
            }
        }
        return null;
    }
    
    /**
     * Check whether or not this Hunter is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newHunters A list to add newly born Hunters to.
     */
    private void giveBirth(List<Animal> newHunters)
    {
        // New Hunters are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Hunter young = new Hunter(false, field, loc);
            newHunters.add(young);
        }
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A Hunter can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
}