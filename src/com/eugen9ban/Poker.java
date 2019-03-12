package com.eugen9ban;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Poker {
    public List<Card> Poker = new ArrayList<Card>();
    public final int COUNT = 52;
    public static int Top = 0;
    public String[] Color = {"����","÷��","����","����"};
    public String[] Value = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    public Image cardImage[] = new Image[COUNT];

    public void getCardImage(){
        for (int i = 0; i<Color.length; i++) {
            for (int j = 0; j < Value.length; j++) {
                cardImage[i * Value.length + j] = Toolkit.getDefaultToolkit().getImage("C:\\Users\\HONGYI LU\\Downloads\\Card\\" + (i + 1) + "-" + (j + 1) + ".jpg");
            }
        }
    }

    //�����˿�����
    public Poker(){
        this.getCardImage();
        for (int i = 0; i<Color.length; i++)
            for (int j = 0; j < Value.length; j++) {
                Card newcard = new Card(Color[i], Value[j], cardImage[i * Value.length + j]);
                Poker.add(newcard);
                ++Top;
            }
    }

    //ϴ��
    public void washCard(){
        Collections.shuffle(this.Poker);
    }

    //��ȡһ���˿˶���
    public List<Card> getCard(){
        return Poker;
    }

    //��ȡһ���˿��ƶ���
    public Card getOneCard(int n) {
        return this.Poker.get(n-1);
    }

    //��ʾ���е��ƶ���color �� value��Ϣ
    public void show()
    {
        for(int i = 0; i < COUNT; i++)
        {
            Poker.get(i).print();
        }
        System.out.println();
    }
}
