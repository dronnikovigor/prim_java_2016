/**
 * Created by Lyushnina Elena on 25.06.2016.
 */
public interface Graph_interface {
    /**
     * Удаляет все вершины и ребра из графа.
     */
    void clear();

    /**
     * Добавляет ребро в граф
     *
     * @param _x      начало дуги
     * @param _y      конец дуги
     * @param _weight вес дуги
     */
    void addEdge(byte _x, byte _y, int _weight);
}