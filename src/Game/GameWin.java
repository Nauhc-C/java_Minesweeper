package Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {
    int width = 2*GameUtil.offset + GameUtil.Map_W*GameUtil.Square_length;
    int height = 4*GameUtil.offset + GameUtil.Map_H*GameUtil.Square_length;
    MapBotton mapBotton = new MapBotton();
    MapTop mapTop = new MapTop();

    Image offScreenImage = null;
    public void launch(){
        this.setVisible(true);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setTitle("川川的扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //获取鼠标点击
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton()==1){  //左键

                    GameUtil.MOUSE_X = e.getX();
                    GameUtil.MOUSE_Y = e.getY();
                    GameUtil.LEFT =true;
                    GameUtil.RIGHT = false;
                    System.out.println("1"+GameUtil.LEFT);
                }
                if(e.getButton()==3){  //左键
                    System.out.println("3");
                    GameUtil.MOUSE_X = e.getX();
                    GameUtil.MOUSE_Y = e.getY();
                    GameUtil.LEFT =false;
                    GameUtil.RIGHT = true;
                }
            }
        });
        while(true){
            repaint();
        }
    }

    public void paint(Graphics g){
        offScreenImage = this.createImage(width,height);
        Graphics gImage = offScreenImage.getGraphics();
        gImage.setColor(Color.pink);
        gImage.fillRect(0,0,width,height);
        mapBotton.paintSelf(gImage);
        mapTop.paintSelf(gImage);
        g.drawImage(offScreenImage,0,0,null);
    }


}
