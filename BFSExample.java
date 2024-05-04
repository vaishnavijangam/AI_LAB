
import java.util.*;

class Graph {
    private int V; // Number of vertices
    private LinkedList<Integer>[] adjList; // Adjacency list representation

    public Graph(int vertices) {
        this.V = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adjList[v].add(w);
        adjList[w].add(v); // Since it's an undirected graph
    }

    public void BFS(int startVertex) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            for (int neighbor : adjList[currentVertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}

public class BFSExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take input for the number of vertices
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        Graph graph = new Graph(vertices);

        // Take input for edges
        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges (vertex1 vertex2):");
        for (int i = 0; i < edges; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.addEdge(v, w);
        }

        // Take input for the starting vertex for BFS
        System.out.print("Enter the starting vertex for BFS: ");
        int startVertex = scanner.nextInt();

        System.out.print("Breadth-First Search starting from vertex " + startVertex + ": ");
        graph.BFS(startVertex);

        scanner.close();
    }
}
