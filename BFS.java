/*****
 * name: Patricia Columbia-Walsh & Quentin Cognon
 ******/

import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class BFS {
    // class variables
    private boolean[] visited;
    private int numV;
    private int start;
    private LinkedList<Integer> adjacencyList[];
    private Queue<Integer> bfsQueue;


    /**
     * Constructor: This creates a new instance of a BFS object.
     */
    public BFS(LinkedList<Integer> adjacencyList[], int start) {
        this.bfsQueue = new LinkedList<> ();
        this.adjacencyList = adjacencyList;
        this.numV = adjacencyList.length;
        this.visited = new boolean[numV];
        this.start =  start;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: traverses an adjacency list in order of breadth first search.
     * BFS starts at a source node and traverses all the nodes at that frontier before moving on.
     */
    public void bfs() {
        // create current node and add start to discovered and queue
        int curr = start;
        visited[start] = true;
        bfsQueue.add(start);

        while (!allDiscovered()) {

            // while the queue is not empty remove the top item
            while (!bfsQueue.isEmpty()) {
                curr = bfsQueue.remove();

                // find all the neighbors of current node and add them to queue and discovered
                Iterator<Integer> i = adjacencyList[curr].listIterator();
                while (i.hasNext()) {
                    int j = i.next();
                    if (!visited[j]) {
                        bfsQueue.add(j);
                        visited[j] = true;
                    }
                }
            }

            // pick a new node if BFS finishes on component
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
