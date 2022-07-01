package Game;

import javax.swing.*;
import java.awt.*;

public class MapBotton {
    void paintSelf(Graphics g){
        g.setColor(Color.red);

        for(int i = 0;i<=GameUtil.Map_W;i++){  //竖线
            g.drawLine(GameUtil.offset+i*GameUtil.Square_length,3*GameUtil.offset,GameUtil.offset+i*GameUtil.Square_length,3*GameUtil.offset+GameUtil.Map_H*GameUtil.Square_length);
        }
        for(int i = 0;i<=GameUtil.Map_H;i++){  //横线
            g.drawLine(GameUtil.offset,3*GameUtil.offset+i*GameUtil.Square_length,GameUtil.offset+GameUtil.Map_W*GameUtil.Square_length,3*GameUtil.offset+i*GameUtil.Square_length);
        }


    }
}
