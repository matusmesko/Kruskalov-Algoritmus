package matus.mesko;

import java.util.*;

public class Graph {
    private final List<Edge> edges = new ArrayList<>();
    private final Map<Character, Integer> nodeToIndex = new HashMap<>();
    private final Map<Integer, Character> indexToNode = new HashMap<>();
    private int nodeCount = 0;

    public void addEdge(char u, char v, int weight) {
        if (!nodeToIndex.containsKey(u)) {
            nodeToIndex.put(u, nodeCount);
            indexToNode.put(nodeCount, u);
            nodeCount++;
        }
        if (!nodeToIndex.containsKey(v)) {
            nodeToIndex.put(v, nodeCount);
            indexToNode.put(nodeCount, v);
            nodeCount++;
        }
        int src = nodeToIndex.get(u);
        int dest = nodeToIndex.get(v);
        edges.add(new Edge(src, dest, weight));
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    public int getVertexCount() {
        return nodeCount;
    }

    public Character getNodeName(int index) {
        return indexToNode.get(index);
    }
}
