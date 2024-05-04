import java.util.*;

class PuzzleNode {
    int[][] state;// Represents the current state of the puzzle
    int emptyRow, emptyCol;//Coordinates of the empty cell (0)
    int cost;// Cost from initial state to current state
    int heuristic;// Heuristic value for the current state
    PuzzleNode parent;// Reference to the parent node

     // Constructor to initialize PuzzleNode
    public PuzzleNode(int[][] state, int cost, int heuristic, PuzzleNode parent) {
        this.state = state;
        findEmptyCell();
        this.cost = cost;
        this.heuristic = heuristic;
        this.parent = parent;
    }

        // Method to find the empty cell in the current state
    private void findEmptyCell() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                    return;
                }
            }
        }
    }
}

public class AStarEightPuzzle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting initial and final states from the user
        System.out.println("Enter the initial state (3x3 matrix, 0 represents the empty cell):");
        int[][] initialState = readMatrix(scanner);

        System.out.println("Enter the final state (3x3 matrix):");
        int[][] finalState = readMatrix(scanner);
// Solving the puzzle
        solvePuzzle(initialState, finalState);

        scanner.close();
    }

        // Method to read a 3x3 matrix from the user
    private static int[][] readMatrix(Scanner scanner) {
        int[][] matrix = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        return matrix;
    }

    
    // Method to solve the 8-puzzle using A* algorithm
    public static void solvePuzzle(int[][] initialState, int[][] finalState) {
        // Priority queue to store PuzzleNodes based on their costs and heuristics
        PriorityQueue<PuzzleNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost + node.heuristic));

        // Creating the initial node and adding it to the priority queue
        PuzzleNode initialNode = new PuzzleNode(initialState, 0, calculateHeuristic(initialState, finalState), null);
        priorityQueue.add(initialNode);

        // Set to keep track of visited states to avoid revisiting them
        Set<String> visitedStates = new HashSet<>();

        // Iterating until the priority queue is empty or solution is found
        while (!priorityQueue.isEmpty()) {
            PuzzleNode currentNode = priorityQueue.poll();

            // If current state matches the final state, solution found
            if (Arrays.deepEquals(currentNode.state, finalState)) {
                System.out.println("Solution found!");
                printSolution(currentNode);
                return;
            }
// Adding current state to visited states
            visitedStates.add(Arrays.deepToString(currentNode.state));

            // Exploring neighbors of current state
            exploreNeighbors(currentNode, finalState, priorityQueue, visitedStates);
        }

        System.out.println("No solution found.");
    }

    // Method to explore neighboring states of a given state
    private static void exploreNeighbors(PuzzleNode currentNode, int[][] finalState, PriorityQueue<PuzzleNode> priorityQueue, Set<String> visitedStates) {
        int[] dr = {-1, 1, 0, 0}; // Directions: up, down, left, right
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) { // Iterating through possible moves
            int newRow = currentNode.emptyRow + dr[i];
            int newCol = currentNode.emptyCol + dc[i];

            if (isValidMove(newRow, newCol)) { // Checking if move is valid
                int[][] newState = Arrays.stream(currentNode.state).map(int[]::clone).toArray(int[][]::new);
                newState[currentNode.emptyRow][currentNode.emptyCol] = newState[newRow][newCol];
                newState[newRow][newCol] = 0;

                  // Creating neighbor node
                PuzzleNode neighborNode = new PuzzleNode(newState, currentNode.cost + 1, calculateHeuristic(newState, finalState), currentNode);
// If neighbor state is not visited, add it to priority queue
                if (!visitedStates.contains(Arrays.deepToString(newState))) {
                    priorityQueue.add(neighborNode);
                }
            }
        }
    }
    // Method to check if a move is valid
    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3;
    }

    
    // Method to calculate the Manhattan heuristic for a given state
    private static int calculateHeuristic(int[][] state, int[][] finalState) {
        int heuristic = 0;

        // Calculating heuristic by summing Manhattan distances of each tile from its target position
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] != 0) {
                    int targetRow = (state[i][j] - 1) / 3;
                    int targetCol = (state[i][j] - 1) % 3;
                    heuristic += Math.abs(i - targetRow) + Math.abs(j - targetCol);
                }
            }
        }

        return heuristic;
    }
    
    // Method to print the solution path
    private static void printSolution(PuzzleNode node) {
        List<PuzzleNode> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }

        
        // Printing the solution path in reverse order
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println("Move #" + (path.size() - i - 1) + ":");
            printState(path.get(i).state);
        }
    }

    // Method to print the state of the puzzle
    private static void printState(int[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}