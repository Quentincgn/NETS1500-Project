import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

    private int numV;
    private List<List<Integer>> adjacencyList;
    private boolean[] discovered;
    private Stack<Integer> stack;

    public TopologicalSort(int numV) {
        this.numV = numV;
        this.adjacencyList = new ArrayList<>(numV);
        for (int i = 0; i < numV; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        this.discovered = new boolean[numV];
        this.stack = new Stack<>();
    }

    public void addEdge(int u, int v) {
        adjacencyList.get(u).add(v);
    }

    public List<Integer> getTopologicalSort() {
        for (int i = 0; i < numV; i++) {
            if (!discovered[i]) {
                dfs(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void dfs(int d) {
        discovered[d] = true;
        for (int v : adjacencyList.get(d)) {
            if (!discovered[v]) {
                dfs(v);
            }
        }
        stack.push(d);
    }

    public static void main(String[] args) {
        TopologicalSort ts = new TopologicalSort(6);
        ts.addEdge(5, 2);
        ts.addEdge(5, 0);
        ts.addEdge(4, 0);
        ts.addEdge(4, 1);
        ts.addEdge(2, 3);
        ts.addEdge(3, 1);

        List<Integer> topologicalSort = ts.getTopologicalSort();
        System.out.println(topologicalSort);
    }
}
