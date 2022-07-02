package Game;

import javax.swing.*;
import java.awt.*;

public class MapBotton {

    BottomRay bottomRay = new BottomRay();
    BottomNum bottomNum = new BottomNum();


    void paintSelf(Graphics g){
        g.setColor(Color.red);

        for(int i = 0;i<=GameUtil.Map_W;i++){  //竖线
            g.drawLine(GameUtil.offset+i*GameUtil.Square_length,3*GameUtil.offset,GameUtil.offset+i*GameUtil.Square_length,3*GameUtil.offset+GameUtil.Map_H*GameUtil.Square_length);
        }
        for(int i = 0;i<=GameUtil.Map_H;i++){  //横线
            g.drawLine(GameUtil.offset,3*GameUtil.offset+i*GameUtil.Square_length,GameUtil.offset+GameUtil.Map_W*GameUtil.Square_length,3*GameUtil.offset+i*GameUtil.Square_length);
        }
        for(int i = 1;i<=GameUtil.Map_W;i++){
            for(int j = 1;j<=GameUtil.Map_H;j++){
                if(GameUtil.DATA_BOTTOM[i][j]==-1) {   //如果是地雷
                    System.out.println("2");
                    g.drawImage(GameUtil.bomb,
                            GameUtil.offset + (i - 1) * GameUtil.Square_length + 1,
                            GameUtil.offset * 3 + (j - 1) * GameUtil.Square_length + 1,
                            GameUtil.Square_length - 2,
                            GameUtil.Square_length - 2,
                            null);
                }
                if(GameUtil.DATA_BOTTOM[i][j]>=0) {   //如果是数字
                    System.out.println("2");
                    g.drawImage(GameUtil.images[GameUtil.DATA_BOTTOM[i][j]],
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
