import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
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
        private int[][] adjacencyMatrix;
        private Queue<Integer> bfsQueue;
        private int[] visited;

        public VisualizationPanel() {
            setBackground(Color.WHITE);
        }

        public void createGraph(int[][] adjacencyMatrix) {
            this.adjacencyMatrix = adjacencyMatrix;
            int numVertices = adjacencyMatrix.length;
            vertexList = new Vertex[numVertices];
            bfsQueue = new LinkedList<>();
            visited = new int[numVertices];
            for (int i = 0; i < numVertices; i++) {
                vertexList[i] = new Vertex(i);
            }
            bfs(0);
        }

        public void bfs(int startVertex) {
            bfsQueue.add(startVertex);
            visited[startVertex] = 1;
            vertexList[startVertex].setVisited(true);
            panel.repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!bfsQueue.isEmpty()) {
                int node = bfsQueue.remove();
                for (int i = 0; i < adjacencyMatrix[node].length; i++) {
                    if (adjacencyMatrix[node][i] == 1 && visited[i] == 0) {
                        bfsQueue.add(i);
                        visited[i] = 1;
                        vertexList[i].setVisited(true);
                    }
                }
                panel.repaint();
                try {
                    Thread.sleep(1000);
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

            // Draw edges
            g.setColor(Color.BLACK);
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = i + 1; j < adjacencyMatrix[i].length; j++) {
                    if (adjacencyMatrix[i][j] == 1) {
                        Point p1 = new Point(vertexList[i].getX() + diameter / 2, vertexList[i].getY() + diameter / 2);
                        Point p2 = new Point(vertexList[j].getX() + diameter / 2, vertexList[j].getY() + diameter / 2);
                        g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                }
            }
        }
    }

    private class Vertex {
        private int label;
        private boolean visited;
        private int x;
        private int y;
        private int parent; // add parent field

        public Vertex(int label) {
            this.label = label;
            visited = false;
            parent = -1; // initialize parent to -1 by default
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

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }
    }


    public static void main(String[] args) {
        int[][] adjMatrix = {{0, 1, 1, 0, 0, 1}, {1, 0, 1, 0, 1, 0}, {1, 1, 0, 0, 1, 1}, {0, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 0, 0}, {1, 0, 1, 1, 0, 0}};
        BFSVisualization bfsViz = new BFSVisualization();
        bfsViz.panel.createGraph(adjMatrix);
    }
}