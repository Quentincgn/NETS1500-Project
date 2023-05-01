import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DIJKSTRA {

    private List<List<Node>> graph;
    private int numN;
    private int[] dist;
    private boolean[] discovered;
    private PriorityQueue<Node> pq;

    public DIJKSTRA(List<List<Node>> graph, int numN) {
        this.graph = graph;
        this.numN = numN;
        this.dist = new int[numN];
        Arrays.fill(dist, Integer.MAX_VALUE);
        this.discovered = new boolean[numN];
        this.pq = new PriorityQueue<>(numN, Comparator.comparingInt(a -> a.distance));
    }

    public void run(int start) {
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            int curr = pq.poll().node;
            discovered[curr] = true;

            for (Node neighbor : graph.get(curr)) {
                if (!discovered[neighbor.node]) {
                    int newDist = dist[curr] + neighbor.distance;
                    if (newDist < dist[neighbor.node]) {
                        dist[neighbor.node] = newDist;
                        pq.offer(new Node(neighbor.node, newDist));
                    }
                }
            }
        }
    }

    public int[] getShortestDistances() {
        return dist;
    }

    public static void main(String[] args) {
        int numN = 5;
        int sourceNode = 0;
        List<List<Node>> graph = new ArrayList<>(numN);
        for (int i = 0; i < numN; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(new Node(1, 2));
        graph.get(0).add(new Node(2, 4));
        graph.get(1).add(new Node(2, 1));
        graph.get(1).add(new Node(3, 7));
        graph.get(2).add(new Node(3, 1));
        graph.get(2).add(new Node(4, 5));
        graph.get(3).add(new Node(4, 3));

        DIJKSTRA dijkstrasAlgo = new DIJKSTRA(graph, numN);
        dijkstrasAlgo.run(sourceNode);
        int[] shortestDist = dijkstrasAlgo.getShortestDistances();
        System.out.println(Arrays.toString(shortestDist)); // should output [0, 2, 3, 4, 7]
    }

}

class Node {
    int node;
    int distance;

    public Node(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}
