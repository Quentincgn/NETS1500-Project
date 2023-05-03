/*****
 * name: Patricia Columbia-Walsh & Quentin Cognon
 ******/

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class DFS {
    // class variables
    private boolean[] visited;
    private int numV;
    private int startVertex;
    private LinkedList<Integer> adjacencyList[];
    private Stack<Integer> stack;


    /**
     * Constructor: This creates a new instance of a DFS object.
     */
    public DFS(LinkedList<Integer> adjacencyList[], int start) {
        this.stack = new Stack<>();
        this.adjacencyList = adjacencyList;
        this.numV = adjacencyList.length;
        this.visited = new boolean[numV];
        this.startVertex = startVertex;

    }
    /**
     * Inputs: none
     * Outputs: void
     * Description: traverses an adjacency matrix in order of depth first search.
     * DFS starts at a source node and then goes down a path completely before backtracking and
     * moving to next path.
     */
    public void dfs() {
        // create current node and add start to discovered and stack
        int curr = 0;
        visited[startVertex] = true;
        stack.push(startVertex);

        while (!allDiscovered()) {

            // while the stack is not empty remove the top item
            while (!stack.isEmpty()) {
                curr = stack.pop();

                // find neighbors and add them to the stack
                Iterator<Integer> i = adjacencyList[curr].listIterator();
                while (i.hasNext()) {
                    int j = i.next();
                    if (visited[j] == false) {
                        stack.push(j);
                        visited[j] = true;
                        //vertexList[nextNode].setVisited(true);
                    }
                }
            }

            // pick a new node if DFS finishes on component
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    curr = i;
                }
            }
        }
    }

    public boolean allDiscovered() {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}



