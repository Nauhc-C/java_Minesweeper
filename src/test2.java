import com.formdev.flatlaf.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Hashtable;

public class test2 {

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
    public static void main(String[] args) {
        InitGlobalFont(new Font("MiSans", Font.PLAIN, 12));
        System.setProperty("awt.useSystemAAFontSettings", "on");

        System.setProperty("swing.aatext", "true");
        FlatLightLaf.setup();
    //    Font f = new Font("MiSans",Font.PLAIN,12);
      //  UIManager.put("Button.font",f);

        JFrame login = new JFrame("Login in mine clearance");
        login.setSize(640, 360);
        login.setResizable(false);
       // Box vBox = Box.createVerticalBox();
        JPanel loginJPanel = new JPanel(null);
        JLabel mine = new JLabel(new ImageIcon("MINE_ICON.jpg"));
     //   vBox.add(mine);
        mine.setBounds(246, 15, 131, 131);
        JTextField userName = new JTextField();
        userName.setBounds(212, 170, 200, 30);
        JPasswordField passWord = new JPasswordField();
        passWord.setBounds(212, 220, 200, 30);
        JLabel textFirst = new JLabel("ÓÃ»§Ãû:");
        JLabel textSecond = new JLabel("ÃÜÂë:");
        textFirst.setBounds(170, 170, 200, 30);
        textSecond.setBounds(182, 220, 200, 30);
        JButton in = new JButton("µÇÂ½");
        JButton noPass = new JButton("×¢²á");
    //    noPass.setBorderPainted(false);
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
}


