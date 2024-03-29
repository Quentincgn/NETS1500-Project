import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DFSVisualization extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private VisualizationPanel panel;

    public DFSVisualization() {
        setTitle("Depth-first Search Visualization");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new VisualizationPanel();
        add(panel);

        setVisible(true);
    }

    public VisualizationPanel getPanel() {
        return panel;
    }
    public class VisualizationPanel extends JPanel {

        private Vertex[] vertexList;
        private ArrayList<ArrayList<Integer>> adjacencyList;
        private Stack<Integer> dfsStack;
        private LinkedList<Integer> visited;

        public VisualizationPanel() {
            setBackground(Color.WHITE);
        }

        /**
         * Creates the graph and starts the DFS algorithm
         * @param adjacencyList
         */
        public void createGraph(ArrayList<ArrayList<Integer>> adjacencyList) {
            this.adjacencyList = adjacencyList;
            int numVertices = adjacencyList.size();
            vertexList = new Vertex[numVertices];
            dfsStack = new Stack<>();
            visited = new LinkedList<Integer>();
            for (int i = 0; i < numVertices; i++) {
                vertexList[i] = new Vertex(i);
            }
            dfs(0); // Start from vertex 0
        }

        /**
         * Depth-first search algorithm
         * @param startVertex
         */
        public void dfs(int startVertex) {
            // create current node and add start to discovered and stack
            int curr = 0;
            dfsStack.push(startVertex);
            visited.add(startVertex);
            vertexList[startVertex].setVisited(true);
            panel.repaint();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // while stack is not empty remove the top item
            while (!dfsStack.isEmpty()) {
                curr = dfsStack.pop();
                    // find neighbors and add them to the stack
                    Iterator<Integer> i = adjacencyList.get(curr).listIterator();
                    while (i.hasNext()) {
                        int j = i.next();
                        if (!visited.contains(j)) {
                            dfsStack.push(j);
                            visited.add(j);
                            vertexList[j].setVisited(true);
                        }
                    }
                panel.repaint();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * Paints the graph
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int diameter = 40;
            int x, y;
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = Math.min(centerX, centerY) - diameter; // adjust radius here
            g.setFont(g.getFont().deriveFont(20.0f));

            for (int i = 0; i < vertexList.length; i++) {
                if (vertexList[i].isVisited()) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.BLACK);
                }
                x = (int) (centerX + radius * Math.cos(i * 2 * Math.PI / vertexList.length));
                y = (int) (centerY + radius * Math.sin(i * 2 * Math.PI / vertexList.length));
                vertexList[i].setX(x);
                vertexList[i].setY(y);
                g.fillOval(x, y, diameter, diameter);
                g.setColor(Color.WHITE);
                g.drawString("" + vertexList[i].getLabel(), x + diameter / 2, y + diameter / 2);
            }
            g.setColor(Color.BLACK);
            g.setFont(g.getFont().deriveFont(20.0f));
            g.drawString("Discovered nodes: " + visited , 10, 20);
            g.drawString("Stack: " + dfsStack, 10, 40);

            // Draw edges
            g.setColor(Color.BLACK);
            for (int i = 0; i < vertexList.length; i++) {
                for (int j = 0; j < adjacencyList.get(i).size(); j++) {
                    int neighborIndex = adjacencyList.get(i).get(j);
                    Point p1 = new Point(vertexList[i].getX() + diameter / 2, vertexList[i].getY() + diameter / 2);
                    Point p2 = new Point(vertexList[neighborIndex].getX() + diameter / 2, vertexList[neighborIndex].getY() + diameter / 2);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }

    private class Vertex {
        private int label;
        private boolean visited;
        private int x;
        private int y;

        public Vertex(int label) {
            this.label = label;
            visited = false;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public int getLabel() {
            return label;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        adjList.add(new ArrayList<Integer>() {{add(1); add(2); add(5);}});
        adjList.add(new ArrayList<Integer>() {{add(0); add(2); add(4);}});
        adjList.add(new ArrayList<Integer>() {{add(0); add(1); add(4); add(5); add(3);}});
        adjList.add(new ArrayList<Integer>() {{add(4); add(5);}});
        adjList.add(new ArrayList<Integer>() {{add(1); add(2); add(3); add(6); add(7); }});
        adjList.add(new ArrayList<Integer>() {{add(0); add(2); add(3); add(8);}});

        adjList.add(new ArrayList<Integer>() {{add(4);}});

        adjList.add(new ArrayList<Integer>() {{add(4);}});

        adjList.add(new ArrayList<Integer>() {{add(5); add(9);}});
        adjList.add(new ArrayList<Integer>() {{add(8);}});

        DFSVisualization dfsViz = new DFSVisualization();
        dfsViz.panel.createGraph(adjList);
    }
}
