package edu.hust.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @Author: QX
 * @Date: 2020/3/28 16:28
 * @Version 1.0
 */
public class SnakePanel extends JPanel implements KeyListener ,ActionListener {


    //定义蛇的数据结构
    int length;
    int[] snakex = new int[600];
    int[] snakey = new int[600];
    String fx;
    boolean isStart;
    Timer timer = new Timer(100,this);

    int foodx;
    int foody;
    Random random = new Random();
    boolean isFail;
    int score;


    public SnakePanel() {
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
    }
    public  void init() {
        score = 0;
        isStart = false;
        isFail = false;
        fx = "right";
        length = 3;
        snakex[0] = 100;snakey[0] = 100;
        snakex[1] = 75;snakey[1] = 100;
        snakex[2] = 50;snakey[2] = 100;
        timer.start();
        foodx = 25 + 25 * random.nextInt(34);
        foody = 75 + 25 * random.nextInt(24);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.setBackground(Color.black);
        Data.head.paintIcon(this, g, 25, 11);
        g.fillRect(25, 75, 850, 600);
        Data.food.paintIcon(this, g, foodx,foody );

        g.setColor(Color.white);
        g.setFont(new Font("weiryh", Font.BOLD, 20));
        g.drawString("分数" + score, 750,35 );
        g.drawString("长度" + length, 750,50 );

        if (fx.equalsIgnoreCase("right")) {
            Data.right.paintIcon(this, g, snakex[0],snakey[0] );
        } else if (fx.equalsIgnoreCase("left")) {
            Data.left.paintIcon(this, g, snakex[0],snakey[0] );
        }else if (fx.equalsIgnoreCase("up")) {
            Data.up.paintIcon(this, g, snakex[0],snakey[0] );
        }else if (fx.equalsIgnoreCase("down")) {
            Data.down.paintIcon(this, g, snakex[0],snakey[0] );
        }
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakex[i],snakey[i] );
        }



        if (isStart == false) {
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            g.drawString("按下空格开始游戏", 400, 400);
        }
        if (isFail == true) {
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", Font.BOLD, 35));
            g.drawString("失败,按空格重新来过", 300, 300);
        }


//        Data.body.paintIcon(this, g, snakex[1],snakey[1] );
//        Data.body.paintIcon(this, g, snakex[2],snakey[2] );

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
//            isStart = !isStart;
//            repaint();
            if (isFail == true) {
                isFail = false;
                init();
            } else {
                isStart = !isStart;
            }
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !fx.equalsIgnoreCase("left")) {
            fx = "right";
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT&& !fx.equalsIgnoreCase("right")) {
            fx = "left";
        }else if (e.getKeyCode() == KeyEvent.VK_UP&& !fx.equalsIgnoreCase("down")) {
            fx = "up";
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN&& !fx.equalsIgnoreCase("up")) {
            fx = "down";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && isFail == false) {
            if (snakex[0] == foodx && snakey[0] == foody) {
                length++;
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);
                score += 10;
            }
            for (int i = length-1; i > 0; i--) {
                snakex[i] = snakex[i - 1];
                snakey[i] = snakey[i - 1];
            }
            if (fx.equalsIgnoreCase("right")) {
                snakex[0] += 25;
                if (snakex[0] > 850) {
                    snakex[0] = 25;
                }
            } else if (fx.equalsIgnoreCase("left")) {
                snakex[0] -= 25;
                if (snakex[0] < 25) {
                    snakex[0] = 850;
                }
            }else if (fx.equalsIgnoreCase("up")) {
                snakey[0] -= 25;
                if (snakey[0] < 75) {
                    snakey[0] = 650;
                }
            }else if (fx.equalsIgnoreCase("down")) {
                snakey[0] += 25;
                if (snakey[0] > 650) {
                    snakey[0] = 75;
                }
            }

            for (int i = 1; i < length; i++) {
                if (snakex[0] == snakex[i] && snakey[0] == snakey[i]) {
                    isFail = true;
                }
            }

            repaint();
        }
    }
}
