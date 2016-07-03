/**
 * Created by Lyushnina Elena on 24.06.2016.
 */

/**
 * Класс Graph представляет собой ориетированный граф, для
 * реализации которого использовалась обощенная коллекция ArrayList.
 * В ней содержатся элементы вложенного класса Edge, представляющего
 * из себя направленное ребро графа.
 * Класс поддерживает операции добавления ребра и очистки графа.
 * Операция clear работает за константное время. Операция addEdge работает
 * работает за время O(V).
 *
 * @author Lyushnina Elena
 */
public class Graph implements Graph_interface {
    protected final static int MAX_V = 40;

    public static class Edge {
        private int x;
        private int y;
        private int weight;
        private Edge next;

        public Edge(int _x, int _y, int _weight) {
            x = _x;
            y = _y;
            weight = _weight;
            next = null;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWeight() {
            return weight;
        }

        public Edge getNext() {
            return next;
        }
    }

    public Edge[] edges;

    /**
     * Инициализирует пустой граф
     */
    public Graph() {
        edges = new Edge[MAX_V + 1];
        clear();
    }

    public void clear() {
        for (int i = 0; i < edges.length; ++i) {
            Edge e = new Edge(0, 0, 0);
            edges[i] = e;
        }
    }

    public void addEdge(int _x, int _y, int _weight) {
        Edge e = new Edge(_x, _y, _weight);

        e.next = edges[_x];
        edges[_x] = e;

        Edge _e = new Edge(_y, _x, _weight);

        _e.next = edges[_y];
        edges[_y] = _e;
    }
}