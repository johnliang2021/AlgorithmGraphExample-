package com.anylangtech.test;

import java.util.*;

/**
 * @author
 * @version V1.0
 * @description 广度优先搜索
 */
public class Search {
    // 先定义实现图的数据结构
    public static class Node implements Comparable<Node> {
        private String name;
        private TreeSet<Node> set = new TreeSet<>();

        public Node() {
        }

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public TreeSet<Node> getSet() {
            return set;
        }

        public void setSet(TreeSet<Node> set) {
            this.set = set;
        }

        @Override
        public int compareTo(Node o) {
            return name.hashCode() - o.hashCode();
        }
    }

    public Node init() {// 初始化一个图
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        nodeA.getSet().add(nodeB);
        nodeA.getSet().add(nodeC);
        nodeB.getSet().add(nodeD);
        nodeB.getSet().add(nodeE);
        nodeC.getSet().add(nodeF);
        nodeC.getSet().add(nodeG);
        nodeD.getSet().add(nodeH);
        nodeE.getSet().add(nodeH);
        return nodeA;
    }

    // 访问每个结点的方法
    public void visite(Node node) {
        System.out.println(node.getName());
    }

    // 广度搜索的实现
    public Boolean search(Node start) {
        // 准备一个待检查的队列
        Queue<Node> queue = new LinkedList<>();
        // 准备一个数组用于存储已经检查过的结点
        List<Node> visited = new ArrayList<>();
        // 起始结点放入队列
        queue.add(start);
        // 当队列不为空时
        while (!queue.isEmpty()) {
            // 头结点出队
            Node node = queue.poll();
            // 先判断是否已经检查过
            if (!visited.contains(node)) {
                // 只有没检查过时才执行判断是否为目标结点
                if ("A".equals(node.getName())) {
                    // 如果是目标结点，返回true
                    System.out.println("目标结点是" + node.getName());
                    return true;
                } else {
                    // 如果不是，则先将该结点相邻结点加入队列，再将该结点标为已经检查过
                    TreeSet<Node> set = node.getSet();
                    queue.addAll(set);
                    visited.add(node);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Search search = new Search();
        search.search(search.init());
    }
}
