import java.util.*;

public class GraphColoring {
    int V; // Number of vertices
    int[][] graph; // Adjacency matrix
    int[] colors; // Array to store colors of vertices
    boolean[] usedColors; // Array to keep track of used colors
    int chromaticNumber; // Number of colors needed

    public GraphColoring(int V) {
        this.V = V;
        graph = new int[V][V];
        colors = new int[V];
        usedColors = new boolean[V];
        chromaticNumber = 0;
    }

    // Method to check if it is safe to color vertex v with color c
    boolean isSafe(int v, int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && colors[i] == c) {
                return false;
            }
        }
        return true;
    }

    // Backtracking method to color vertices
    boolean graphColoringBacktrack(int v) {
        if (v == V) {
            return true; // All vertices are colored
        }

        for (int c = 1; c <= chromaticNumber; c++) {
            if (isSafe(v, c)) {
                colors[v] = c; // Color vertex v with color c

                if (graphColoringBacktrack(v + 1)) {
                    return true; // Recur to color next vertices
                }

                colors[v] = 0; // Backtrack if solution not found
            }
        }

        return false; // No color found for vertex v
    }

    // Method to find chromatic number using backtracking
    int findChromaticNumberBacktrack() {
        for (int i = 0; i < V; i++) {
            if (graphColoringBacktrack(i)) {
                return chromaticNumber; // Return chromatic number if solution found
            }
            chromaticNumber++; // Increment chromatic number if no solution found
        }
        return -1; // No solution found
    }

    // Branch and bound method to color vertices
    boolean graphColoringBranchBound(int v) {
        if (v == V) {
            return true; // All vertices are colored
        }

        // Initialize lower bound for available colors
        int lb = 1;

        // Find the smallest available color for vertex v
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && colors[i] >= lb) {
                lb = colors[i] + 1; // Update lower bound
            }
        }

        // Try coloring vertex v with each available color
        for (int c = lb; c <= chromaticNumber; c++) {
            if (usedColors[c]) continue; // Skip if color c is already used
            if (isSafe(v, c)) {
                colors[v] = c; // Color vertex v with color c
                usedColors[c] = true; // Mark color c as used

                if (graphColoringBranchBound(v + 1)) {
                    return true; // Recur to color next vertices
                }

                colors[v] = 0; // Backtrack if solution not found
                usedColors[c] = false; // Mark color c as unused
            }
        }

        return false; // No color found for vertex v
    }

    // Method to find chromatic number using branch and bound
    int findChromaticNumberBranchBound() {
        for (int i = 0; i < V; i++) {
            if (graphColoringBranchBound(i)) {
                return chromaticNumber; // Return chromatic number if solution found
            }
            chromaticNumber++; // Increment chromatic number if no solution found
        }
        return -1; // No solution found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        GraphColoring graph = new GraphColoring(V);

        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph.graph[i][j] = scanner.nextInt();
            }
        }

        // Find chromatic number using backtracking
        int chromaticNumberBacktrack = graph.findChromaticNumberBacktrack();
        System.out.println("Chromatic number (Backtracking): " + chromaticNumberBacktrack);

        // Reset colors array and usedColors array for branch and bound
        graph.colors = new int[V];
        graph.usedColors = new boolean[V];
        graph.chromaticNumber = 0;

        // Find chromatic number using branch and bound
        int chromaticNumberBranchBound = graph.findChromaticNumberBranchBound();
        System.out.println("Chromatic number (Branch and Bound): " + chromaticNumberBranchBound);

        scanner.close();
    }
}
