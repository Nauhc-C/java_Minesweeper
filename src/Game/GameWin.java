package Game;
import javax.swing.*;
import java.awt.*;

public class GameWin extends JFrame {
    int width = 2*GameUtil.offset + GameUtil.Map_W*GameUtil.Square_length;
    int height = 4*GameUtil.offset + GameUtil.Map_H*GameUtil.Square_length;
    MapBotton mapBotton = new MapBotton();
    public void launch(){
        this.setVisible(true);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setTitle("川川的扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint(Graphics g){
        mapBotton.paintSelf(g);
    }


}
