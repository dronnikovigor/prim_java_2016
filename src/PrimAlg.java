import javafx.scene.shape.Circle;

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
    private JButton loadButton;
    private JButton startButton;
    private JButton nextButton;
    private JButton finishButton;
    private JTextField startParam;
    private JTextPane outputText;
    private JButton exitButton;
    private Algorithm prim;
    private int startParamNum;

    private int windowSizeH = 700;
    private int windowSizeW = 900;

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

    public void paintGraph(){
        Graphics2D gfx = (Graphics2D)rootPanel.getGraphics();
        Color color_gray = new Color(238,238,238);
        gfx.setColor(color_gray);
        gfx.fillRect(0,0,windowSizeW/4*2,windowSizeH/5*3);
        gfx.setColor(Color.black);
       // gfx.setFont(Font);
        gfx.setStroke(new BasicStroke(2.0f));  // толщина равна 2
        double pi=3.14;
        double angle = 2*pi /(prim.getNumVertices());
        int r=windowSizeH/4,
                u=windowSizeW/5+50,
                v=windowSizeH/3-20,
                rPoint = 20;
        for (int j = 1; j < prim.getLength(); ++j){
            Graph.Edge e = prim.getEdge(j);
            while (e != null) {
                if (e.getX()!=0 && e.getY()!=0) {
                    double x1 = r * Math.cos(angle * e.getX() - pi / 2) + u + rPoint / 2;
                    double y1 = r * Math.sin(angle * e.getX() - pi / 2) + v + rPoint / 2;
                    double x2 = r * Math.cos(angle * e.getY() - pi / 2) + u + rPoint / 2;
                    double y2 = r * Math.sin(angle * e.getY() - pi / 2) + v + rPoint / 2;

                    int _x1 = (int) x1;
                    int _y1 = (int) y1;
                    int _x2 = (int) x2;
                    int _y2 = (int) y2;

                    gfx.drawLine(_x1, _y1, _x2, _y2);

                    x1 = (x1 + x2) / 2 + 10;
                    y1 = (y1 + y2) / 2 + 10;
                    _x1 = (int) x1;
                    _y1 = (int) y1;

                    gfx.drawString(String.valueOf(e.getWeight()), _x1 + rPoint, _y1);
                }
                e = e.getNext();
            }
        }
        for (int j = 1; j<=prim.getNumVertices(); j++){
            double x = r*Math.cos(angle*(j)-pi/2) + u;
            double y = r*Math.sin(angle*(j)-pi/2) + v;
            int _x = (int)x;
            int _y = (int)y;
            gfx.drawOval(_x, _y, rPoint, rPoint); // рисуем круг
            gfx.drawString(String.valueOf(j), _x+rPoint, _y);
        }
    }

    public void paintStepGraph(){
        Graphics2D gfx = (Graphics2D)rootPanel.getGraphics();
        gfx.setStroke(new BasicStroke(4.0f));  // толщина равна 4
        double pi=3.14;
        double angle = 2*pi /(prim.getNumVertices());
        int r=windowSizeH/4,
                u=windowSizeW/5+50,
                v=windowSizeH/3-20,
                rPoint = 20;

        int parent[] = prim.getParent();
        gfx.setColor(Color.blue);
        for (int j = 1; j < prim.getMAXV(); ++j) {
            if (parent[j] != -1 && parent[j] != 0) {

                double x1 = r * Math.cos(angle * parent[j] - pi / 2) + u + rPoint / 2;
                double y1 = r * Math.sin(angle * parent[j] - pi / 2) + v + rPoint / 2;
                double x2 = r * Math.cos(angle * j - pi / 2) + u + rPoint / 2;
                double y2 = r * Math.sin(angle * j - pi / 2) + v + rPoint / 2;

                int _x1 = (int) x1;
                int _y1 = (int) y1;
                int _x2 = (int) x2;
                int _y2 = (int) y2;

                gfx.drawLine(_x1, _y1, _x2, _y2);

                double x = r * Math.cos(angle * parent[j] - pi / 2) + u;
                double y = r * Math.sin(angle * parent[j] - pi / 2) + v;
                int _x = (int) x;
                int _y = (int) y;
                gfx.drawOval(_x, _y, rPoint, rPoint); // рисуем круг

                x = r * Math.cos(angle * j - pi / 2) + u;
                y = r * Math.sin(angle * j - pi / 2) + v;
                _x = (int) x;
                _y = (int) y;
                gfx.drawOval(_x, _y, rPoint, rPoint); // рисуем круг
            }
        }
    }

    public class LoadButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            prim.readData();
            loadButton.setEnabled(false);
            startButton.setEnabled(true);
            finishButton.setEnabled(true);
        }
    }
    public class StartButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            startParamNum = Integer.parseInt(startParam.getText());
            prim.start(startParamNum);
            paintGraph();
            nextButton.setEnabled(true);
            startButton.setEnabled(false);
        }
    }
    public class NextButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean check = prim.executeStepAlgorithm();
            paintGraph();
            paintStepGraph();
            if (check) nextButton.setEnabled(false);
        }
    }
    public class FinishButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            prim.clear();
            loadButton.setEnabled(true);
            startButton.setEnabled(false);
            nextButton.setEnabled(false);
            finishButton.setEnabled(false);
        }
    }
    public class ExitButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
