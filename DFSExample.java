import java.util.*;

class Graph {
    private int V; // Number of vertices
    private LinkedList<Integer>[] adjList; // Adjacency list

    // Constructor
    Graph(int vertices) {
        V = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Add an edge to the graph
    void addEdge(int v, int w) {
        adjList[v].add(w);
        adjList[w].add(v); // For undirected graph
    }

    // DFS traversal starting from a given vertex
    void DFS(int v) {
        boolean[] visited = new boolean[V];
        DFSUtil(v, visited);
    }

    // Recursive function for DFS traversal
    private void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this vertex
        for (int neighbor : adjList[v]) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }
}

public class DFSExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking the number of vertices as input
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        Graph graph = new Graph(vertices);

        // Taking edges as input
        System.out.println("Enter the edges (vertex1 vertex2): ");
        System.out.println("(Enter -1 -1 to stop)");
        while (true) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();

            if (v1 == -1 || v2 == -1) {
                break;
            }

            graph.addEdge(v1, v2);
        }

        // Taking the starting vertex for DFS as input
        System.out.print("Enter the starting vertex for DFS: ");
        int startVertex = scanner.nextInt();

        System.out.println("DFS traversal starting from vertex " + startVertex + ":");
        graph.DFS(startVertex);

        scanner.close();
    }
}
