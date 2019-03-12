package com.eugen9ban;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JFrame {
    GamePanel pokerPanel = new GamePanel();
    JButton button1 = new JButton("Start");
    JButton button2 = new JButton("Hit");
    JButton button3= new JButton("Stand");
    JButton button4 = new JButton("Continue");
    JButton button5 = new JButton("Restart");
    JButton button6 = new JButton("Exit");


    public Game(){
        JPanel panel1 = new JPanel();

        panel1.setLayout(new FlowLayout());
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);
        panel1.add(button5);
        panel1.add(button6);

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        //添加游戏面板至中心
        container.add(pokerPanel,BorderLayout.CENTER);
        //添加按键面板至下方
        container.add(panel1,BorderLayout.SOUTH);

        //设置框架
        this.setTitle("BlackJack!");
        this.setVisible(true);
        this.setBounds(100,100,500,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button1.addMouseListener(new MouseAdapter()//游戏开始
        {
            public void mouseClicked(MouseEvent e) {
                if (pokerPanel.myLose != true && pokerPanel.computerLose != true) {
                    System.out.print("You have chosen Start!");
                    if (pokerPanel.n == 1) {
                        System.out.println("  Player is the dealer");
                        JOptionPane.showMessageDialog(null, "Player is the dealer", "Tips", JOptionPane.OK_OPTION);
                    } else {
                        System.out.println("  Computer is the dealer");
                        JOptionPane.showMessageDialog(null, "Computer is the dealer", "Tips", JOptionPane.OK_OPTION);
                    }
                    pokerPanel.reGame();
                }
            }
        });

        button2.addMouseListener(new MouseAdapter()//玩家要牌的阶段
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pokerPanel.myLose != true && pokerPanel.computerLose != true) {
                    System.out.println("You have chosen Hit!");
                    pokerPanel.Player();
                }
            }
        });

        button3.addMouseListener(new MouseAdapter()//玩家停牌阶段
        {
            public void mouseClicked(MouseEvent e) {
                if (pokerPanel.myLose != true && pokerPanel.computerLose != true) {
                    System.out.println("You have chosen Stand!");
                    if (pokerPanel.n == 0) {
                        pokerPanel.computerContinue();
                        while (pokerPanel.computerContinue) {
                            pokerPanel.Computer();
                        }
                    }
                    //计算总成绩
                    if (pokerPanel.myLose == false && pokerPanel.computerLose == false) {
                        pokerPanel.countAllScores();
                    }
                }
            }
        });

        button4.addMouseListener(new MouseAdapter()//继续游戏
        {
            public void mouseClicked(MouseEvent e)
            {
                System.out.print("You have chosen Continue!");
                if(pokerPanel.n==1){
                    System.out.println("  Player is the dealer");
                    JOptionPane.showMessageDialog(null, "Player is the dealer", "Tips", JOptionPane.OK_OPTION);
                }else{
                    System.out.println("  Computer is the dealer");
                    JOptionPane.showMessageDialog(null, "Computer is the dealer", "Tips", JOptionPane.OK_OPTION);
                }
                pokerPanel.continueGame();
            }
        });

        button5.addMouseListener(new MouseAdapter()//游戏重新开始
        {
            public void mouseClicked(MouseEvent e)
            {
                System.out.print("You have chosen Restart!");
                if(pokerPanel.n==1){
                    System.out.println("  Player is the dealer");
                    JOptionPane.showMessageDialog(null, "Player is the dealer", "Tips", JOptionPane.OK_OPTION);
                }else{
                    System.out.println("  Computer is the dealer");
                    JOptionPane.showMessageDialog(null, "Computer is the dealer", "Tips", JOptionPane.OK_OPTION);
                }
                pokerPanel.reGame();
            }
        });

        button6.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                System.exit(1);
            }
        });
    }

    public static void main(String[] args) {
        new Game();
    }

}
