import javax.swing.*;

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

    public PrimAlg() {
        super("Алгоритм Прима");
        setContentPane(rootPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(800, 500);
        setVisible(true);
    }
}
