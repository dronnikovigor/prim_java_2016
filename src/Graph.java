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
    private final static byte MAX_V = 8;

    public static class Edge {
        private byte x;
        private byte y;
        private int weight;
        private Edge next;
        private Edge last;

        public Edge(byte _x, byte _y, int _weight) {
            x = _x;
            y = _y;
            weight = _weight;
            next = null;
            last = this;
        }

        public byte getX() {
            return x;
        }

        public byte getY() {
            return y;
        }

        public int getWeight() {
            return weight;
        }

        public Edge getNext() {
            return next;
        }

        public void setLast(Edge last) {
            this.last = last;
        }

        public void setNext(Edge next) {
            this.next = next;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public void setX(byte x) {
            this.x = x;
        }

        public void setY(byte y) {
            this.y = y;
        }

    }

    //    public ArrayList<Edge> edges;
    public Edge[] edges;

    /**
     * Инициализирует пустой граф
     */
    public Graph() {
        //edges = new ArrayList<>();
        edges = new Edge[MAX_V + 1];
        clear();
    }

    public void clear() {
        for (int i = 0; i < edges.length; ++i) {
            byte zero = 0;
            Edge e = new Edge(zero, zero, 0);
            edges[i] = e;
        }
    }

    public void addEdge(byte _x, byte _y, int _weight) {
        Edge e = new Edge(_x, _y, _weight);
        for (int i = 1; i < edges.length; ++i) {
            if (edges[i].getX() == _x) {
                edges[i].last.next = e;
                edges[i].last = e;
                return;
            }
        }
        edges[_x] = e;
    }
}