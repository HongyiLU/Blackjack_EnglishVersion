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

    //总和大于21时给A牌设置点数
    public void setM_count(int m_count) {
        this.m_count = m_count;
    }

    //设置牌位置
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    //在游戏面板上画出对象正面
    public void drawCard(Graphics g, JPanel i){
        g.drawImage(m_cardImage, x, y, cardWidth, cardHeigth,(ImageObserver) i);
    }

    //在游戏面板上画出对象反面
    public void drawCardBack(Graphics g, JPanel i){
        g.drawImage(m_cardBack, x-4, y-4, cardWidth+5,cardHeigth+7,(ImageObserver) i);
    }

    //获取当前对象花色
    public String getM_color() {
        return m_color;
    }

    //获取当前对象数字
    public String getM_value() {
        return m_value;
    }

    //获取当前对象的数值
    public int getM_count() {
        return m_count;
    }

    public void print(){
        System.out.println("牌面："+m_color+m_value+" 数值："+m_count);
    }


}
