package com.anylangtech.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version V1.0
 * @description 狄克斯特拉算法，适用于非负加权图
 */
public class Dijkstra {
    // 用散列表表示加权图
    // 准备三个散列表
    public Integer dijkstra(Map<String,Map<String,Integer>> graph, Map<String,Integer> costs, Map<String,String> parents) {
        // visited列表表示已经处理过的结点
        List<String> visited = new ArrayList<>();
        // 取出未处理结点中花费最小的结点
        String node = findLowestCost(costs,visited);
        // 当存在未处理的结点时循环
        while (node != null) {
            // 获取结点对应的消耗
            Integer cost = costs.get(node);
            // 获取该结点对应的邻居集合
            Map<String, Integer> map = graph.get(node);
            if (map != null) {
                // 遍历邻居集合，更新邻居的消耗和邻居的父节点
                for(Map.Entry<String, Integer> n:map.entrySet()) {
                    Integer newCost = cost + n.getValue();
                    // 如果新的消耗小于原来的，则执行更新操作
                    if (newCost < costs.get(n.getKey())) {
                        // 更新邻居的消耗和邻居的父节点
                        costs.put(n.getKey(),newCost);
                        parents.put(n.getKey(),node);
                    }
                }
            }
            // 遍历完成后将结点加入visited列表
            visited.add(node);
            // 更新接下来要处理的结点，并循环
            node = findLowestCost(costs,visited);
        }
        return costs.get("终点");
    }

    // 寻找未处理结点中花费最小的结点
    public String findLowestCost(Map<String,Integer> costs,List<String> visited) {
        Integer lowestCost = Integer.MAX_VALUE;
        String lowestCostNode = null;
        // 遍历costs找出最小花费结点
        for(Map.Entry<String, Integer> node:costs.entrySet()) {
            int cost = node.getValue();
            if (!visited.contains(node.getKey()) && cost < lowestCost) {
                // 如果结点没有处理而且比最低花费少，则将其设为最低花费结点
                lowestCost = cost;
                lowestCostNode = node.getKey();
            }
        }
        return lowestCostNode;
    }

    public static void main(String[] args) {
        Map<String,Map<String,Integer>> graph = new HashMap<>();
        Map<String,Integer> map1 = new HashMap<>();
        map1.put("A", 6);
        map1.put("B", 2);

        Map<String,Integer> map2 = new HashMap<>();
        map2.put("终点", 1);

        Map<String,Integer> map3 = new HashMap<>();
        map3.put("A", 3);
        map3.put("终点", 5);

        graph.put("起点", map1);
        graph.put("A", map2);
        graph.put("B", map3);
        graph.put("终点", null);

        // costs
        Map<String,Integer> costs = new HashMap<>();
        costs.put("A",6);
        costs.put("B",2);
        costs.put("终点",Integer.MAX_VALUE);

        // parents
        Map<String,String> parents = new HashMap<>();
        parents.put("A", "起点");
        parents.put("B", "起点");
        parents.put("终点", null);

        Dijkstra dijkstra = new Dijkstra();
        Integer cost = dijkstra.dijkstra(graph, costs, parents);
        System.out.println(cost);
    }
}
