package edu.hust.snake;

import javax.swing.*;

/**
 * @Author: QX
 * @Date: 2020/3/28 16:22
 * @Version 1.0
 */
public class SnakeGame {
    public static void main(String[] args){
        JFrame frame = new JFrame();

        frame.setBounds(10, 10, 900, 720);
        frame.setResizable(false);

        frame.add(new SnakePanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
