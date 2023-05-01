/*****
 * name: Patricia Columbia-Walsh & Quentin Cognon
 ******/

import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class BFS {
    // class variables
    private boolean[] discovered;
    private int numV;
    private int start;
    private LinkedList<Integer> adjacencyList[];
    private Queue<Integer> queue;


    /**
     * Constructor: This creates a new instance of a BFS object.
     */
    public BFS(LinkedList<Integer> adjacencyList[], int start) {
        this.queue = new LinkedList<> ();
        this.adjacencyList = adjacencyList;
        this.numV = adjacencyList.length;
        this.discovered = new boolean[numV];
        this.start =  start;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: traverses an adjacency list in order of breadth first search.
     * BFS starts at a source node and traverses all the nodes at that frontier before moving on.
     */
    public void bfs() {


        // distance variable to keep track of frontiers
        int[] dist = new int[numV];

        // create current node and add start to discovered and queue
        int curr = start;
        discovered[start] = true;
        queue.add(start);

        while (!allDiscovered()) {

            // while the queue is not empty remove the top item
            while (!queue.isEmpty()) {
                curr = queue.remove();

                // find all the neighbors of current node and add them to queue and discovered
                Iterator<Integer> i = adjacencyList[curr].listIterator();
                while (i.hasNext()) {
                    int j = i.next();
                    if (!discovered[j]) {
                        queue.add(j);
                        discovered[j] = true;

                        // update frontier tracker
                        dist[j] = dist[curr] + 1;
                    }
                }
            }

            // pick a new node if BFS finishes on component
            for (int i = 0; i < discovered.length; i++) {
                if (!discovered[i]) {
                    curr = i;
                }
            }

        }
    }

    public boolean allDiscovered() {
        for (int i = 0; i < discovered.length; i++) {
            if (!discovered[i]) {
                return false;
            }
        }
        return true;
    }
}
