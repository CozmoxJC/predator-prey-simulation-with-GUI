import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a Wolf.
 * Wolves age, move, eat foxes(rabbits), and die.
 * 
 * @author Jeremy Chu
 * @version 2021.05.26
 */
public class Wolf extends Animal
{
    // Characteristics shared by all Wolves (static fields).
    
    // The age at which a Wolf can start to breed.
    private static final int BREEDING_AGE = 20;
    // The age to which a wolf can live.
    private static final int MAX_AGE = 250;
    // The likelihood of a wolf breeding.
    private static final double BREEDING_PROBABILITY = 0.2;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a Wolf can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 6;
    // The food value of a single fox. In effect, this is the
    // number of steps a Wolf can go before it has to eat again.
    private static final int FOX_FOOD_VALUE = 14;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The Wolf's age.
    private int age;
    // The Wolf's food level, which is increased by eating foxes and rabbits.
    private int foodLevel;

    /**
     * Create a Wolf. A Wolf can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the Wolf will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Wolf(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE) + rand.nextInt(FOX_FOOD_VALUE);
        }
        else {
            age = 0;
            foodLevel = RABBIT_FOOD_VALUE + FOX_FOOD_VALUE;
        }
    }
    
    /**
     * This is what the Wolf does most of the time: it hunts for
     * foxes and rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newWolves A list to add newly born Wolves to.
     */
    public void act(List<Animal> newWolves)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newWolves);            
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
     * Increase the age. This could result in the Wolf's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Make this Wolf more hungry. This could result in the Wolf's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Tell the Wolf to look for preys adjacent to its current location.
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
        }
        return null;
    }
    
    /**
     * Check whether or not this Wolf is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newWolves A list to add newly born Wolves to.
     */
    private void giveBirth(List<Animal> newWolves)
    {
        // New Wolves are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Wolf young = new Wolf(false, field, loc);
            newWolves.add(young);
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
     * A Wolf can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
}