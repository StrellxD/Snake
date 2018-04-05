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
        jPanel.setBackground(Color.BLACK);

        BoxLayout ex=new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        BorderLayout e= new BorderLayout();

        ex.preferredLayoutSize(jPanel);
        jPanel.setLayout(ex);

        JButton newGameButton = new JButton("Новая игра");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 24));
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //newGameButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        JButton aboutButton = new JButton("Об игре");
        aboutButton.setBackground(Color.BLACK);
        aboutButton.setForeground(Color.WHITE);
        aboutButton.setFont(new Font("Arial", Font.PLAIN, 24));
        aboutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //aboutButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        JButton exitButton = new JButton("Выход");
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.PLAIN, 24));
        exitButton.setPreferredSize(new Dimension(200, 100));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //exitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        jPanel.add(newGameButton,BorderLayout.CENTER);
        jPanel.add(aboutButton,BorderLayout.CENTER);
        jPanel.add(exitButton,BorderLayout.CENTER);


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
