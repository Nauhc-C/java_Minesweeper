package Game;

import java.awt.*;

public class GameUtil {
    static int Map_W=11;
    static int Map_H=11;
    static int offset=45;
    static int Square_length=50;

    static int RAY_MAX = 5;

    //底层元素   -1 雷   0空    1-8代表数字
    static int[][] DATA_BOTTOM = new int[Map_H+2][Map_H+2];



    //载入图片
    static Image bomb = Toolkit.getDefaultToolkit().getImage("image/bomb.png");
    static  Image[] images = new Image[9];
    static {
        for(int i = 1;i<=8;i++){
            images[i] = Toolkit.getDefaultToolkit().getImage("image/"+i+".png");
        }
    }


}
