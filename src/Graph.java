import java.util.ArrayList;

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
    public static class Edge {
        private int x;
        private int y;
        private int weight;
        private Edge next;
        private Edge last;

        public Edge(int _x, int _y, int _weight) {
            x = _x;
            y = _y;
            weight = _weight;
            next = null;
            last = this;
        }

        public int getX(){return x;}
        public int getY(){return y;}
        public int getWeight(){return weight;}
        public Edge getNext(){return next;}
    }

    public ArrayList<Edge> edges;

    /**
     * Инициализирует пустой граф
     */
    public Graph() {
        edges = new ArrayList<>();
    }

    public void clear() {
        edges.clear();
    }

    public void addEdge(int _x, int _y, int _weight) {
        Edge e = new Edge(_x, _y, _weight);
        for (int i = 0; i < edges.size(); ++i) {
            if (edges.get(i).x == _x) {
                edges.get(i).last.next = e;
                edges.get(i).last = e;
                return;
            }
        }
        edges.add(e);
    }
}