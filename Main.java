import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("What is the algorithm you want to visualize?");

        System.out.println("Do you want to use the default graph (Y) or try your own graph (N)?\n");
        Scanner scanner = new Scanner(System.in);
        String example = scanner.nextLine();
        if (example.equals("Y") || example.equals("y")) {
            System.out.println("1. Breadth-First Search");
            System.out.println("2. Depth-First Search");
            System.out.println("3. Topological Sort");
            System.out.println("4. Dijkstra's Algorithm");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    BFSVisualization.main(args);
                    break;
                case 2:
                    DFSVisualization.main(args);
                    break;
                case 3:
                    TSVisualization.main(args);
                    break;
                case 4:
                    DijkstraVisualization.main(args);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else if (example.equals("N") || example.equals("n")) {
            System.out.println("1. Breadth-First Search");
            System.out.println("2. Depth-First Search");
            System.out.println("3. Topological Sort");
            System.out.println("4. Dijkstra's Algorithm");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    ArrayList<ArrayList<Integer>> adjListBfs = new ArrayList<ArrayList<Integer>>();
                    System.out.println("Enter the number of vertices:\n ");
                    int numVerticesBfs = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < numVerticesBfs; i++) {
                        adjListBfs.add(new ArrayList<Integer>());
                    }
                    System.out.println("Enter the nodes that have an edge between them: the format is 0,3 without spaces and press enter each time.");
                    System.out.println("Enter -1 to stop.");
                    String edge = scanner.nextLine();
                    while (!edge.equals("-1")) {
                        String[] nodes = edge.split(",");
                        int node1 = Integer.parseInt(nodes[0]);
                        int node2 = Integer.parseInt(nodes[1]);
                        adjListBfs.get(node1).add(node2);
                        adjListBfs.get(node2).add(node1);
                        System.out.println("Enter the nodes that have an edge between them: the format is 0,3 without spaces.");
                        System.out.println("Enter -1 to stop.");
                        edge = scanner.nextLine();
                    }
                    BFSVisualization bfsVisualization = new BFSVisualization();
                    bfsVisualization.getPanel().createGraph(adjListBfs);
                    break;
                case 2:
                    ArrayList<ArrayList<Integer>> adjListDfs = new ArrayList<ArrayList<Integer>>();
                    System.out.println("Enter the number of vertices:\n ");
                    int numVerticesDfs = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < numVerticesDfs; i++) {
                        adjListDfs.add(new ArrayList<Integer>());
                    }
                    System.out.println("Enter the nodes that have an edge between them: the format is 0,3 without spaces and press enter each time.");
                    System.out.println("Enter -1 to stop.");
                    String edgeDfs = scanner.nextLine();
                    while (!edgeDfs.equals("-1")) {
                        String[] nodes = edgeDfs.split(",");
                        int node1 = Integer.parseInt(nodes[0]);
                        int node2 = Integer.parseInt(nodes[1]);
                        adjListDfs.get(node1).add(node2);
                        adjListDfs.get(node2).add(node1);
                        System.out.println("Enter the nodes that have an edge between them: the format is 0,3 without spaces.");
                        System.out.println("Enter -1 to stop.");
                        edgeDfs = scanner.nextLine();
                    }
                    DFSVisualization dfsVisualization = new DFSVisualization();
                    dfsVisualization.getPanel().createGraph(adjListDfs);
                    break;
                case 3:
                    ArrayList<ArrayList<Integer>> adjListTs = new ArrayList<ArrayList<Integer>>();
                    System.out.println("Enter the number of vertices:\n ");
                    int numVerticesTs = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < numVerticesTs; i++) {
                        adjListTs.add(new ArrayList<Integer>());
                    }
                    System.out.println("Enter the nodes that have an edge between them: the format is 0,3 without spaces and press enter each time.");
                    System.out.println("Enter -1 to stop.");
                    String edgeTs = scanner.nextLine();
                    while (!edgeTs.equals("-1")) {
                        String[] nodes = edgeTs.split(",");
                        int node1 = Integer.parseInt(nodes[0]);
                        int node2 = Integer.parseInt(nodes[1]);
                        adjListTs.get(node1).add(node2);
                        adjListTs.get(node2).add(node1);
                        System.out.println("Enter the nodes that have an edge between them: the format is 0,3 without spaces.");
                        System.out.println("Enter -1 to stop.");
                        edgeTs = scanner.nextLine();
                    }
                    Vertex[] vertexListTs = new Vertex[numVerticesTs];
                    for (int i = 0; i < numVerticesTs; i++) {
                        vertexListTs[i] = new Vertex(i);
                    }
                    TSVisualization tsVisualization = new TSVisualization();
                    tsVisualization.getPanel().createGraph(adjListTs, numVerticesTs, vertexListTs);
                    break;
                case 4:
                    System.out.println("Enter the number of vertices:\n ");
                    int numVerticesDj = scanner.nextInt();
                    scanner.nextLine();
                    List<List<Vertex>> adjListDj = new ArrayList<>(numVerticesDj);
                    for (int i = 0; i < numVerticesDj; i++) {
                        adjListDj.add(new ArrayList<>());
                    }
                    System.out.println("Enter the nodes that have an edge between them: the format is 0,3,4 without spaces, the two first numbers are the nodes and the last is the cost of the edge between them");
                    System.out.println("Press enter each time.");
                    System.out.println("Enter -1 to stop.");
                    String edgeDj = scanner.nextLine();
                    while (!edgeDj.equals("-1")) {
                        String[] nodes = edgeDj.split(",");
                        int node1 = Integer.parseInt(nodes[0]);
                        int node2 = Integer.parseInt(nodes[1]);
                        int cost = Integer.parseInt(nodes[2]);
                        adjListDj.get(node1).add(new Vertex(node2, cost));
                        adjListDj.get(node2).add(new Vertex(node1, cost));
                        System.out.println("Enter the nodes that have an edge between them: the format is 0,3 without spaces.");
                        System.out.println("Enter -1 to stop.");
                        edgeDj = scanner.nextLine();
                    }
                    Vertex[] vertexListDj = new Vertex[numVerticesDj];
                    for (int i = 0; i < numVerticesDj; i++) {
                        vertexListDj[i] = new Vertex(i);
                    }
                    DijkstraVisualization dijkstraVisualization = new DijkstraVisualization();
                    dijkstraVisualization.getPanel().createGraph(adjListDj, numVerticesDj, vertexListDj);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
