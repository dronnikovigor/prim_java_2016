import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dronn on 26.06.2016.
 */
public class MainWindow extends JFrame {
    private JPanel rootPanel;
    private JButton startButton;
    private JButton aboutButton;
    private JButton exitButton;

    public MainWindow() {
        super("Алгоритм Прима");
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ActionListener startActionListener = new StartButtonActionListener();
        ActionListener aboutActionListener = new AboutButtonActionListener();
        ActionListener exitActionListener = new ExitButtonActionListener();

        startButton.addActionListener(startActionListener);
        aboutButton.addActionListener(aboutActionListener);
        exitButton.addActionListener(exitActionListener);

        setSize(500,500);
        setVisible(true);
    }

    public class StartButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PrimAlg alg_window = new PrimAlg();
        }
    }
    public class AboutButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AboutWindow about_window = new AboutWindow();
        }
    }
    public class ExitButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(1);
        }
    }
}
