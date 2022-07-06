package Game;

import AboutTopList.ShowList;
import AboutTopList.TopList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class GameWin extends JFrame {
    int width = 2 * GameUtil.offset + GameUtil.Map_W * GameUtil.Square_length;
    int height = 4 * GameUtil.offset + GameUtil.Map_H * GameUtil.Square_length;
    MapBotton mapBotton = new MapBotton();
    MapTop mapTop = new MapTop();
    GameSelect gameSelect = new GameSelect();
    Image offScreenImage = null;
    // 是否开始,f未开始,t开始（判断是否点击到难度选项
    boolean begin = false;

    public void launch() {
        GameUtil.Start_time = System.currentTimeMillis();// 记录时间
        this.setVisible(true);
        if (GameUtil.state == 3) {
            this.setSize(650, 650);
        } else {
            GameUtil.state = 0;
            this.setSize(width, height);
        }
        this.setLocationRelativeTo(null);
        this.setTitle("川川和鑫鑫和维维的扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 获取鼠标点击
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (GameUtil.state) {// 按图片则重置，去掉break：无论什么时候按均可重置
                    case 0:
                        if (e.getButton() == 1) { // 左键

                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.LEFT = true;
                            GameUtil.RIGHT = false;
      //                      System.out.println("1" + GameUtil.LEFT);
                        }
                        if (e.getButton() == 3) { // 右键
     //                       System.out.println("3");
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.LEFT = false;
                            GameUtil.RIGHT = true;
                        }

                    case 1:
                        if (GameUtil.Play_time != 0) {
                            TopList.add((int)GameUtil.Play_time);
                            GameUtil.Play_time = 0;
                        }
                    case 2:
                        if (e.getButton() == 1) {
                            if (e.getX() > GameUtil.offset + GameUtil.Square_length * (GameUtil.Map_W / 2)
                                    && e.getX() < GameUtil.offset + GameUtil.Square_length * (GameUtil.Map_W / 2)
                                            + GameUtil.Square_length
                                    && e.getY() > GameUtil.offset
                                    && e.getY() < GameUtil.offset + GameUtil.Square_length) {// 点到图片重置游戏
                                mapBotton.reGame();
                                mapTop.reGame();
                                GameUtil.FLAG_NUM = 0;
                                GameUtil.state = 0;
                                GameUtil.Play_time = 0;
                                GameUtil.Start_time = System.currentTimeMillis();
                            }
                        }
                        if (e.getButton() == 2) {// 点击滚轮可以重新选择难度
                            GameUtil.state = 3;
                            begin = true;
                        }
                        break;
                    case 3:
                        if (e.getButton() == 1) {
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            begin = gameSelect.hard();
                        }
                        break;
                    default:

                }

            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'k') {
                    ShowList.TopListInterFace();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        while (true) {
            repaint();
            begin();
            try {// 判断一次begin后sleep一段时间让窗口不频繁地闪烁（好像是
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void begin() {// 开始游戏
        if (begin) {
            begin = false;
            gameSelect.hard(GameUtil.level);
            dispose();// 将选择难度的窗口关闭
            if (GameUtil.level == 4) {
                System.out.println("请按顺序输入你想要选择的地图大小（先输入长（最大值为30）再输入宽（最大值为15））以及地雷个数（最大值为70,且不能超过地图面积）：");
                Scanner sc = null;
                sc = new Scanner(System.in);
                GameUtil.Map_W = sc.nextInt();
                GameUtil.Map_H = sc.nextInt();
                GameUtil.RAY_MAX = sc.nextInt();
                int flag = 0;
                while (flag == 0) {
                    if (GameUtil.Map_W > 0 && GameUtil.Map_W <= 30 && GameUtil.Map_H > 0 && GameUtil.Map_H <= 15
                            && GameUtil.RAY_MAX > 0 && GameUtil.RAY_MAX <= 70
                            && GameUtil.RAY_MAX < GameUtil.Map_W * GameUtil.Map_H)
                        flag = 1;
                    else {
                        System.out.println("输入不符合要求，请重新输入地图大小（先输入长（最大值为30）再输入宽（最大值为15））以及地雷个数（最大值为70）：");
                        GameUtil.Map_W = sc.nextInt();
                        GameUtil.Map_H = sc.nextInt();
                        GameUtil.RAY_MAX = sc.nextInt();
                    }
                }
            }
            GameWin gameWin = new GameWin();
            GameUtil.Start_time = System.currentTimeMillis();
            GameUtil.FLAG_NUM = 0;
            GameUtil.Play_time = 0;
            GameUtil.level = -1;
            mapBotton.reGame();
            mapTop.reGame();
            gameWin.launch();
        }
    }

    public void paint(Graphics g) {
        if (GameUtil.state == 3) {
            g.setColor(Color.white);
            g.fillRect(0, 0, 650, 650);
            gameSelect.paintSelf(g);
        } else {
            offScreenImage = this.createImage(width, height);
            Graphics gImage = offScreenImage.getGraphics();
            gImage.setColor(Color.pink);
            gImage.fillRect(0, 0, width, height);
            mapBotton.paintSelf(gImage);
            mapTop.paintSelf(gImage);
            g.drawImage(offScreenImage, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();


    }
}
