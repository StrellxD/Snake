package snake;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 600;
    private final int B_HEIGHT = 600;
    private final int DOT_SIZE = 20; // Размер тела змейки
    private final int ALL_DOTS = B_WIDTH*B_HEIGHT/(DOT_SIZE*DOT_SIZE); //900 Максимальное кол-во точке змейки

    private final int RAND_POS = 29;
    private final int DELAY = 100;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public Board() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {     //Иконки не трогать!!!!(пока не нарисуешь лучше)

        ImageIcon iid = new ImageIcon("tail.png");
        ball = iid.getImage();
        ImageIcon iia = new ImageIcon("apple.png");

        apple = iia.getImage();
        ImageIcon iih = new ImageIcon("head.png");
        head = iih.getImage();
    }

    private void initGame() { //Инициализация игры

        dots = 3;//Начальное кол-во точек 3

        for (int z = 0; z < dots; z++) {        //Начальные позиции 100,100 80,100 60,100
            x[z] = 100 - z * 20;
            y[z] = 100;
        }

        locateApple();//Разместить биткоин

        timer = new Timer(DELAY, this);// Это на подготовку задержка на подготовку
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();


        } else {

            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {

        String msg = "Game Over. Новую?";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);


    }

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }

        if(!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {
        List<Integer> xs=IntStream.range(0,dots).map(i->x[i]).boxed().collect(Collectors.toList()); //Координаты х каждого блокка змейки
        List<Integer> ys=IntStream.range(0,dots).map(i->y[i]).boxed().collect(Collectors.toList());

        List<ArrayList<Integer>> grid=new ArrayList<>();//Массив 2x900 для хранения всех возможных X и Y змейки
        for (int i:IntStream.range(0,30).toArray())
        {
            for (int j:IntStream.range(0,30).toArray()){
                ArrayList<Integer> a= new ArrayList<>();
                a.add(i*DOT_SIZE);
                a.add(j*DOT_SIZE);
                grid.add(a);
            }
        }
        List<ArrayList<Integer>> filteredGrid=grid.stream().filter(i-> //фильтрум точки убирая все из xs и ys
                !(xs.contains(i.get(0))&&ys.contains(i.get(1))) ).collect(Collectors.toList());
        ArrayList<Integer> randomDot=filteredGrid.get((int)(Math.random() * filteredGrid.size())); //выбираем рандомное новое положение
        apple_x=randomDot.get(0);
        apple_y=randomDot.get(1);
        //List<Integer>  randX=IntStream.range(0,B_WIDTH/DOT_SIZE).filter(i->!xs.contains(i)).boxed().collect(Collectors.toList());
        //List<Integer> randY=IntStream.range(0,B_HEIGHT/DOT_SIZE).filter(i->!ys.contains(i)).boxed().collect(Collectors.toList());
        //apple_x = (randX.get((int)(Math.random()*randX.size())) * DOT_SIZE);
        //apple_y = (randY.get((int)(Math.random()*randX.size())) * DOT_SIZE);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}
