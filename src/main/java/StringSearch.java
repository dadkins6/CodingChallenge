package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringSearch {

    public static String fullString = "The quick brown fox jumps over the lazy dog";
    public static String[] strings = {"the", "brown", "over", "dog", "to"};

    public static void main(String[] args) {

        Map<String, List<Integer>> locations = getStringLocations(fullString, strings);

        System.out.println(locations);

    }

    public static Map<String, List<Integer>> getStringLocations(String stringToSearch, String[] toFind) {
        Map<String, List<Integer>> locationMap = new HashMap<>();

        for (String s : toFind) {
            List<Integer> locationList = new ArrayList<>();

            int location = -1;
            do {
                location = stringToSearch.toLowerCase().indexOf(s.toLowerCase(), location + 1);

                if (location != -1) {
                    locationList.add(location);
                }

            } while (location >= 0);

            locationMap.put(s, locationList);
        }
        return locationMap;
    }

}
