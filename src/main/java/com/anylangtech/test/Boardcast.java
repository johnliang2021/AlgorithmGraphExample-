package com.anylangtech.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author
 * @version V1.0
 * @description 贪婪算法可得到非常接近的解
 */
public class Boardcast {

    public static String findBiggest (HashSet<String> needed, HashMap<String, String[]> station) {
        LinkedList<String> result = new LinkedList<String>();
        Set<String> stations = station.keySet();
        HashMap<String, Integer> stateContained = new HashMap<String, Integer>();


        //find the best one according to the states it covers
        for (String boardcast : stations) {
            int count = 0;
            for (String state : station.get(boardcast)) {
                if (needed.contains(state) && needed.contains(state)) {
                    count++;
                }
                stateContained.put(boardcast, count);
            }
        }


        int contained = 0;
        String biggest = "";
        for (String boardcast : stateContained.keySet()) {
            if (stateContained.get(boardcast) > contained) {
                contained = stateContained.get(boardcast);
                biggest = boardcast;
            }
        }


        return biggest;
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //the list of all bardcasts
        HashMap<String, String[]> station = new HashMap<String, String[]>();
        station.put("kone", new String[] {"id", "nv", "ut"});
        station.put("ktwo", new String[] {"wa", "id", "mt"});
        station.put("kthree", new String[] {"or", "nv", "ca"});
        station.put("kfour", new String[] {"nv", "ut"});
        station.put("kfive", new String[] {"ca", "az"});


        //the required states
        HashSet<String> needed = new HashSet<String>();
        needed.add("mt");
        needed.add("wa");
        needed.add("or");
        needed.add("id");
        needed.add("nv");
        needed.add("ut");
        needed.add("ca");
        needed.add("az");

        HashSet<String> covered = new HashSet<String>();
        //confirm the boardcasts
        while (!needed.isEmpty()) {
            String boardcast = findBiggest(needed, station);
            System.out.println(boardcast);
            String[] contained = station.get(boardcast);

            for (String state : needed) {
                for (String included : contained) {
                    if (state.equals(included)) {
                        covered.add(state);
                    }
                }
            }
            needed.removeAll(covered);
        }
        covered.forEach(s -> System.out.println(s));
    }

}
