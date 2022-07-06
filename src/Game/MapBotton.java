package Game;

import javax.swing.*;
import java.awt.*;

public class MapBotton {

    BottomRay bottomRay = new BottomRay();
    BottomNum bottomNum = new BottomNum();
    {
        bottomRay.newRay();
        bottomNum.newNum();// 初始化雷和数字
    }

    // 重置游戏
    void reGame() {
        for (int i = 1; i <= GameUtil.Map_W; i++) {
            for (int j = 1; j <= GameUtil.Map_H; j++) {
                GameUtil.DATA_BOTTOM[i][j] = 0;
            }
        }
        bottomRay.newRay();
        bottomNum.newNum();
    }

    void paintSelf(Graphics g) {
        g.setColor(Color.red);

        for (int i = 0; i <= GameUtil.Map_W; i++) { // 竖线
            g.drawLine(GameUtil.offset + i * GameUtil.Square_length, 3 * GameUtil.offset,
                    GameUtil.offset + i * GameUtil.Square_length,
                    3 * GameUtil.offset + GameUtil.Map_H * GameUtil.Square_length);
        }
        for (int i = 0; i <= GameUtil.Map_H; i++) { // 横线
            g.drawLine(GameUtil.offset, 3 * GameUtil.offset + i * GameUtil.Square_length,
                    GameUtil.offset + GameUtil.Map_W * GameUtil.Square_length,
                    3 * GameUtil.offset + i * GameUtil.Square_length);
        }
        for (int i = 1; i <= GameUtil.Map_W; i++) {
            for (int j = 1; j <= GameUtil.Map_H; j++) {
                if (GameUtil.DATA_BOTTOM[i][j] == -1) { // 如果是地雷
                    // System.out.println("2");
                    g.drawImage(GameUtil.bomb,
                            GameUtil.offset + (i - 1) * GameUtil.Square_length + 1,
                            GameUtil.offset * 3 + (j - 1) * GameUtil.Square_length + 1,
                            GameUtil.Square_length - 2,
                            GameUtil.Square_length - 2,
                            null);
                }
                if (GameUtil.DATA_BOTTOM[i][j] >= 0) { // 如果是数字
                    // System.out.println("2");
                    g.drawImage(GameUtil.images[GameUtil.DATA_BOTTOM[i][j]],
                            GameUtil.offset + (i - 1) * GameUtil.Square_length + 1,
                            GameUtil.offset * 3 + (j - 1) * GameUtil.Square_length + 1,
                            GameUtil.Square_length - 2,
                            GameUtil.Square_length - 2,
                            null);
                }

            }
        }
        switch (GameUtil.state) {// 显示游戏状态
            case 0:
                g.drawImage(GameUtil.gaming,
                        GameUtil.offset + GameUtil.Square_length * (GameUtil.Map_W / 2),
                        GameUtil.offset,
                        null);
                break;
            case 1:
                g.drawImage(GameUtil.win,
                        GameUtil.offset + GameUtil.Square_length * (GameUtil.Map_W / 2),
                        GameUtil.offset,
                        null);
                break;
            case 2:
                g.drawImage(GameUtil.over,
                        GameUtil.offset + GameUtil.Square_length * (GameUtil.Map_W / 2),
                        GameUtil.offset,
                        null);
                break;

            default:
        }
        // 绘制数字 剩余雷数,计时
        GameUtil.drawWord(g, "" + (GameUtil.RAY_MAX - GameUtil.FLAG_NUM),
                GameUtil.offset,
                2 * GameUtil.offset, 30, Color.black);
        GameUtil.drawWord(g, "" + (GameUtil.End_time - GameUtil.Start_time) / 1000,
                GameUtil.offset + GameUtil.Square_length * (GameUtil.Map_W - 1),
                2 * GameUtil.offset, 30, Color.black);
        switch (GameUtil.state) {
            case 0:
                GameUtil.End_time = System.currentTimeMillis();
                g.drawImage(GameUtil.gaming,
                        GameUtil.offset + GameUtil.Square_length * (GameUtil.Map_W / 2),
                        GameUtil.offset,
                        null);
                break;
            case 1:
                g.drawImage(GameUtil.win,
                        GameUtil.offset + GameUtil.Square_length * (GameUtil.Map_W / 2),
                        GameUtil.offset,
                        null);
                GameUtil.Play_time = (GameUtil.End_time - GameUtil.Start_time) / 1000;
                break;
            case 2:
                g.drawImage(GameUtil.over,
                        GameUtil.offset + GameUtil.Square_length * (GameUtil.Map_W / 2),
                        GameUtil.offset,
                        null);
                break;

            default:
        }
    }
}
