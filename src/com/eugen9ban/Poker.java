package com.eugen9ban;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Poker {
    public List<Card> Poker = new ArrayList<Card>();
    public final int COUNT = 52;
    public static int Top = 0;
    public String[] Color = {"方块","梅花","红桃","黑桃"};
    public String[] Value = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    public Image cardImage[] = new Image[COUNT];

    public void getCardImage(){
        for (int i = 0; i<Color.length; i++) {
            for (int j = 0; j < Value.length; j++) {
                cardImage[i * Value.length + j] = Toolkit.getDefaultToolkit().getImage("C:\\Users\\HONGYI LU\\Downloads\\Card\\" + (i + 1) + "-" + (j + 1) + ".jpg");
            }
        }
    }

    //创建扑克牌组
    public Poker(){
        this.getCardImage();
        for (int i = 0; i<Color.length; i++)
            for (int j = 0; j < Value.length; j++) {
                Card newcard = new Card(Color[i], Value[j], cardImage[i * Value.length + j]);
                Poker.add(newcard);
                ++Top;
            }
    }

    //洗牌
    public void washCard(){
        Collections.shuffle(this.Poker);
    }

    //获取一副扑克对象
    public List<Card> getCard(){
        return Poker;
    }

    //获取一张扑克牌对象
    public Card getOneCard(int n) {
        return this.Poker.get(n-1);
    }

    //显示所有的牌对象color 和 value信息
    public void show()
    {
        for(int i = 0; i < COUNT; i++)
        {
            Poker.get(i).print();
        }
        System.out.println();
    }
}
