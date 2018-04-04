package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    public static boolean visible;

    public Menu() {
        super("Snake");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);                                              //Окно в центре

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 1, 5, 5));

        JButton newGameButton = new JButton("Новая игра");
        JButton aboutButton = new JButton("О игре");
        JButton exitButton = new JButton("Выход");

        jPanel.add(newGameButton);
        jPanel.add(aboutButton);
        jPanel.add(exitButton);

        setContentPane(jPanel);
        ActionListener actionListener = new EventHandler.NewGameButton();
        newGameButton.addActionListener(actionListener);
    }
}
