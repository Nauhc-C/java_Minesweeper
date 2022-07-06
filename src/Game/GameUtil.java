package Game;

import java.awt.*;

public class GameUtil {
    static int Map_W = 31;// 地图宽
    static int Map_H = 16;// 地图高
    static int offset = 45;// 偏移量
    static int Square_length = 50;// 格子边长

    static int RAY_MAX = 71;
    // 游戏状态 0 表示游戏中 1 胜利 2 失败 3难度选择
    static int state = 3;
    // 游戏难度
    static int level;
    // 插旗数量
    static int FLAG_NUM = 0;
    // 鼠标相关参数
    static int MOUSE_X;
    static int MOUSE_Y;
    static boolean LEFT = false;
    static boolean RIGHT = false;
    // 计时
    static long Start_time;
    static long End_time;
    static long Play_time;
    // 底层元素 -1 雷 0空 1-8代表数字
    static int[][] DATA_BOTTOM = new int[Map_W + 2][Map_H + 2];
    static int[][] DATA_TOP = new int[Map_W + 2][Map_H + 2]; // 顶层元素 -1 无覆盖 0 覆盖 1插旗 2插错旗

    // 载入图片
    static Image bomb = Toolkit.getDefaultToolkit().getImage("image/bomb.png");
    static Image[] images = new Image[9];
    static {
        for (int i = 1; i <= 8; i++) {
            images[i] = Toolkit.getDefaultToolkit().getImage("image/" + i + ".png");
        }
    }
    static Image top = Toolkit.getDefaultToolkit().getImage("image/top.png");
    static Image flag = Toolkit.getDefaultToolkit().getImage("image/flag.png");
    static Image worseFlag = Toolkit.getDefaultToolkit().getImage("image/worseflag.png");
    static Image gaming = Toolkit.getDefaultToolkit().getImage("image/gaming.png");
    static Image over = Toolkit.getDefaultToolkit().getImage("image/over.png");
    static Image win = Toolkit.getDefaultToolkit().getImage("image/win.png");

    // 画字符（雷的个数以及开始和结束时间）
    static void drawWord(Graphics g, String str, int x, int y, int size, Color color) {
        g.setColor(color);
        g.setFont(new Font("仿宋", Font.BOLD, size));
        g.drawString(str, x, y);

    }
}
