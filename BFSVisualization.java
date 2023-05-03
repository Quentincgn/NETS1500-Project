import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BFSVisualization extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private VisualizationPanel panel;

    public BFSVisualization() {
        setTitle("Breadth-first Search Visualization");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new VisualizationPanel();
        add(panel);

        setVisible(true);
    }

    private class VisualizationPanel extends JPanel {

        private Vertex[] vertexList;
        private ArrayList<ArrayList<Integer>> adjacencyList;
        private Queue<Integer> bfsQueue;
        private LinkedList<Integer> visited;

        private int numVertices;

        public VisualizationPanel() {
            setBackground(Color.WHITE);
        }

        public void createGraph(ArrayList<ArrayList<Integer>> adjacencyList) {
            this.adjacencyList = adjacencyList;
            numVertices = adjacencyList.size();
            vertexList = new Vertex[numVertices];
            bfsQueue = new LinkedList<>();
            visited = new LinkedList<Integer>();
            for (int i = 0; i < numVertices; i++) {
                vertexList[i] = new Vertex(i);
            }
            bfs(0);
        }

        public void bfs(int startVertex) {
            // create current node and add start to discovered and queue
            int curr = startVertex;
            visited.add(startVertex);
            vertexList[startVertex].setVisited(true);
            bfsQueue.add(startVertex);
            panel.repaint();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // while the queue is not empty remove the top item
            while (!bfsQueue.isEmpty()) {
                curr = bfsQueue.remove();
                Iterator<Integer> i = adjacencyList.get(curr).listIterator();
                while (i.hasNext()) {
                    int j = i.next();
                    if (!visited.contains(j)) {
                        bfsQueue.add(j);
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

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int diameter = 40;
            int x, y;
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = Math.min(centerX, centerY) - diameter; // adjust radius here

            for (int i = 0; i < vertexList.length; i++) {
                if (visited.contains(i)) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.BLACK);
                }
                x = (int) (centerX + radius * Math.cos(i * 2 * Math.PI / numVertices));
                y = (int) (centerY + radius * Math.sin(i * 2 * Math.PI / numVertices));
                vertexList[i].setX(x);
                vertexList[i].setY(y);
                g.fillOval(x, y, diameter, diameter);
                g.setColor(Color.WHITE);
                g.drawString("" + vertexList[i].getLabel(), x + diameter / 2, y + diameter / 2);
            }
            g.setColor(Color.BLACK);
            g.setFont(g.getFont().deriveFont(20.0f));
            g.drawString("Discovered nodes: " + visited , 10, 20);
            g.drawString("Queue: " + bfsQueue, 10, 40);

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
        BFSVisualization bfsViz = new BFSVisualization();
        bfsViz.panel.createGraph(adjList);
    }

}
