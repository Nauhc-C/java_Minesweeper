package Game;

import java.awt.*;

public class GameUtil {
    static int Map_W=11;
    static int Map_H=11;
    static int offset=45;
    static int Square_length=50;

    static int RAY_MAX = 5;


    //鼠标相关参数
    static int MOUSE_X;
    static int MOUSE_Y;
    static boolean LEFT = false;
    static boolean RIGHT = false;

    //底层元素   -1 雷   0空    1-8代表数字
    static int[][] DATA_BOTTOM = new int[Map_H+2][Map_H+2];
    static int[][] DATA_TOP = new int[Map_H+2][Map_H+2];  //顶层元素 -1 无覆盖 0 覆盖 1插旗 2差错旗


    //载入图片
    static Image bomb = Toolkit.getDefaultToolkit().getImage("image/bomb.png");
    static  Image[] images = new Image[9];
    static {
        for(int i = 1;i<=8;i++){
            images[i] = Toolkit.getDefaultToolkit().getImage("image/"+i+".png");
        }
    }
    static Image top = Toolkit.getDefaultToolkit().getImage("image/top.png");
    static Image flag = Toolkit.getDefaultToolkit().getImage("image/flag.png");
    static Image worseFlag = Toolkit.getDefaultToolkit().getImage("image/flag.png");


}
