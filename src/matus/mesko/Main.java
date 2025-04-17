package matus.mesko;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(new File("src/matus/mesko/data/hrany_z_excelu.hrn"))) {
            Graph graph = new Graph();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue; // preskočíme prázdne riadky

                String[] parts = line.split("\\s+");
                if (parts.length != 3) {
                    System.err.println("Zlý formát riadku: " + line);
                    continue;
                }

                char u = parts[0].charAt(0);
                char v = parts[1].charAt(0);
                int weight = Integer.parseInt(parts[2]);

                graph.addEdge(u, v, weight);
            }

            List<Edge> mst = KruskalAlgorithm.findMST(graph);
            int totalCost = 0;

            System.out.printf("%-10s %5s\n", "Hrana", "Váha");
            System.out.println("-------------------");

            for (Edge edge : mst) {
                char src = graph.getNodeName(edge.getSrc());
                char dest = graph.getNodeName(edge.getDest());
                int weight = edge.getWeight();

                System.out.printf("%-2s – %-5s %5d\n", src, dest, weight);
                totalCost += weight;
            }

            System.out.println("-------------------");
            System.out.printf("%-10s %5d\n", "Spolu", totalCost);


        } catch (Exception e) {
            System.err.println("Chyba pri načítaní: " + e.getMessage());
        }
    }
}
