package com.eugen9ban;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel{
    Poker poker = new Poker();
    int my_scores = 0;
    int computer_scores = 0;
    List<Card> myCardsList = new ArrayList<Card>();
    List<Card> computerCardsList = new ArrayList<Card>();
    boolean myLose = false;
    boolean computerLose = false;
    static int n =0;//�ж�ׯ��, 0Ϊ������ׯ�ң�1Ϊ�����ׯ��
    public boolean computerContinue = true;
    boolean BlackJack = false;

    public GamePanel(){
        poker.washCard();
        poker.show();
        this.setVisible(true);
        repaint();
    }

    //�ж�˭��ׯ��
    public void whoIsCroupier(){
        if(myLose==true){
            n=0;
        }else{
            n=1;
        }
    }

    //�ж������Ƿ��Ǻڽܿ�
    public void computerIsBlackjack(List<Card> list){
        int blackjack = 0;
        if(list.size()<3) {
            for (Card card : list) {
                if (card.getM_value() == "A") {
                    blackjack++;
                    if (card.getM_value() == "J" || card.getM_value() == "Q" || card.getM_value() == "K" || card.getM_value()=="10") {
                        blackjack++;
                    }
                }
            }
        }
        if(blackjack==2){
            BlackJack = true;
        }else{
            BlackJack = false;
        }

        if (BlackJack == false)
            JOptionPane.showMessageDialog(null, "Computer WIN��Player LOSE��\nComputer's Scores��"+computer_scores+"\nPlayer's Scores��"+my_scores, "LOSE", JOptionPane.OK_OPTION);
        else
            JOptionPane.showMessageDialog(null, "BlackJack!!Computer WIN��Player LOSE��\nComputer's Scores��"+computer_scores+"\nPlayer's Scores��"+my_scores, "LOSE", JOptionPane.OK_OPTION);
    }

    public void playerIsBlackjack(List<Card> list){
        int blackjack = 0;
        if(list.size()<3) {
            for (Card card : list) {
                if (card.getM_value() == "A") {
                    blackjack++;
                    if (card.getM_value() == "J" || card.getM_value() == "Q" || card.getM_value() == "K" || card.getM_value()=="10") {
                        blackjack++;
                    }
                }
            }
        }
        if(blackjack==2){
            BlackJack = true;
        }else{
            BlackJack = false;
        }
        if (BlackJack == false)
            JOptionPane.showMessageDialog(null, "Player WIN��Computer LOSE��\nComputer's Scores��"+computer_scores+"\nPlayer's Scores��"+my_scores, "WIN", JOptionPane.OK_OPTION);
        else
            JOptionPane.showMessageDialog(null, "BlackJack!!Player WIN��Computer LOSE��\nComputer's Scores��"+computer_scores+"\nPlayer's Scores��"+my_scores, "WIN", JOptionPane.OK_OPTION);
    }

    //�������ƣ�������A�ƣ���A�Ƶ���ֵ���1
    public void changeAce(List<Card> list){
        for (int i=0; i<list.size(); i++) {
            Card card = (Card) list.get(i);
            if(card.getM_value()=="A"){
                list.get(i).setM_count(1);
            }
        }
    }

    //����A��
    public void resetAce(){
        for (Card card:poker.Poker) {
            if(card.getM_value()=="A"){
                card.setM_count(11);
            }
        }
    }


    //����˭��ׯ�Ҿ������Ե��ƵĴ�ӡ��ʽ
    public void paint(Graphics g){
        g.clearRect(0,0,this.getWidth(),this.getHeight());
        g.drawString("Computer's Cards",300,100);
        if (n == 0){
            if(computerCardsList.size()!=0){
            Card card1 = (Card) computerCardsList.get(0);
            card1.setPos(50,100);
            card1.drawCard(g,this);
            Card card2 = (Card) computerCardsList.get(1);
            card2.setPos(100,100);
            card2.drawCardBack(g,this);
            }
            for (int i=2; i<computerCardsList.size(); i++){
                Card card = (Card) computerCardsList.get(i);
                card.setPos((i+1)*50,100);
                card.print();
                card.drawCard(g,this);

            }
            if (myLose == true || computerLose == true){
                for (Card c:computerCardsList) {
                    c.drawCard(g,this);
                }
            }
        }else{
            for (int i=0; i<computerCardsList.size(); i++){
                Card card2 = (Card) computerCardsList.get(i);
                card2.setPos((i+1)*50,100);
                card2.print();
                card2.drawCard(g,this);
            }
        }

        g.drawString("Player's Cards",300,300);
        for (int i=0; i<myCardsList.size(); i++) {
            Card card3 = (Card) myCardsList.get(i);
            card3.setPos((i+1)*50,300);
            card3.print();
            card3.drawCard(g,this);
        }
        System.out.println();
    }

    //��ҷ���
    public void Player(){
        myCardsList.add(poker.getOneCard(poker.Top));
        poker.Top--;
        countMyScores();
        repaint();
        if (my_scores>21){
            changeAce(myCardsList);
            countMyScores();
            if(my_scores>21) {
                myLose = true;
                computerIsBlackjack(computerCardsList);
                whoIsCroupier();
                repaint();
            }
        }
    }

    //���������ܷ�
    public void countMyScores(){
        my_scores=0;
        for (Card card:myCardsList) {
            my_scores += card.getM_count();
        }
    }

    //���Է���
    public void Computer(){
        computerCardsList.add(poker.getOneCard(poker.Top));
        poker.Top--;
        countComputerScores();
        repaint();

        if (computer_scores>21){
            changeAce(computerCardsList);
            countComputerScores();
            if(computer_scores>21) {
                computerLose = true;
                computerContinue = false;
                playerIsBlackjack(myCardsList);
                whoIsCroupier();
                repaint();
            }
        }else{
            computerContinue();
        }
    }

    public void computerContinue(){
        if(n == 0){//���������ׯ���������Ƶ���С����ҵ�������һֱ����ֱ�����е����������
            if(computer_scores<my_scores){
                computerContinue = true;
                System.out.println("������ׯ��������С�����");
            }else{
                computerContinue = false;
            }
        }else{
            if(computer_scores>=16){//��������ׯ�ң������ֱ����������16
                computerContinue = false;
                JOptionPane.showMessageDialog(null, "Computer Stands��", "Tips", JOptionPane.OK_OPTION);
            }
        }
    }

    //����������Ƶ���
    public void countComputerScores(){
        computer_scores=0;
        for (Card card:computerCardsList) {
            computer_scores += card.getM_count();
        }
    }

    //��˫����û�г���21�㣬���ж�˫�����Ƶ���˭��ʤ
    public void countAllScores(){
        {
            this.countMyScores();
            this.countComputerScores();
            if(my_scores > computer_scores) {
                computerLose = true;
                whoIsCroupier();
                playerIsBlackjack(myCardsList);
            }

            else if(my_scores < computer_scores) {
                myLose = true;
                whoIsCroupier();
                computerIsBlackjack(computerCardsList);
            }
            else {
                myLose = true;
                JOptionPane.showMessageDialog(null, "DRAW��\nComputer's Scores��"+computer_scores+"\nPlayer's Scores��"+my_scores, "DRAW", JOptionPane.OK_OPTION);
            }
            repaint();
        }
    }

    //������Ϸ
    public void continueGame(){
        my_scores = 0;
        computer_scores = 0;
        poker.Top = 51;
        resetAce();
        poker.washCard();
        myLose = false;
        computerLose = false;
        computerContinue = true;
        myCardsList.clear();
        computerCardsList.clear();
        for(int i = 0; i < 2; i++)
        {
            Player();
            Computer();
            repaint();
        }
        if(n==1) {
            while (computerContinue) {
                Computer();
            }
        }
    }

    //���¿�ʼ��Ϸ
    public void reGame(){
        n = 0;
        continueGame();
    }
}
