package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    public Menu() {
        super("Snake");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 1, 5, 5));

        JButton newGameButton = new JButton("Новая игра");
        JButton aboutButton = new JButton("О игре");
        JButton exitButton = new JButton("Выход");

        jPanel.add(newGameButton);
        jPanel.add(aboutButton);
        jPanel.add(exitButton);

        setContentPane(jPanel);

//        ActionListener actionListener = new EventHandler.NewGameButton();
//        newGameButton.addActionListener(actionListener);
//        exitButton.addActionListener(actionListener);
        newGameButton.addActionListener(this::actionPerformed);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ex = new Snake();
                ex.setVisible(true);
            }
        });
    }
}
