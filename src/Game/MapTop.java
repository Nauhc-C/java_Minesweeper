package Game;

import java.awt.*;

public class MapTop { // 上层图

    // 格子位置
    int temp_x;
    int temp_y;

    // 重置游戏
    void reGame() {
        for (int i = 1; i <= GameUtil.Map_W; i++) {
            for (int j = 1; j <= GameUtil.Map_H; j++) {
                GameUtil.DATA_TOP[i][j] = 0;
            }
        }
    }

    // 判断逻辑
    void Logic() {

        temp_x = 0;
        temp_y = 0;
        if (GameUtil.MOUSE_X > GameUtil.offset && GameUtil.MOUSE_Y > 3 * GameUtil.offset) {
            temp_x = (GameUtil.MOUSE_X - GameUtil.offset) / GameUtil.Square_length + 1;
            temp_y = (GameUtil.MOUSE_Y - GameUtil.offset * 3) / GameUtil.Square_length + 1;
        }

        if (temp_x >= 1 && temp_x <= GameUtil.Map_W
                && temp_y >= 1 && temp_y <= GameUtil.Map_H) {
            if (GameUtil.LEFT) {// 左键翻开
                if (GameUtil.DATA_TOP[temp_x][temp_y] == 0) {
                    GameUtil.DATA_TOP[temp_x][temp_y] = -1;
                }
                spaceOpen(temp_x, temp_y);
                GameUtil.LEFT = false;
            }
            if (GameUtil.RIGHT) {// 右键插旗或取消插旗或点击数字翻开周围覆盖区域
                // 覆盖则插旗
                if (GameUtil.DATA_TOP[temp_x][temp_y] == 0) {
                    GameUtil.DATA_TOP[temp_x][temp_y] = 1;
                    GameUtil.FLAG_NUM++;
                }
                // 插旗则取消
                else if (GameUtil.DATA_TOP[temp_x][temp_y] == 1) {
                    GameUtil.DATA_TOP[temp_x][temp_y] = 0;
                    GameUtil.FLAG_NUM--;
                }
                // 点击数字则翻开周围覆盖
                else if (GameUtil.DATA_TOP[temp_x][temp_y] == -1) {
                    numOpen(temp_x, temp_y);
                }
                GameUtil.RIGHT = false;
            }
        }
        boom();
        victory();
    }

    // 翻开数字
    void numOpen(int x, int y) {
        // 记录旗数,只有周围旗数等于右键点击的数字时才会翻开周围覆盖区域
        int count = 0;
        if (GameUtil.DATA_BOTTOM[x][y] > 0) {// 统计周围旗子数量
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (GameUtil.DATA_TOP[i][j] == 1) {
                        count++;
                    }
                }
            }
            if (count == GameUtil.DATA_BOTTOM[x][y]) {// 旗子数等于数字时翻开
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (GameUtil.DATA_TOP[i][j] == 0) {// 翻开非旗的覆盖的格子，即状态为覆盖的格子（插旗的格子虽然覆盖但是状态为1）
                            GameUtil.DATA_TOP[i][j] = -1;
                        }
                        if (i >= 1 && j >= 1 && i <= GameUtil.Map_W && j <= GameUtil.Map_H) {// 递归翻开空格
                            spaceOpen(i, j);
                        }

                    }
                }
            }
        }

    }

    // 如果格子四周没有雷则递归打开所有空格直至打开区域外部一圈全部为数字
    void spaceOpen(int x, int y) {
        if (GameUtil.DATA_BOTTOM[x][y] == 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    // 周围格子是覆盖的情况下参与递归
                    if (GameUtil.DATA_TOP[i][j] != -1) {
                        if (GameUtil.DATA_TOP[i][j] == 1) {
                            GameUtil.FLAG_NUM--;// 此时的情况为这个旗判断错误且被打开（因为此时中间为空格，这个旗所在位置是空格周围
                                                // 所以这个位置一定不是雷，这个位置被打开后其状态由插旗变为无覆盖，所以旗子数减一
                        }
                        GameUtil.DATA_TOP[i][j] = -1;
                        // 防止递归中数组越界，即打开的位置必须在雷区当中
                        if (i >= 1 && j >= 1 && i <= GameUtil.Map_W && j <= GameUtil.Map_H) {
                            spaceOpen(i, j);
                        }
                    }

                }
            }
        }

    }

    // 失败判定,当旗子总数等于雷的数量时也会触发 true 表示失败 false 未失败
    boolean boom() {
        if (GameUtil.FLAG_NUM == GameUtil.RAY_MAX) {
            for (int i = 1; i <= GameUtil.Map_W; i++) {
                for (int j = 1; j <= GameUtil.Map_H; j++) {
                    if (GameUtil.DATA_TOP[i][j] == 0) {
                        GameUtil.DATA_TOP[i][j] = -1;
                    }
                }
            }
        }
        for (int i = 1; i <= GameUtil.Map_W; i++) {
            for (int j = 1; j <= GameUtil.Map_H; j++) {
                if (GameUtil.DATA_BOTTOM[i][j] == -1 && GameUtil.DATA_TOP[i][j] == -1) {
                    GameUtil.state = 2;
                    seeBoom();
                    return true;
                }

            }

        }
        return false;
    }

    // 失败显示
    void seeBoom() {
        for (int i = 1; i <= GameUtil.Map_W; i++) {
            for (int j = 1; j <= GameUtil.Map_H; j++) {
                // 底层是雷,顶层不是旗,显示雷
                if (GameUtil.DATA_BOTTOM[i][j] == -1 && GameUtil.DATA_TOP[i][j] != 1) {
                    GameUtil.DATA_TOP[i][j] = -1;
                }
                // 底层不是雷,顶层是旗,显示插错旗
                if (GameUtil.DATA_BOTTOM[i][j] != -1 && GameUtil.DATA_TOP[i][j] == 1) {
                    GameUtil.DATA_TOP[i][j] = 2;
                }
            }
        }

    }

    // 胜利判断 true 表示胜利 false 未胜利
    boolean victory() {
        // 统计未打开格子数
        int count = 0;
        for (int i = 1; i <= GameUtil.Map_W; i++) {
            for (int j = 1; j <= GameUtil.Map_H; j++) {
                if (GameUtil.DATA_TOP[i][j] != -1) {
                    count++;
                }
            }
        }
        if (count == GameUtil.RAY_MAX) {
            GameUtil.state = 1;
            for (int i = 1; i <= GameUtil.Map_W; i++) {
                for (int j = 1; j <= GameUtil.Map_H; j++) {
                    // 未翻开,变成旗
                    if (GameUtil.DATA_TOP[i][j] == 0) {
                        GameUtil.DATA_TOP[i][j] = 1;
                        GameUtil.FLAG_NUM++;
                    }
                }
            }
            return true;
        }
        return false;
    }

    // 绘制
    void paintSelf(Graphics g) {
        Logic();
        for (int i = 1; i <= GameUtil.Map_W; i++) {
            for (int j = 1; j <= GameUtil.Map_H; j++) {
                if (GameUtil.DATA_TOP[i][j] == 0) { // 如果有覆盖
                    g.drawImage(GameUtil.top,
                            GameUtil.offset + (i - 1) * GameUtil.Square_length + 1,
                            GameUtil.offset * 3 + (j - 1) * GameUtil.Square_length + 1,
                            GameUtil.Square_length - 2,
                            GameUtil.Square_length - 2,
                            null);
                }
                if (GameUtil.DATA_TOP[i][j] == 1) { // 如果旗子
                    g.drawImage(GameUtil.flag,
                            GameUtil.offset + (i - 1) * GameUtil.Square_length + 1,
                            GameUtil.offset * 3 + (j - 1) * GameUtil.Square_length + 1,
                            GameUtil.Square_length - 2,
                            GameUtil.Square_length - 2,
                            null);
                }
                if (GameUtil.DATA_TOP[i][j] == 2) { // 如果插错了旗子
                    g.drawImage(GameUtil.worseFlag,
                            GameUtil.offset + (i - 1) * GameUtil.Square_length + 1,
                            GameUtil.offset * 3 + (j - 1) * GameUtil.Square_length + 1,
                            GameUtil.Square_length - 2,
                            GameUtil.Square_length - 2,
                            null);
                }

            }
        }

    }
}
