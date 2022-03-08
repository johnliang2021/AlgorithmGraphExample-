package com.anylangtech.test;

import java.util.*;

/**
 * @author
 * @version V1.0
 * @description 狄克斯特拉算法，适用于非负加权图
 */
public class Dijkstra {
    // 用散列表表示加权图
    // 准备三个散列表
    public Integer dijkstra(Map<String, Map<String, Integer>> graph) {
        Map<String, Integer> costs = graph.get("start");
        Map<String, String> parents = new HashMap<>();
        // visited列表表示已经处理过的结点
        List<String> visited = new ArrayList<>();
        // 取出未处理结点中花费最小的结点
        String node = findLowestCost(costs, visited);
        // 当存在未处理的结点或者不是终点时循环
        while (node != null && graph.get(node) != null) {
            // 获取结点对应的消耗
            Integer cost = costs.get(node);
            // 获取该结点对应的邻居集合
            Map<String, Integer> map = graph.get(node);
            // 遍历邻居集合，更新邻居的消耗和邻居的父节点
            for (Map.Entry<String, Integer> n : map.entrySet()) {
                Integer newCost = cost + n.getValue();
                // 如果新的消耗小于原来的，则执行更新操作或者不包含在costs表中的，给它加入
                if (!costs.containsKey(n.getKey()) || newCost < costs.get(n.getKey())) {
                    // 更新邻居的消耗和邻居的父节点
                    costs.put(n.getKey(), newCost);
                    parents.put(n.getKey(), node);
                }
            }

            // 遍历完成后将结点加入visited列表
            visited.add(node);
            // 更新接下来要处理的结点，并循环
            node = findLowestCost(costs, visited);
        }
        return costs.get("end");
    }

    // 寻找未处理结点中花费最小的结点
    public String findLowestCost(Map<String, Integer> costs, List<String> visited) {
        Integer lowestCost = Integer.MAX_VALUE;
        String lowestCostNode = null;
        // 遍历costs找出最小花费结点
        for (Map.Entry<String, Integer> node : costs.entrySet()) {
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
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 6);
        map1.put("B", 2);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("end", 1);

        Map<String, Integer> map3 = new HashMap<>();
        map3.put("A", 3);
        map3.put("end", 5);

        graph.put("start", map1);
        graph.put("A", map2);
        graph.put("B", map3);
        graph.put("end", null);

        // costs
        Map<String, Integer> costs = new HashMap<>();
        costs.put("A", 6);
        costs.put("B", 2);
        costs.put("end", Integer.MAX_VALUE);

        // parents
        Map<String, String> parents = new HashMap<>();
        parents.put("A", "start");
        parents.put("B", "start");
        parents.put("end", null);

        Dijkstra dijkstra = new Dijkstra();
        Integer cost = dijkstra.dijkstra(graph);
        System.out.println(cost);
    }


    /**
     * @program: MyCode
     * @description: 狄克斯特拉算法
     * 算法步骤：1.判断是否有未处理的节点
     * 2.若有，获得其中离起点最近的节点
     * 3.遍历该节点所有邻居并更行其开销
     * 4.如果有邻居的开销被更新，同时更新父节点
     * 5.该节点标记为已处理
     * 6.重复第1步
     * @author: like
     * @create: 2018-04-22 10:49
     **/
 /*   //设置没有已知到达路径的标记
    private static int NOWAY_SIGN = Integer.MAX_VALUE;
    private static final String START = "start";
    private static final String END = "end";

    public void getMinStep(String start, String end, Map<String, Map<String, Integer>> graph) {
        //各节点的最少花费
        Map<String, Integer> costs = graph.get(start);
        //各节点最少花费时的父节点
        Map<String, String> parents = new HashMap<String, String>();
        //已处理的节点
        HashSet<String> processed = new HashSet<String>();
        //在未处理的节点中找到开销最小的节点
        String node = findLowestCostNode(costs, processed);
        while (node != null && graph.get(node) != null) {
            int cost = costs.get(node);
            //遍历当前节点的所有邻居
            Iterator iterator = graph.get(node).entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Integer> entry = (Map.Entry) iterator.next();
                //通过node节点到该节点的最小消耗
                int newCost = cost + entry.getValue();
                //更新从start到该节点的最小消耗
                if (!costs.containsKey(entry.getKey()) || costs.get(entry.getKey()) > newCost) {
                    costs.put(entry.getKey(), newCost);
                    parents.put(entry.getKey(), node);
                }
            }
            //该节点加入已处理
            processed.add(node);
            //找出当前最小消耗的节点
            node = findLowestCostNode(costs, processed);
        }
        printPath(parents, costs.get(END));
    }

    public void initParents(String start, Map<String, Integer> startGraph, Map<String, String> parents) {
        Iterator iterator = startGraph.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry) iterator.next();
            parents.put(entry.getKey(), start);
        }
    }

    *//**
     * 找出未处理节点中消耗最小的节点
     *
     * @param costs
     * @param processed
     * @return
     *//*
    public String findLowestCostNode(Map<String, Integer> costs, HashSet<String> processed) {
        int lowestCost = NOWAY_SIGN;
        String lowestCostNode = null;
        Iterator iterator = costs.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry) iterator.next();
            if (!processed.contains(entry.getKey()) && entry.getValue() < lowestCost) {
                lowestCost = entry.getValue();
                lowestCostNode = entry.getKey();
            }
        }
        return lowestCostNode;
    }

    public void printPath(Map<String, String> parents, int cost) {
        Stack<String> stack = new Stack<String>();
        String parent = parents.get(END);
        while (parent != null) {
            if (START.equalsIgnoreCase(parent)) {
                stack.push(START);
                break;
            }
            stack.push(parent);
            parent = parents.get(parent);
        }
        StringBuffer path = new StringBuffer();
        while (!stack.empty()) {
            String node = stack.pop();
            if (path.length() != 0) {
                path.append("->");
            }
            path.append(node);
        }
        System.out.println("最优路线：" + START + "->" + path.toString() + "->" + END);
        System.out.println("其开销为：" + cost);
    }

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = new HashMap<String, Map<String, Integer>>();
        Map<String, Integer> start = new HashMap<String, Integer>();
        start.put("A", 5);
        start.put("B", 2);
        graph.put(START, start);
        Map<String, Integer> a = new HashMap<String, Integer>();
        a.put("C", 4);
        a.put("D", 2);
        graph.put("A", a);
        Map<String, Integer> b = new HashMap<String, Integer>();
        b.put("A", 8);
        b.put("D", 7);
        graph.put("B", b);
        Map<String, Integer> c = new HashMap<String, Integer>();
        c.put("D", 6);
        c.put(END, 3);
        graph.put("C", c);
        Map<String, Integer> d = new HashMap<String, Integer>();
        d.put(END, 1);
        graph.put("D", d);
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.getMinStep(START, END, graph);
    }
*/
}
