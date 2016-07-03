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

    private String textInfo;

    /**
     *
     */
    public Algorithm() {
        G = new Graph();
        startVertex = 0;

        intree = new boolean[G.edges.length];
        distance = new int[G.edges.length];
        parent = new int[G.edges.length];

        textInfo = "";
    }
    public int getMAXV(){return G.MAX_V;}
    public int[] getParent() {
        return parent;
    }

    public Graph.Edge getEdge(int j) {return G.edges[j];}

    public int getNumVertices() {return numVertices;}

    public int getLength() {
        return G.edges.length;
    }


    public String getTextInfo() {
        return textInfo;
    }

    /**
     * Считывание данных из файла
     */
    public void readData() {
        File f = new File("input.txt");
        try {
            Scanner scan = new Scanner(f);

            numVertices = scan.nextInt();
            if (numVertices <= 0) throw new IllegalArgumentException();

            for (int i = 0; i < numVertices; ++i) {
                int vertex_x = scan.nextInt();
                if (vertex_x <= 0) throw new IllegalArgumentException();

                int numEdges = scan.nextInt();
                if (numEdges < 0) throw new IllegalArgumentException();

                for (int j = 0; j < numEdges; ++j) {

                    int vertex_y = scan.nextInt();
                    if (vertex_y <= 0) throw new IllegalArgumentException();

                    int _weight = scan.nextInt();
                    if (_weight <= 0) throw new IllegalArgumentException();

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
        textInfo = "";
        if (!intree[v]) {
            int w;                  // кондидат на следующую вершину
            int weight;             // вес ребра
            int dist;               // наилучшее текущее расстояние от начала

            textInfo += "Вершина " + v + " добавлена в МОД\n";

            intree[v] = true;
            p = G.edges[v];
            while (p != null) {
                w = p.getY();
                if (w != 0) {
                    textInfo += "Рассмотрим вершину " + w + "\n";
                    weight = p.getWeight();
                    if (distance[w] > weight && !intree[w]) {
                        distance[w] = weight;
                        parent[w] = v;
                        textInfo += "Вес ребра до данной вершины меньше тем тот, который уже записан и эта вершина еще не добавлена в дерево. Заменяем вес на новый.\n";
                    } else {
                        if (distance[w] > weight)
                            textInfo += "Вес ребра до данной вершины больше или равен тому, который уже записан.\n";
                        else textInfo += "Данная вершина уже включена в МОД.\n";
                    }
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
            textInfo += "Далее рассмотрим вершину " +v;
            return false;
        } else {
            textInfo += "Все вершины добавлены в МОД\n";
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

        textInfo = "";
    }
}

