package com.eugen9ban;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Card {
    private String m_color;
    private String m_value;
    private Image m_cardImage;
    private Image m_cardBack = Toolkit.getDefaultToolkit().getImage("C:\\Users\\HONGYI LU\\Downloads\\Cardback.png");
    private int m_count;
    public int x;
    public int y;
    public int cardWidth = 105;
    public int cardHeigth = 150;

    public Card(String color, String value, Image cardImage){
        this.m_color = color;
        this.m_value = value;
        this.m_cardImage = cardImage;
        if(value == "J" || value == "Q" || value == "K"){
            this.m_count=10;
        }else if(value == "A"){
            this.m_count= 11;
        }else{
            this.m_count = Integer.parseInt(value);
        }
    }

    //�ܺʹ���21ʱ��A�����õ���
    public void setM_count(int m_count) {
        this.m_count = m_count;
    }

    //������λ��
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    //����Ϸ����ϻ�����������
    public void drawCard(Graphics g, JPanel i){
        g.drawImage(m_cardImage, x, y, cardWidth, cardHeigth,(ImageObserver) i);
    }

    //����Ϸ����ϻ���������
    public void drawCardBack(Graphics g, JPanel i){
        g.drawImage(m_cardBack, x-4, y-4, cardWidth+5,cardHeigth+7,(ImageObserver) i);
    }

    //��ȡ��ǰ����ɫ
    public String getM_color() {
        return m_color;
    }

    //��ȡ��ǰ��������
    public String getM_value() {
        return m_value;
    }

    //��ȡ��ǰ�������ֵ
    public int getM_count() {
        return m_count;
    }

    public void print(){
        System.out.println("���棺"+m_color+m_value+" ��ֵ��"+m_count);
    }


}
