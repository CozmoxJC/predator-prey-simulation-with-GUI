package l;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a Bear.
 * Bears age, move, eat wolves(foxes,rabbits), and die.
 * 
 * @author Jeremy Chu
 * @version 2021.05.26
 */
public class Bear extends Animal
{
    // Characteristics shared by all Bears (static fields).
    
    // The age at which a Bear can start to breed.
    private static final int BREEDING_AGE = 30;
    // The age to which a Bear can live.
    private static final int MAX_AGE = 350;
    // The likelihood of a Bear breeding.
    private static final double BREEDING_PROBABILITY = 0.1;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a Bear can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 5;
    // The food value of a single fox. In effect, this is the
    // number of steps a Bear can go before it has to eat again.
    private static final int FOX_FOOD_VALUE = 12;
    // The food value of a single wolf. In effect, this is the
    // number of steps a Bear can go before it has to eat again.
    private static final int WOLF_FOOD_VALUE = 20;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The Bear's age.
    private int age;
    // The Bear's food level, which is increased by eating foxes and rabbits.
    private int foodLevel;

    /**
     * Create a Bear. A Bear can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the Bear will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Bear(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE) + rand.nextInt(FOX_FOOD_VALUE) + rand.nextInt(WOLF_FOOD_VALUE);
        }
        else {
            age = 0;
            foodLevel = RABBIT_FOOD_VALUE + FOX_FOOD_VALUE + WOLF_FOOD_VALUE;
        }
    }
    
    /**
     * This is what the Bear does most of the time: it hunts for
     * wolves, foxes and rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newBears A list to add newly born Bears to.
     */
    public void act(List<Animal> newBears)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newBears);            
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
     * Increase the age. This could result in the Bear's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Make this Bear more hungry. This could result in the Bear's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Tell the Bear to look for preys adjacent to its current location.
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
        }
        return null;
    }
    
    /**
     * Check whether or not this Bear is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newBears A list to add newly born Bears to.
     */
    private void giveBirth(List<Animal> newBears)
    {
        // New Bears are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Bear young = new Bear(false, field, loc);
            newBears.add(young);
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
     * A Bear can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
}
