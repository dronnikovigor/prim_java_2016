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
    private final static int MAX_INT = 1000;
    private Graph G;            // исходный граф
    private int startVertex;   // начальная вершина
    private int numVertices;   // количество вершин
    private int[] parent;
    private boolean[] intree;   // вершина в дереве?
    private int[] distance;     // стоимость добавления к дереву

    private Graph.Edge p;       // временный указатель
    private int v;             // текущая вершина для обработки

    /**
     *
     */
    public Algorithm() {
        G = new Graph();
        startVertex = 0;

        intree = new boolean[G.edges.length];
        distance = new int[G.edges.length];
        parent = new int[G.edges.length];
    }

    public int[] getParent() {
        return parent;
    }

    public Graph getGraph() {return G;}

    public Graph.Edge getEdge(int j) {return G.edges[j];}

    public int getNumVertices() {return numVertices;}

    public int getLength() {
        return G.edges.length;
    }


    /**
     * Считывание данных из файла
     */
    public void readData() {
        File f = new File("input.txt");
        try {
            Scanner scan = new Scanner(f);
            byte temp = 0;
            temp = scan.nextByte();
            if (temp <= 0) throw new IllegalArgumentException();
            else
                numVertices = temp;

            for (int i = 0; i < numVertices; ++i) {
                byte vertex_x;
                temp = scan.nextByte();
                if (temp <= 0) throw new IllegalArgumentException();
                else
                    vertex_x = temp;

                byte numEdges;
                temp = scan.nextByte();
                if (temp < 0) throw new IllegalArgumentException();
                else
                    numEdges = temp;

                for (int j = 0; j < numEdges; ++j) {

                    byte vertex_y;
                    temp = scan.nextByte();
                    if (temp <= 0) throw new IllegalArgumentException();
                    else
                        vertex_y = temp;

                    int _weight;
                    int t = scan.nextInt();
                    if (temp <= 0) throw new IllegalArgumentException();
                    else
                        _weight = t;

                    G.addEdge(vertex_x, vertex_y, _weight);
                }
            }
            System.out.println("Данные считаны успешно");
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
            p = G.edges[v];
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

    public void clear(){
        G.clear();
        startVertex = 0;
        numVertices = 0;
        for (int i = 0; i < parent.length; ++i){
            parent[i] = -1;
            intree[i] = false;
            distance[i] = MAX_INT;
        }
        p = null;
        v = 0;

    }
}

