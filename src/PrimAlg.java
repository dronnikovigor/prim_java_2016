import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private Algorithm prim;

    public PrimAlg() {
        super("Алгоритм Прима");
        setContentPane(rootPanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prim = new Algorithm();

        ActionListener loadActionListener = new LoadButtonActionListener();
        ActionListener startActionListener = new StartButtonActionListener();
        ActionListener nextActionListener = new NextButtonActionListener();
        ActionListener finishActionListener = new FinishButtonActionListener();

        loadButton.addActionListener(loadActionListener);
        startButton.addActionListener(startActionListener);
        nextButton.addActionListener(nextActionListener);
        finishButton.addActionListener(finishActionListener);

        setSize(800, 500);
        setVisible(true);
    }

    public class LoadButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            prim.readData();
        }
    }
    public class StartButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }
    public class NextButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            prim.executeStepAlgorithm();
        }
    }
    public class FinishButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            prim.clear();
        }
    }
}
