package snake;

import javax.swing.*;
import java.awt.*;

public class Menu {
    public Menu() {
        JFrame menuFrame = new JFrame("Snake");
        menuFrame.setSize(600, 600);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(2, 2, 5, 5));

        JButton newGameButton = new JButton();
        JButton exitButton = new JButton();
        JButton aboutButton = new JButton();

    }
}
