package Game;

import java.awt.*;

public class MapTop {  //上层图


    //鼠标
    void Logic(){
        //System.out.println("LOGIC");
        if(GameUtil.LEFT){
            System.out.println("LEFT:x="+GameUtil.MOUSE_X);
            GameUtil.LEFT=false;
        }
        if(GameUtil.RIGHT){
            System.out.println("RIGHT:y="+GameUtil.MOUSE_Y);
            GameUtil.RIGHT=false;
        }
    }

    //绘制
    void paintSelf(Graphics g){
        Logic();
        for(int i = 1;i<=GameUtil.Map_W;i++){
            for(int j = 1;j<=GameUtil.Map_H;j++){
                if(GameUtil.DATA_TOP[i][j]==0) {   //如果有覆盖
                    g.drawImage(GameUtil.top,
                            GameUtil.offset + (i - 1) * GameUtil.Square_length + 1,
                            GameUtil.offset * 3 + (j - 1) * GameUtil.Square_length + 1,
                            GameUtil.Square_length - 2,
                            GameUtil.Square_length - 2,
                            null);
                }
                if(GameUtil.DATA_TOP[i][j]==1) {   //如果旗子
                    g.drawImage(GameUtil.flag,
                            GameUtil.offset + (i - 1) * GameUtil.Square_length + 1,
                            GameUtil.offset * 3 + (j - 1) * GameUtil.Square_length + 1,
                            GameUtil.Square_length - 2,
                            GameUtil.Square_length - 2,
                            null);
                }
                if(GameUtil.DATA_TOP[i][j]==2) {   //如果插错了旗子
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
