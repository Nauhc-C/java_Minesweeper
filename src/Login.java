import com.formdev.flatlaf.*;
import Game.GameWin;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class Login {

    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    private static void loginInterface(){
        InitGlobalFont(new Font("MiSans", Font.PLAIN, 12));
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        FlatLightLaf.setup();
        JFrame login = new JFrame("Login in mine clearance");
        login.setSize(640, 360);
        login.setResizable(false);
        JPanel loginJPanel = new JPanel(null);
        JLabel mine = new JLabel(new ImageIcon("MINE_ICON.jpg"));
        mine.setBounds(246, 15, 131, 131);
        JTextField userName = new JTextField();
        userName.setBounds(212, 170, 200, 30);
        JPasswordField passWord = new JPasswordField();
        passWord.setBounds(212, 220, 200, 30);
        JLabel textFirst = new JLabel("用户名:");
        JLabel textSecond = new JLabel("密码:");
        textFirst.setBounds(170, 170, 200, 30);
        textSecond.setBounds(182, 220, 200, 30);
        JButton in = new JButton("登陆");
        JButton noPass = new JButton("注册");
        in.setBounds(332, 265, 80, 35);
        noPass.setBounds(212,265, 80, 35);
        loginJPanel.add(noPass);
        loginJPanel.add(in);
        loginJPanel.add(passWord);
        loginJPanel.add(userName);
        loginJPanel.add(mine);
        loginJPanel.add(textFirst);
        loginJPanel.add(textSecond);
        login.setContentPane(loginJPanel);
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }







    public static void main(String[] args) {
        loginInterface();   //qww你之前所有的代码我都给你扔这里面了,给我们留一个干净的主函数吧
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}


