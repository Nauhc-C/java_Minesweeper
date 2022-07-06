package Game;

//初始化地雷的类
public class BottomRay {
    static int[] rays = new int[GameUtil.RAY_MAX * 2];// 公共防止越界
    int x, y;
    boolean isPlace = true; // 是否可以放置

    void newRay() {
        for (int i = 0; i < GameUtil.RAY_MAX * 2; i += 2) {
            x = (int) (Math.random() * GameUtil.Map_W + 1);
            y = (int) (Math.random() * GameUtil.Map_H + 1);

            for (int j = 0; j < i; j += 2) { // 防止重合
                if (x == rays[j] && y == rays[j + 1]) {
                    i = i - 2;
                    isPlace = false;
                    break;
                }
            }
            if (isPlace) {
                rays[i] = x;
                rays[i + 1] = y;
            }
            isPlace = true;
        }

        for (int i = 0; i < GameUtil.RAY_MAX * 2; i += 2) {
            GameUtil.DATA_BOTTOM[rays[i]][rays[i + 1]] = -1;
            // System.out.println(rays[i] + " "+ rays[i+1]);
        }
    }
}
