/**
 * Created by Lyushnina Elena on 26.06.2016.
 */

/**
 * Класс Algorithm реализует алгоритм Прима.
 * Он поддерживает следующие операции: считывание графа,
 * readData, подготовку к выполнению алгоритма, start
 * и выполнение шага алгоритма, executeStepAlgorithm.
 */
public class Algorithm {
    private final static int MAX_V = 8;
    private final static int MAX_INT = 1000;
    private Graph G;
    private int startVertex;    // начальная вершина
    private int numVertices;    // количество вершин
    private int[] parent;
    private boolean[] intree;   // вершина в дереве?
    private int[] distance;     // стоимость добавления к дереву

    private Graph.Edge p;       // временный указатель
    private int v;              // текущая вершина для обработки


    /**
     *
     */
    public Algorithm() {
        G = new Graph();
        startVertex = 0;

        intree = new boolean[MAX_V + 1];
        distance = new int[MAX_V + 1];
        parent = new int[MAX_V + 1];
    }

    /**
     * Считывание данных из файла
     */
    public void readData() {

        // считывание графа

    }

    /**
     * Подготовка к выполнению алгоритма, которая
     * заключается в инициализации необходимых полей
     * начальными значениями.
     *
     * @param s начальная вершина
     */
    public void start(int s) {
        startVertex = s;
        for (int i = 1; i <= numVertices; ++i) {
            intree[i] = false;
            distance[i] = MAX_INT;
            parent[i] = -1;
        }

        distance[startVertex] = 0;
        v = startVertex;
    }

    /**
     * Выполняет шаг алгоритма
     *
     * @return false, если еще не все вершины добавлены в граф;
     * в противном случае - true
     */
    public boolean executeStepAlgorithm() {
        if (!intree[v]) {
            int w;                  // кондидат на следующую вершину
            int weight;             // вес ребра
            int dist;               // наилучшее текущее расстояние от начала

            intree[v] = true;
            p = G.edges.get(v);
            while (p != null) {
                w = p.getY();
                weight = p.getWeight();
                if (distance[w] > weight && !intree[w]) {
                    distance[w] = weight;
                    parent[w] = v;
                }
                p = p.getNext();
            }
            v = 1;
            dist = MAX_INT;
            for (int i = 1; i <= numVertices; ++i) {
                if (!intree[i] && dist > distance[i]) {
                    dist = distance[i];
                    v = i;
                }
            }
            return false;
        } else {
            return true;
        }

    }
}
