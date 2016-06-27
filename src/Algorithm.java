/**
 * Created by Lyushnina Elena on 26.06.2016.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класс Algorithm реализует алгоритм Прима.
 * Он поддерживает следующие операции: считывание графа,
 * readData, подготовку к выполнению алгоритма, start
 * и выполнение шага алгоритма, executeStepAlgorithm.
 */
public class Algorithm {
    private final static byte MAX_V = 8;
    private final static int MAX_INT = 1000;
    private Graph G;
    private byte startVertex;    // начальная вершина
    private byte numVertices;    // количество вершин
    private byte[] parent;
    private boolean[] intree;   // вершина в дереве?
    private int[] distance;     // стоимость добавления к дереву

    private Graph.Edge p;       // временный указатель
    private byte v;              // текущая вершина для обработки


    /**
     *
     */
    public Algorithm() {
        G = new Graph();
        startVertex = 0;

        intree = new boolean[MAX_V + 1];
        distance = new int[MAX_V + 1];
        parent = new byte[MAX_V + 1];
    }

    /**
     * Считывание данных из файла
     */
    public void readData() {
        File f = new File("input.txt");
        try {
            Scanner scan = new Scanner(f);

            numVertices = scan.nextByte();

            for (int i = 0; i < numVertices; ++i) {
                byte vertex_x = scan.nextByte();
                byte numEdges = scan.nextByte();
                for (int j = 0; j < numEdges; ++j) {
                    byte vertex_y = scan.nextByte();
                    short _weight = scan.nextShort();
                    G.addEdge(vertex_x, vertex_y, _weight);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Подготовка к выполнению алгоритма, которая
     * заключается в инициализации необходимых полей
     * начальными значениями.
     *
     * @param s начальная вершина
     */
    public void start(byte s) {
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
            for (byte i = 1; i <= numVertices; ++i) {
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
