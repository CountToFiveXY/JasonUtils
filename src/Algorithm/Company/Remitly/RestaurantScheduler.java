package Algorithm.Company.Remitly;

import java.util.*;

/**
 * Constructor of restaurant takes in max allowed time to order and MenuItems (define MenuItem however you want)
 *
 * class Restaurant (
 *   // This is the promised maximum wait time that the restaurant allows for any given order
 *   maxAllowedTimeOrder: Int,
 *
 *   // These are the only items that the restaurant supports.
 *   // Each menu items has the name of the item and the time it takes to cook the item
 *   menuItems: List<MenuItem>
 * ) {
 *
 *   fun order(meal: String) //Order a meal from restaurant
 *   fun getNumberCooks(): Int //Returns the number of cooks
 *   fun incrementTime(minutes: Int) //Add this amount of time to clock at the restaurant
 *   fun getCurrentTime(): Int //Returns the current time at the restaurant
 *
 *   // Additional Method (You do NOT need to implement this or think about how to implement this-included for clarity with examples)
 *   fun printCurrentOrders() //Prints out the state of meals that are currently being worked on at the restaurant
 * }
 *
 * There are several constraints for how this restaurant functions (IMPORTANT-Read each one carefully)
 *
 * 1. The restaurant starts with 0 cooks.
 * 2. The restaurant time starts at 0 and is only changed by the incrementTime function.
 * 3. This restaurant will immediately add a cook if the maximum wait time will be exceeded by the current order.
 * 4. The restaurant will never decrease the amount of cooks once a cook has been added.
 * 5. Each cook can only work on one meal at a time and will work on their current meal until they have finished it.
 * 6. Each meal can only be worked on by one cook at a time.
 * 7. Each meal is assigned to the cook that can start that meal the soonest when the meal is ordered.
 *    Note that this means the goal is not to strictly minimize the total number of cooks!
 */
public class RestaurantScheduler {

    static class MenuItem {
        String name;
        int cookTime;

        public MenuItem(String name, int cookTime) {
            this.name = name;
            this.cookTime = cookTime;
        }
    }

    static class Restaurant {
        int clocks;
        int cooks;
        int freeCooks;
        int maxAllowedTimeOrder;
        Map<String, Integer> map;

        PriorityQueue<Integer> nextAvailabeTime;

        public Restaurant(int maxAllowedTimeOrder, List<MenuItem> menuItems) {
            // TODO
            this.maxAllowedTimeOrder = maxAllowedTimeOrder;
            cooks = 0;
            freeCooks = 0;
            clocks = 0;
            map = new HashMap<>();
            nextAvailabeTime = new PriorityQueue<>();
            for (MenuItem item: menuItems) {
                map.put(item.name, item.cookTime);
            }
        }

        public void order(String meal) {
            // TODO

            //what if waiting time > max time?
            //what if meal not in the menu?
            int currentTime = getCurrentTime();
            int cookingTime = map.get(meal);

            //free new cook
            while(!nextAvailabeTime.isEmpty() && nextAvailabeTime.peek() <= currentTime) {
                nextAvailabeTime.poll();
                freeCooks ++;
            }

            if (freeCooks > 0) {
                freeCooks--;
                nextAvailabeTime.offer(currentTime + cookingTime);
                return;
            }


            //if there is no freecook and no one in the job line, then we need a new cook
            if (nextAvailabeTime.isEmpty()) {
               //no one working
               cooks++;
               //add a new cook who will be available at cookingTime
               nextAvailabeTime.offer(currentTime + cookingTime);
            } else {
               int fastestCookAvailableTime = nextAvailabeTime.peek();
               int orderFinishTime = fastestCookAvailableTime + cookingTime;

               if (orderFinishTime <= currentTime + maxAllowedTimeOrder) {
                   //inside time, use existing cook to do it
                   nextAvailabeTime.poll();
                   nextAvailabeTime.offer(orderFinishTime);
               } else {
                   //we can't wait for existing cook so hire a new cook to do the job
                   cooks++;
                   nextAvailabeTime.offer(currentTime + cookingTime);
               }
           }
        }

        public int getNumberCooks() {
            // TODO
            return cooks;
        }

        public void incrementTime(int minutes) {
            // TODO
            clocks += minutes;

        }

        public int getCurrentTime() {
            // TODO
            return clocks;
        }

        public void printCurrentOrders() {
            // TODO
        }
    }

    public static void main(String[] args) {

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("steak", 15));
        menuItems.add(new MenuItem("pizza", 20));
        menuItems.add(new MenuItem("pasta", 10));
        menuItems.add(new MenuItem("french fries", 5));

        Restaurant rest = new Restaurant(30, menuItems);

        rest.order("steak");
        rest.order("pizza");
        rest.order("pizza");
        rest.order("pasta");

        System.out.println("The time is " + rest.getCurrentTime());
        System.out.println("The number of cooks is " + rest.getNumberCooks());
        System.out.println();

        rest.printCurrentOrders();
    }
}