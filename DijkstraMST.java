import java.util.*;

public class DijkstraMST {
    
    // Private member variables to store graph information
    private int numVertices; // Number of vertices in the graph
    private int[] dist; // Array to store shortest distances from source to each vertex
    private boolean[] visited; // Array to mark visited vertices
    private int[][] graph; // Adjacency matrix representing the graph
    
    // Constructor to initialize the graph and its properties
    public DijkstraMST(int[][] graph, int numVertices) {
        this.numVertices = numVertices; // Initialize number of vertices
        this.graph = graph; // Initialize graph
        this.dist = new int[numVertices]; // Initialize distance array
        this.visited = new boolean[numVertices]; // Initialize visited array
    }
    
    // Method to perform Dijkstra's algorithm to find shortest paths
    public void dijkstra(int startVertex) {
        // Initialize distance array and visited array
        for (int i = 0; i < numVertices; i++) {
            dist[i] = Integer.MAX_VALUE; // Set distance to all vertices to infinity
            visited[i] = false; // Mark all vertices as not visited
        }
        
        dist[startVertex] = 0; // Distance from source to itself is 0
        // Iterate over all vertices
        for (int i = 0; i < numVertices - 1; i++) {
            int u = minDistance(dist, visited); // Find the vertex with minimum distance
            
            visited[u] = true; // Mark the vertex as visited
            
            // Update distances to adjacent vertices if shorter path is found
            for (int v = 0; v < numVertices; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v]; // Update distance
                }
            }
        }
        
        printMST(startVertex); // Print the shortest distances from the source vertex
    }
    
    // Helper method to find the vertex with minimum distance from the source
    private int minDistance(int[] dist, boolean[] visited) {
        int minDist = Integer.MAX_VALUE; // Initialize minimum distance
        int minIndex = -1; // Initialize index of vertex with minimum distance
        
        // Iterate over all vertices
        for (int i = 0; i < numVertices; i++) {
            // Find the vertex with minimum distance which is not visited
            if (!visited[i] && dist[i] <= minDist) {
                minDist = dist[i]; // Update minimum distance
                minIndex = i; // Update index of vertex with minimum distance
            }
        }
        
        return minIndex; // Return index of vertex with minimum distance
    }
    
    // Method to print the shortest distances from the source vertex
    private void printMST(int startVertex) {
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < numVertices; i++) {
            System.out.println(i + "\t" + dist[i]); // Print vertex and its distance from source
        }
    }
    
    // Main method to take input and execute Dijkstra's algorithm
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Scanner object to read input
        System.out.print("Enter the size of the graph: ");
        int n = in.nextInt(); // Read number of vertices in the graph
        int[][] graph = new int[n][n]; // Create adjacency matrix for the graph
        // Loop to take input weights for each edge
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print("Enter the weight "+i+ "-> "+j+" of the graph: ");
                graph[i][j]=in.nextInt(); // Read weight of edge (i, j)
            }
        } 
        DijkstraMST dijkstra = new DijkstraMST(graph,n); // Create DijkstraMST object with graph and number of vertices
        System.out.print("Enter the starting vertex of the graph: ");
        int vertex=in.nextInt(); // Read starting vertex for Dijkstra's algorithm
        dijkstra.dijkstra(vertex); // Execute Dijkstra's algorithm with the given starting vertex
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
Enter the starting vertex of the graph: 1
Vertex   Distance from Source
0       2
1       0
2       3
3       8
4       5 
*/
