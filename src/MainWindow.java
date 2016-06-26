import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;

/**
 * Created by dronn on 26.06.2016.
 */
public class MainWindow extends JFrame {
    private JPanel rootPanel;
    private JButton завершитьButton;
    private JButton начатьButton;
    private JButton оПрограммеButton;

    public MainWindow() {
        super("Алгоритм Прима");
        setContentPane(rootPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*завершитьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/
        setSize(500,500);
        setVisible(true);


    }
}
