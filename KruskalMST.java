import java.util.*;

public class KruskalMST {
    
    // Inner class representing an edge in the graph
    class Edge implements Comparable<Edge> {
        int src, dest, weight;
        
        // Compare method to sort edges based on weight
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
    
    // Method to find the minimum spanning tree using Kruskal's algorithm
    public void kruskal(int[][] graph, int numVertices) {

        // List to store all edges in the graph
        List<Edge> edges = new ArrayList<>();
        // Generating edges from the adjacency matrix representation of the graph
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (graph[i][j] != 0) {
                    Edge edge = new Edge();
                    edge.src = i;
                    edge.dest = j;
                    edge.weight = graph[i][j];
                    edges.add(edge);
                }
            }
        }
        
        // Sorting edges based on their weights
        Collections.sort(edges);
 
        // Array to keep track of parent nodes in the MST
        int[] parent = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            parent[i] = i; // Initializing each vertex as its own parent
        }

        // List to store edges in the MST
        List<Edge> mst = new ArrayList<>();
        
        // Iterating through sorted edges and adding them to MST if they don't form a cycle
        for (Edge edge : edges) {
            int srcParent = find(parent, edge.src); // Finding parent of source vertex
            int destParent = find(parent, edge.dest); // Finding parent of destination vertex
            if (srcParent != destParent) { // If including this edge doesn't create a cycle
                mst.add(edge); // Add edge to MST
                parent[srcParent] = destParent; // Update parent of source vertex
            }
        }
        
        // Printing the edges in the MST along with their weights
        System.out.println("Edges in the MST:");
        int sum = 0; // Variable to store the sum of edge weights in MST
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
            sum += edge.weight; // Updating sum with edge weight
        }
        // Printing the minimum weight of the MST
        System.out.println("Minimum weight of MST: " + sum);
    }
    
    // Method to find the parent of a vertex in the MST
    private int find(int[] parent, int i) {
        if (parent[i] != i) { // If the current vertex is not its own parent
            parent[i] = find(parent, parent[i]); // Recursively find parent until it is found
        }
        return parent[i]; // Return the parent of the vertex
    }
    
    // Main method to take input and call Kruskal's algorithm
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the size of the graph: ");
        int n = in.nextInt(); // Reading the number of vertices in the graph
        int[][] graph = new int[n][n]; // Creating adjacency matrix representation of the graph
        // Taking input for edge weights
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print("Enter the weight " + i + " -> " + j + " of the graph: ");
                graph[i][j] = in.nextInt(); // Reading edge weight from user input
            }
        }
        KruskalMST kruskal = new KruskalMST(); // Creating instance of KruskalMST class
        kruskal.kruskal(graph, n); // Calling Kruskal's algorithm
    }
}


/*
Enter the  size of the graph: 5
Enter the weight 0-> 0 of the graph: 0
Enter the weight 0-> 1 of the graph: 2
Enter the weight 0-> 2 of the graph: 0
Enter the weight 0-> 3 of the graph: 6
Enter the weight 0-> 4 of the graph: 0
Enter the weight 1-> 0 of the graph: 2
Enter the weight 1-> 1 of the graph: 0
Enter the weight 1-> 2 of the graph: 3
Enter the weight 1-> 3 of the graph: 8
Enter the weight 1-> 4 of the graph: 5
Enter the weight 2-> 0 of the graph: 0
Enter the weight 2-> 1 of the graph: 3
Enter the weight 2-> 2 of the graph: 0
Enter the weight 2-> 3 of the graph: 0
Enter the weight 2-> 4 of the graph: 7
Enter the weight 3-> 0 of the graph: 6
Enter the weight 3-> 1 of the graph: 8
Enter the weight 3-> 2 of the graph: 0
Enter the weight 3-> 3 of the graph: 0
Enter the weight 3-> 4 of the graph: 9
Enter the weight 4-> 0 of the graph: 0
Enter the weight 4-> 1 of the graph: 5
Enter the weight 4-> 2 of the graph: 7
Enter the weight 4-> 3 of the graph: 9
Enter the weight 4-> 4 of the graph: 0
Edge	Weight
0 - 1   2
1 - 2   3
0 - 3   6
1 - 4   5
Minimum weight of MST: 16
*/
