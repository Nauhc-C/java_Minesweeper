package Game;

import java.awt.*;

public class GameSelect {

    // 判断是否点击到难度
    boolean hard() {
        if (GameUtil.MOUSE_X > 100 && GameUtil.MOUSE_X < 400) {
            if (GameUtil.MOUSE_Y > 50 && GameUtil.MOUSE_Y < 150) {
                GameUtil.level = 1;
                GameUtil.state = 0;
                return true;
            }
            if (GameUtil.MOUSE_Y > 200 && GameUtil.MOUSE_Y < 300) {
                GameUtil.level = 2;
                GameUtil.state = 0;
                return true;
            }
            if (GameUtil.MOUSE_Y > 350 && GameUtil.MOUSE_Y < 450) {
                GameUtil.level = 3;
                GameUtil.state = 0;
                return true;
            }
            if (GameUtil.MOUSE_Y > 500 && GameUtil.MOUSE_Y < 600) {
                GameUtil.level = 4;
                GameUtil.state = 0;
                return true;
            }
        }
        return false;
    }

    void paintSelf(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(100, 50, 300, 100);
        GameUtil.drawWord(g, "简单", 220, 100, 30, Color.black);

        g.drawRect(100, 200, 300, 100);
        GameUtil.drawWord(g, "普通", 220, 250, 30, Color.black);

        g.drawRect(100, 350, 300, 100);
        GameUtil.drawWord(g, "困难", 220, 400, 30, Color.black);

        g.drawRect(100, 500, 300, 100);
        GameUtil.drawWord(g, "自定义", 220, 550, 30, Color.black);
    }

    void hard(int level) {// 方法重载，设置难度
        switch (level) {
            case 1:
                GameUtil.RAY_MAX = 10;
                GameUtil.Map_W = 9;
                GameUtil.Map_H = 9;
                break;
            case 2:
                GameUtil.RAY_MAX = 30;
                GameUtil.Map_W = 20;
                GameUtil.Map_H = 10;
                break;
            case 3:
                GameUtil.RAY_MAX = 70;
                GameUtil.Map_W = 30;
                GameUtil.Map_H = 15;
                break;
            case 4:

                break;
            default:
        }
    }
}

