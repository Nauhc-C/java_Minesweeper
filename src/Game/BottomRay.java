package Game;

//初始化地雷的类
public class BottomRay {
    static int[] rays = new int[GameUtil.RAY_MAX * 2];// 公共防止越界

    int x, y;
    boolean isPlace = true; // 是否可以放置

    static int[] dfsTemp = new int[GameUtil.RAY_MAX];
    // 第一步,获取当前的位置并标记
    // 第二步,检查它附近的四个位置是否是雷
    // 如果都不是,则返回1
    //// 如果有雷,则判断下一个雷的三个方向是否有雷,其中这个三个方向排除掉的是原来的方向
    ////
    boolean dfs(int x,int y,int iMax,int way,int startx,int starty){
        if((x==startx&&y==starty-1&&way!=1)||(x==startx&&y==starty+1&&way!=2)||(x==startx-1&&y==starty&&way!=3)||(x==startx&&y==starty+1&&way!=4)){
            return true;
        }
        for(int i=0;i<iMax;i+=2){
            if(x == rays[i]&&y==rays[i+1]-1&&way != 2){    //雷在上面
                if(dfs(rays[i],rays[i+1],iMax,1,startx,starty))
                    return true;
            }
            if(x == rays[i]&&y==rays[i+1]+1&&way != 1){    //雷在下
                if(dfs(rays[i],rays[i+1],iMax,2,startx,starty))
                    return true;
            }
            if(x == rays[i]+1&&y==rays[i+1]-1&&way != 4){    //雷在左
                if(dfs(rays[i],rays[i+1],iMax,3,startx,starty))
                    return true;
            }
            if(x == rays[i]-1&&y==rays[i+1]-1&&way != 3){    //雷在右
                if(dfs(rays[i],rays[i+1],iMax,4,startx,starty))
                    return true;
            }
        }
        return false;
    }
    void newRay() {
        for (int i = 0; i < GameUtil.RAY_MAX * 2; i += 2) {
            x = (int) (Math.random() * GameUtil.Map_W + 1);
            y = (int) (Math.random() * GameUtil.Map_H + 1);

            for (int j = 0; j < i; j += 2) { // 防止重合
                if (x == rays[j] && y == rays[j + 1] ) {
                    i = i - 2;
                    isPlace = false;
                    break;
                }
                if(dfs(x,y,i,0,x,y)){
                    System.out.println("dfs");
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
        //生成完所有雷才进入数组
        for (int i = 0; i < GameUtil.RAY_MAX * 2; i += 2) {
            GameUtil.DATA_BOTTOM[rays[i]][rays[i + 1]] = -1;
            // System.out.println(rays[i] + " "+ rays[i+1]);
        }

    }
}
