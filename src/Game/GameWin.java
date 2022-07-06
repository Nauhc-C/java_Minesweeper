package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {

    int wigth = 2 * GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH;
    int height = 4 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH;

    Image offScreenImage = null;

    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();

    void launch(){
        GameUtil.START_TIME=System.currentTimeMillis();
        this.setVisible(true);
        this.setSize(wigth,height);
        this.setLocationRelativeTo(null);
        this.setTitle("尚学堂扫雷游戏");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //鼠标事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (GameUtil.state){
                    case 0 :
                        if(e.getButton()==1){
                        GameUtil.MOUSE_X = e.getX();
                        GameUtil.MOUSE_Y = e.getY();
                        GameUtil.LEFT = true;
                        }
                        if(e.getButton()==3) {
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.RIGHT = true;
                        }
                    case 1 :
                    case 2 :
                        if(e.getButton()==1){
                            if(e.getX()>GameUtil.OFFSET + GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2)
                                    && e.getX()<GameUtil.OFFSET + GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2) + GameUtil.SQUARE_LENGTH
                                    && e.getY()>GameUtil.OFFSET
                                    && e.getY()<GameUtil.OFFSET+GameUtil.SQUARE_LENGTH){
                                mapBottom.reGame();
                                mapTop.reGame();
                                GameUtil.FLAG_NUM=0;
                                GameUtil.START_TIME=System.currentTimeMillis();
                                GameUtil.state=0;
                            }
                        }
                        break;
                    default:

                }


            }
        });


        while (true){
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        offScreenImage = this.createImage(wigth,height);
        Graphics gImage = offScreenImage.getGraphics();
        //设置背景颜色
        gImage.setColor(Color.orange);
        gImage.fillRect(0,0,wigth,height);

        mapBottom.paintSelf(gImage);
        mapTop.paintSelf(gImage);
        g.drawImage(offScreenImage,0,0,null);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}

