import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

/**
 * Created by dronn on 30.06.2016.
 */
public class PrimAlg extends JFrame {
    private JPanel rootPanel;
    private JButton loadButton; //Загрузить данные
    private JButton startButton; //Начать
    private JButton nextButton; //Далее
    private JButton finishButton; //Завершить
    private JTextField startParam; //Начальная вершина
    private JTextPane outputText; //Информационная панель
    private JButton exitButton; //Закрыть окно
    private Algorithm prim; //Алгоритм
    private int startParamNum; //Начальная вершина - парамент

    private int windowSizeH = 700; //Высота окна
    private int windowSizeW = 900; //Шинира окна

    /**
     * Конструктор окна с визуализацией алгоритма
     *
     */
    public PrimAlg() {
        super("Алгоритм Прима");
        setContentPane(rootPanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prim = new Algorithm();

        ActionListener loadActionListener = new LoadButtonActionListener();
        ActionListener startActionListener = new StartButtonActionListener();
        ActionListener nextActionListener = new NextButtonActionListener();
        ActionListener finishActionListener = new FinishButtonActionListener();
        ActionListener exitActionListener = new ExitButtonActionListener();

        loadButton.addActionListener(loadActionListener);
        startButton.addActionListener(startActionListener);
        nextButton.addActionListener(nextActionListener);
        finishButton.addActionListener(finishActionListener);
        exitButton.addActionListener(exitActionListener);

        startButton.setEnabled(false);
        nextButton.setEnabled(false);
        finishButton.setEnabled(false);

        setSize(windowSizeW, windowSizeH);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Визуализация основной структуры данных
     *
     */
    public void paintGraph(){
        clear();
        Graphics2D gfx = (Graphics2D)rootPanel.getGraphics();
        gfx.setColor(Color.black);

        double pi=3.14;
        double angle = 2*pi /(prim.getNumVertices());
        int r=windowSizeH/4,
                u=windowSizeW/5+50,
                v=windowSizeH/3-20,
                rPoint = 24;

        //отрисовка ребер графа
        Font SegoiUI = new Font("Segoi UI Light", Font.PLAIN, 17);
        gfx.setFont(SegoiUI);
        gfx.setStroke(new BasicStroke(2.0f));  // толщина равна 2
        for (int j = 1; j < prim.getLength(); ++j){
            Graph.Edge e = prim.getEdge(j);
            while (e != null) {
                if (e.getX()!=0 && e.getY()!=0) {
                    double x1 = r * Math.cos(angle * e.getX() - pi / 2) + u + rPoint / 2;
                    double y1 = r * Math.sin(angle * e.getX() - pi / 2) + v + rPoint / 2;
                    double x2 = r * Math.cos(angle * e.getY() - pi / 2) + u + rPoint / 2;
                    double y2 = r * Math.sin(angle * e.getY() - pi / 2) + v + rPoint / 2;

                    gfx.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

                    x1 = (x1 + x2) / 2 + 10;
                    y1 = (y1 + y2) / 2 + 10;
                    gfx.drawString(String.valueOf(e.getWeight()), (int)x1 + rPoint/3, (int)y1 + rPoint/3);
                }
                e = e.getNext();
            }
        }

        //отрисовка вершин
        SegoiUI = new Font("Segoi UI Light", Font.BOLD, 15);
        gfx.setFont(SegoiUI);
        for (int j = 1; j<=prim.getNumVertices(); j++){
            gfx.setColor(Color.black);
            double x = r*Math.cos(angle*(j)-pi/2) + u;
            double y = r*Math.sin(angle*(j)-pi/2) + v;
            gfx.fillOval((int)x, (int)y, rPoint, rPoint); // рисуем круг

            gfx.setColor(Color.red);
            gfx.drawString(String.valueOf(j), (int)x+ rPoint/3, (int)y+ rPoint/3*2);
        }
    }

    /**
     * Визуализация каждого шага алгортима
     *
     */
    public void paintStepGraph(){
        Graphics2D gfx = (Graphics2D)rootPanel.getGraphics();
        gfx.setStroke(new BasicStroke(4.0f));  // толщина равна 4
        Font SegoiUI = new Font("Segoi UI Light", Font.BOLD, 15);
        gfx.setFont(SegoiUI);
        double pi=3.14;
        double angle = 2*pi /(prim.getNumVertices());
        int r=windowSizeH/4,
                u=windowSizeW/5+50,
                v=windowSizeH/3-20,
                rPoint = 24;

        int parent[] = prim.getParent();
        for (int j = 1; j < prim.getMAXV(); ++j) {
            gfx.setColor(Color.blue);
            if (parent[j] != -1 && parent[j] != 0) {
                //отрисовка ребер
                double x1 = r * Math.cos(angle * parent[j] - pi / 2) + u + rPoint / 2;
                double y1 = r * Math.sin(angle * parent[j] - pi / 2) + v + rPoint / 2;
                double x2 = r * Math.cos(angle * j - pi / 2) + u + rPoint / 2;
                double y2 = r * Math.sin(angle * j - pi / 2) + v + rPoint / 2;

                gfx.drawLine((int) x1, (int)y1, (int)x2, (int)y2);

                //отрисовка двух добавленых вершин
                double x = r * Math.cos(angle * parent[j] - pi / 2) + u;
                double y = r * Math.sin(angle * parent[j] - pi / 2) + v;
                gfx.fillOval((int)x, (int)y, rPoint, rPoint); // рисуем круг

                gfx.setColor(Color.yellow);
                gfx.drawString(String.valueOf(parent[j]), (int)x+ rPoint/3, (int)y+ rPoint/3*2);

                gfx.setColor(Color.blue);
                x = r * Math.cos(angle * j - pi / 2) + u;
                y = r * Math.sin(angle * j - pi / 2) + v;
                gfx.fillOval((int)x, (int)y, rPoint, rPoint); // рисуем круг

                gfx.setColor(Color.yellow);
                gfx.drawString(String.valueOf(j), (int)x+ rPoint/3, (int)y+ rPoint/3*2);
            }
        }
    }

    /**
     * Очистка области для вывода
     *
     */
    public void clear(){
        Graphics2D gfx = (Graphics2D)rootPanel.getGraphics();
        Color color_gray = new Color(238,238,238);
        gfx.setColor(color_gray);
        gfx.fillRect(0,0,windowSizeW/4*2,windowSizeH/5*3);
    }


    public class LoadButtonActionListener implements ActionListener {
        /**
         * Обработчик действия кнопки loadButton
         *
         * @param e - loadActionListener
         */
        public void actionPerformed(ActionEvent e) {
            prim.readData();
            loadButton.setEnabled(false);
            startButton.setEnabled(true);
            finishButton.setEnabled(true);
            paintGraph();
        }
    }
    public class StartButtonActionListener implements ActionListener {
        /**
         * Обработчик действия кнопки startButton
         *
         * @param e - startActionListener
         */
        public void actionPerformed(ActionEvent e) {
            startParamNum = Integer.parseInt(startParam.getText());
            prim.start(startParamNum);
            paintGraph();
            nextButton.setEnabled(true);
            startButton.setEnabled(false);
        }
    }
    public class NextButtonActionListener implements ActionListener {
        /**
         * Обработчик действия кнопки nextButton
         *
         * @param e - nextActionListener
         */
        public void actionPerformed(ActionEvent e) {
            boolean check = prim.executeStepAlgorithm();
            paintGraph();
            paintStepGraph();
            if (check) nextButton.setEnabled(false);
            outputText.setText(prim.getTextInfo());
        }
    }
    public class FinishButtonActionListener implements ActionListener {
        /**
         * Обработчик действия кнопки finishButton
         *
         * @param e - finishActionListener
         */
        public void actionPerformed(ActionEvent e) {
            prim.clear();
            loadButton.setEnabled(true);
            startButton.setEnabled(false);
            nextButton.setEnabled(false);
            finishButton.setEnabled(false);
            clear();
            outputText.setText("");
        }
    }
    public class ExitButtonActionListener implements ActionListener {
        /**
         * Обработчик действия кнопки exitButton
         *
         * @param e - exitActionListener
         */
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
