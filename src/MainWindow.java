import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dronn on 26.06.2016.
 */
public class MainWindow extends JFrame {
    private JPanel rootPanel;
    private JButton startButton; //Начать программу
    private JButton aboutButton; //О программе
    private JButton exitButton; //Вывод

    /**
     * Конструктор начального окна
     *
     */
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
        /**
         * Обработчик действия кнопки startButton
         *
         * @param e - startActionListener
         */
        public void actionPerformed(ActionEvent e) {
            PrimAlg alg_window = new PrimAlg();
        }
    }

       public class AboutButtonActionListener implements ActionListener {
           /**
            * Обработчик действия кнопки aboutButton
            *
            * @param e - aboutActionListener
            */
        public void actionPerformed(ActionEvent e) {
            AboutWindow about_window = new AboutWindow();
        }
    }

    public class ExitButtonActionListener implements ActionListener {
        /**
         * Обработчик действия кнопки exitButton
         *
         * @param e - exitActionListener
         */
        public void actionPerformed(ActionEvent e) {
            System.exit(1);
        }
    }
}
