import java.util.*;

public class Bottles {

    /*
     * Challenge: You have 1000 bottles of soda, and exactly one is poisoned. You have 10 test strips which can be used
     * to detect poison. A single drop of poison will turn the test strip positive permanently. You can put any number
     * of drops on a test strip at once and you can reuse a test strip as many times as you'd like (as long as the
     * results are negative). However, you can only run tests once per day and it takes seven days to return a result.
     * How would you figure out the poisoned bottle in as few days as possible? Write code to simulate your approach.
     *
     *
     * Approach: Use ten strips to test 100 bottles each on first day. Guaranteed to come back with one positive result.
     * Next, use the remaining 9 strips to test the batch of 100 that had a positive result. This will leave you with
     * 8 negative strips and 12 possible bottles. Four strips get one bottle and four get two. If the two bottles comes
     * up positive, run tests again to determine which bottle is poisoned.
     */

    public static void main(String args[]) {
        Map<Integer, Boolean> bottles = new HashMap<>(1000);
        int testStripsRemaining = 10;
        int daysTaken = 0;

        populateMap(bottles);

        // We are looking for the single poisoned bottle.
        while (bottles.size() > 1) {
            List<Map<Integer, Boolean>> batches = setupTests(bottles, testStripsRemaining);
            bottles = runTests(batches);
            testStripsRemaining--;
            daysTaken += 7;
        }
        System.out.println("Bottle number " + bottles.keySet().iterator().next() + " has been identified as the " +
                "poisoned bottle in " + daysTaken + " days.");

    }

    /**
     * Create a map of 1000 bottles and choose one at random to be poisoned - denoted by a true boolean.
     *
     * @param bottles
     */
    public static void populateMap(Map<Integer, Boolean> bottles) {
        Random random = new Random();
        int poisonedBottle = random.nextInt(1000);
        System.out.println(poisonedBottle);

        for (int i = 0; i < 1000; i++) {
            bottles.put(i, false);
        }

        bottles.put(poisonedBottle, true);
    }

    /**
     * Create maps containing a equal(ish) amount of bottles. Probably a better way of doing this.
     *
     * @param bottles
     * @param testStripsRemaining
     * @return
     */
    private static List<Map<Integer, Boolean>> setupTests(Map<Integer, Boolean> bottles, int testStripsRemaining) {
        List<Map<Integer, Boolean>> batches = new ArrayList<>(testStripsRemaining);

        int start = getMinimumKey(bottles);

        int cumulativeBatchSize = 0;
        while (cumulativeBatchSize < bottles.size()) {
            // Batch size can change as the batches are created.
            Integer batchSize = (int) Math.ceil((double) (bottles.size() - cumulativeBatchSize) / (testStripsRemaining - batches.size()));

            Map<Integer, Boolean> batch = new HashMap<>(batchSize);
            for (int i = 0; i < batchSize; i++) {
                batch.put(start + cumulativeBatchSize + i, bottles.get(start + cumulativeBatchSize + i));
            }
            batches.add(batch);

            cumulativeBatchSize += batchSize;
        }

        return batches;
    }

    /**
     * The purpose of this method is to determine which batch has the poisoned bottle in it and return that batch
     * for further testing.
     *
     * @param batches
     */
    private static Map<Integer, Boolean> runTests(List<Map<Integer, Boolean>> batches) {
        for (Map<Integer, Boolean> batch : batches) {
            if (batch.values().contains(true)) {
                return batch;
            }
        }
        //this should never happen.
        return null;
    }

    /**
     * This is a necessary method because maps can get out of order. Can't rely on the first value being the lowest.
     *
     * @param map
     * @return
     */
    private static int getMinimumKey(Map<Integer, Boolean> map) {
        Iterator itr = map.keySet().iterator();
        Integer minimum = Integer.MAX_VALUE;
        while (itr.hasNext()) {
            Integer next = (Integer) itr.next();
            minimum = Integer.min(minimum, next);
        }
        return minimum;
    }
}
