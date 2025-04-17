package matus.mesko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {
    public static List<Edge> findMST(Graph graph) {
        int V = graph.getVertexCount();
        List<Edge> result = new ArrayList<>();
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);

        int[] parent = new int[V];
        int[] rank = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int e = 0, i = 0;
        while (e < V - 1 && i < edges.size()) {
            Edge next = edges.get(i++);
            int x = find(parent, next.getSrc());
            int y = find(parent, next.getDest());

            if (x != y) {
                result.add(next);
                union(parent, rank, x, y);
                e++;
            }
        }

        return result;
    }

    private static int find(int[] parent, int i) {
        if (parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }

    private static void union(int[] parent, int[] rank, int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);

        if (rank[xroot] < rank[yroot]) {
            parent[xroot] = yroot;
        } else if (rank[xroot] > rank[yroot]) {
            parent[yroot] = xroot;
        } else {
            parent[yroot] = xroot;
            rank[xroot]++;
        }
    }
}
