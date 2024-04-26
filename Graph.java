import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Graph {
    private int vertices;
    private List<List<Integer>> adjList;

    public Graph(int v) {
        this.vertices = v;
        this.adjList = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; i++) {
            adjList.add(new LinkedList<>());
        }
    }

    public void addEdges(int s, int d) {
        adjList.get(s).add(d);
        adjList.get(d).add(s);
    }

    private boolean isCyclicUtil(int vertex, boolean[] vis, int parent) {
        vis[vertex] = true;

        for (Integer neighbor : adjList.get(vertex)) {
            if (!vis[neighbor]) {
                if (isCyclicUtil(neighbor, vis, vertex)) {
                    return true;
                }
            } else if (neighbor != parent) { // if neighbor is not equal to parent then cycle should be present.
                return true;
            }
        }

        return false;
    }

    public boolean isCyclic() {
        boolean[] vis = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!vis[i]) {
                if (isCyclicUtil(i, vis, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Graph graph = new Graph(5);

        // Example edges for testing (you can modify these accordingly)
        graph.addEdges(0, 1);
        //graph.addEdges(2, 1);
        graph.addEdges(2, 0);
        graph.addEdges(1, 3);
        graph.addEdges(3, 4);

        if (graph.isCyclic()) {
            System.out.println("The graph contains a cycle.");
        } else {
            System.out.println("The graph does not contain a cycle.");
        }
    }
}
