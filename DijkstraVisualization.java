import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DijkstraVisualization extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private VisualizationPanel panel;

    public DijkstraVisualization() {
        setTitle("Dijkstra Visualization");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new VisualizationPanel();
        add(panel);

        setVisible(true);
    }

    private class VisualizationPanel extends JPanel {

        private List<List<Vertex>> graph;

        private Vertex[] vertexList;
        private int numN;
        private int[] dist;
        private boolean[] discovered;
        private PriorityQueue<Node> pq;

        public VisualizationPanel() {
            setBackground(Color.WHITE);
        }

        public void createGraph(List<List<Vertex>> graph, int numN, Vertex[] vertexList) {
            this.graph = graph;
            this.numN = numN;
            this.dist = new int[numN];
            Arrays.fill(dist, Integer.MAX_VALUE);
            this.discovered = new boolean[numN];
            this.vertexList = vertexList;
            this.pq = new PriorityQueue<>(numN, Comparator.comparingInt(a -> a.distance));
            dijkstra(0);
        }

        public void dijkstra(int start) {
            dist[start] = 0;
            pq.offer(new Node(start, 0));
            panel.repaint();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!pq.isEmpty()) {
                int curr = pq.poll().node;
                discovered[curr] = true;
                panel.repaint();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Vertex neighbor : graph.get(curr)) {
                    if (!discovered[neighbor.getLabel()]) {
                        int newDist = dist[curr] + neighbor.getDistance();
                        if (newDist < dist[neighbor.getLabel()]) {
                            dist[neighbor.getLabel()] = newDist;
                            pq.offer(new Node(neighbor.getLabel(), newDist));
                        }
                    }
                }
            }
        }

        private int getDistance(Vertex v1, Vertex v2) {
            return graph.get(v1.getLabel()).get(v2.getLabel()).getDistance();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int diameter = 40;
            int x, y;
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = Math.min(centerX, centerY) - diameter; // adjust radius here
            g.setFont(g.getFont().deriveFont(20.0f));

            for (int i = 0; i < numN; i++) {
                if (discovered[i]) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.BLACK);
                }
                x = (int) (centerX + radius * Math.cos(i * 2 * Math.PI / numN));
                y = (int) (centerY + radius * Math.sin(i * 2 * Math.PI / numN));
                vertexList[i].setX(x);
                vertexList[i].setY(y);
                g.fillOval(x, y, diameter, diameter);
                g.setColor(Color.WHITE);
                g.drawString("" + vertexList[i].getLabel(), x + diameter / 2, y + diameter / 2);
                g.setColor(Color.BLACK);
                if (dist[i] == Integer.MAX_VALUE) {
                    g.drawString("∞", x + diameter / 2 + 20, y + diameter / 2 + 20);
                } else {
                    g.drawString("" + dist[i], x + diameter / 2 + 20, y + diameter / 2 + 20);
                }
            }
            // Draw edges
            g.setColor(Color.BLACK);
            for (int i = 0; i < vertexList.length; i++) {
                for (int j = 0; j < graph.get(i).size(); j++) {
                    int neighborIndex = graph.get(i).get(j).getLabel();
                    Point p1 = new Point(vertexList[i].getX() + diameter / 2, vertexList[i].getY() + diameter / 2);
                    Point p2 = new Point(vertexList[neighborIndex].getX() + diameter / 2, vertexList[neighborIndex].getY() + diameter / 2);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    // Calculate midpoint
                    int midX = (p1.x + p2.x) / 2;
                    int midY = (p1.y + p2.y) / 2;
                    g.drawString(""+graph.get(i).get(j).getDistance(), midX, midY);
                }
            }
        }

    }



    public static void main(String[] args) {
        int numN = 5;
        int sourceNode = 0;
        List<List<Vertex>> graph = new ArrayList<>(numN);
        for (int i = 0; i < numN; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(new Vertex(1, 2));
        graph.get(0).add(new Vertex(2, 4));
        graph.get(1).add(new Vertex(2, 1));
        graph.get(1).add(new Vertex(3, 7));
        graph.get(2).add(new Vertex(3, 1));
        graph.get(2).add(new Vertex(4, 5));
        graph.get(3).add(new Vertex(4, 3));

        Vertex[] vertexList = new Vertex[numN];
        for (int i = 0; i < numN; i++) {
            vertexList[i] = new Vertex(i, 0);
        }
        DijkstraVisualization dijkstraVisualization= new DijkstraVisualization();
        dijkstraVisualization.panel.createGraph(graph, numN, vertexList);
    }

}
