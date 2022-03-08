package com.anylangtech.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author
 * @version V1.0
 * @description 贪婪算法可得到非常接近的解
 */
public class Greedy {
    // 选出一个覆盖了最多未覆盖州的广播台，即使这个广播台已经覆盖了一些已经覆盖了的州也没关系
    // 重复第一步，直到覆盖了所有的州
    public Set<String> greedy(Map<String,Set<String>> stations, Set<String> stationNeeded) {
        // 最终选择的广播台集合
        Set<String> finalStations = new HashSet<>();
        while (!stationNeeded.isEmpty()) {
            // 选出一个覆盖了最多未覆盖州的广播台
            String bestStation = null;
            Set<String> coveredStation = new HashSet<>();
            // 遍历广播台集合
            for (Map.Entry<String,Set<String>> station:stations.entrySet()) {
                Set<String> set = station.getValue();
                set.retainAll(stationNeeded); // 取交集
                if (set.size() > coveredStation.size()) {
                    // 如果它比当前的最佳广播台覆盖广，则将它设为最佳广播台
                    bestStation = station.getKey();
                    coveredStation = station.getValue();
                }
            }
            finalStations.add(bestStation);
            stationNeeded.removeAll(coveredStation); // 取差集，去除已经覆盖的州
        }
        return finalStations;
    }

    public static void main(String[] args) {
        Map<String,Set<String>> stations = new HashMap<>();
        Set<String> set1 = new HashSet<>();
        set1.add("id");
        set1.add("nv");
        set1.add("ut");
        Set<String> set2 = new HashSet<>();
        set2.add("wa");
        set2.add("id");
        set2.add("mt");
        Set<String> set3 = new HashSet<>();
        set3.add("or");
        set3.add("nv");
        set3.add("ca");
        Set<String> set4 = new HashSet<>();
        set4.add("nv");
        set4.add("ut");
        Set<String> set5 = new HashSet<>();
        set5.add("ca");
        set5.add("az");
        stations.put("kone", set1);
        stations.put("ktwo", set2);
        stations.put("kthree", set3);
        stations.put("kfour", set4);
        stations.put("kfive", set5);

        // 需要覆盖的州
        Set<String> stationNeeded = new HashSet<>();
        stationNeeded.add("mt");
        stationNeeded.add("wa");
        stationNeeded.add("or");
        stationNeeded.add("id");
        stationNeeded.add("nv");
        stationNeeded.add("ut");
        stationNeeded.add("ca");
        stationNeeded.add("az");

        Greedy greedy = new Greedy();
        Set<String> set = greedy.greedy(stations, stationNeeded);
        set.forEach(s -> System.out.println(s));
    }
}
