package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler {                                       //Обработчик событий (хз куда его засунуть, я решил отдельный класс создать)
    public static class NewGameButton implements ActionListener { //класс для кнопки "Новая игра"
        public void actionPerformed(ActionEvent e) {              //внутри этого метда код, по нажатию кнопки "Новая игра"
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame ex = new Snake();
                    ex.setVisible(true);
                }

            });
        }

    }

    public static class exitButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
}