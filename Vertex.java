public class Vertex {
    private int label;
    private boolean visited;
    private int x;
    private int y;
    private int distance;

    public Vertex(int label, int distance) {
        this.label = label;
        visited = false;
        this.distance = distance;
    }

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

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }
}