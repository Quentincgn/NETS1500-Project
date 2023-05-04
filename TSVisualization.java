import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TSVisualization extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private VisualizationPanel panel;

    public TSVisualization() {
        setTitle("Topological Sort Visualization");
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
        private int numV;
        private ArrayList<ArrayList<Integer>> adjacencyList;
        private boolean[] discovered;
        private Stack<Integer> stack;


        public VisualizationPanel() {
            setBackground(Color.WHITE);
        }

        public void createGraph(ArrayList<ArrayList<Integer>> adjacencyList, int numV, Vertex[] vertexList) {
            this.numV = numV;
            this.adjacencyList = adjacencyList;
            this.vertexList = vertexList;
            this.discovered = new boolean[numV];
            this.stack = new Stack<>();
            getTopologicalSort();
        }

        public void getTopologicalSort() {
            for (int i = 0; i < numV; i++) {
                if (!discovered[i]) {
                    dfs(i);
                }
            }

            List<Integer> result = new ArrayList<>();
            while (!stack.isEmpty()) {
                result.add(stack.pop());
            }
            Graphics g = super.getGraphics();
            g.setFont(g.getFont().deriveFont(20.0f));
            g.drawString("Topological Sort: " + result.toString(), 10, 20);
        }

        private void dfs(int d) {
            discovered[d] = true;
            panel.repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int v : adjacencyList.get(d)) {
                if (!discovered[v]) {
                    dfs(v);
                }
            }
            stack.push(d);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int diameter = 40;
            int x, y;
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = Math.min(centerX, centerY) - diameter; // adjust radius here
            g.setFont(g.getFont().deriveFont(20.0f));

            for (int i = 0; i < vertexList.length; i++) {
                if (discovered[i]) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.BLACK);
                }
                x = (int) (centerX + radius * Math.cos(i * 2 * Math.PI / numV));
                y = (int) (centerY + radius * Math.sin(i * 2 * Math.PI / numV));
                vertexList[i].setX(x);
                vertexList[i].setY(y);
                g.fillOval(x, y, diameter, diameter);
                g.setColor(Color.WHITE);
                g.drawString("" + vertexList[i].getLabel(), x + diameter / 2, y + diameter / 2);
            }
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
    public static void main(String[] args) {
        TSVisualization tsVisualization = new TSVisualization();
        int numV = 6;
        Vertex[] vertexList = new Vertex[numV];
        for (int i = 0; i < numV; i++) {
            vertexList[i] = new Vertex(i);
        }
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(numV);
        for (int i = 0; i < numV; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        adjacencyList.get(5).add(2);
        adjacencyList.get(2).add(5);
        adjacencyList.get(5).add(0);
        adjacencyList.get(0).add(5);
        adjacencyList.get(4).add(0);
        adjacencyList.get(0).add(4);
        adjacencyList.get(4).add(1);
        adjacencyList.get(1).add(4);
        adjacencyList.get(2).add(3);
        adjacencyList.get(3).add(2);
        adjacencyList.get(3).add(1);
        adjacencyList.get(1).add(3);
        tsVisualization.panel.createGraph(adjacencyList, numV, vertexList);

    }
}
