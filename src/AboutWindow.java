import javax.swing.*;

/**
 * Created by dronn on 03.07.2016.
 */
public class AboutWindow extends JFrame{
    private JPanel rootPanel;

    /**
     * Конструктор окна О программе
     */
    public AboutWindow(){
        super("О программе");
        setContentPane(rootPanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(400,400);
        setVisible(true);
    }
}