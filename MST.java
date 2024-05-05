import java.util.*;

class Graph {
    private int vertices;
    private List<List<Edge>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjacencyList.get(source).add(edge);
        edge = new Edge(destination, source, weight);
        adjacencyList.get(destination).add(edge);
    }

    public void primMST() {
        boolean[] visited = new boolean[vertices];
        PriorityQueue<Edge> pq = new PriorityQueue<>(vertices, Comparator.comparingInt(o -> o.weight));

        // Add the first vertex to the MST
        int source = 0;
        visited[source] = true;
        for (Edge edge : adjacencyList.get(source)) {
            pq.add(edge);
        }

        // MST edges set
        List<Edge> mst = new ArrayList<>();

        // Loop to find the MST
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.destination;

            // If adding edge u-v creates a cycle, skip this edge
            if (visited[u])
                continue;

            // Add the edge to the MST
            mst.add(edge);
            visited[u] = true;

            // Add the adjacent edges of the newly added vertex to the priority queue
            for (Edge adjacentEdge : adjacencyList.get(u)) {
                if (!visited[adjacentEdge.destination])
                    pq.add(adjacentEdge);
            }
        }

        // Print the MST edges
        System.out.println("Minimum Spanning Tree edges:");
        int totalWeight = 0;
        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
            totalWeight += edge.weight;
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }

    class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
}

public class MST {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();
        Graph graph = new Graph(vertices);

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges in format (source, destination, weight):");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }

        graph.primMST();
    }
}
