package PreWork;

import com.formdev.flatlaf.*;
import Game.GameWin;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Login {
    private static JFrame login = new JFrame("Login");

    private static boolean startGame = false;

    private static String userName;

    private static String ip;

    public static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    public static void loginInterface(){
        InitGlobalFont(new Font("MiSans", Font.PLAIN, 12));
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        FlatLightLaf.setup();

        login.setSize(640, 360);
        login.setResizable(false);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel loginJPanel = new JPanel(null);

        JLabel mine = new JLabel(new ImageIcon("MINE_ICON.jpg"));
        mine.setBounds(246, 15, 131, 131);

        JTextField userName = new JTextField();
        JPasswordField passWord = new JPasswordField();
        userName.setBounds(212, 170, 200, 30);
        passWord.setBounds(212, 220, 200, 30);

        JLabel textFirst = new JLabel("用户名:");
        JLabel textSecond = new JLabel("密码:");
        textFirst.setBounds(170, 170, 200, 30);
        textSecond.setBounds(182, 220, 200, 30);

        JButton in = new JButton("登陆");
        in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!UsersTable.find(userName.getText())) {
                    JOptionPane.showMessageDialog(new JPanel(null), "该用户名不存在", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (UsersTable.cmp(userName.getText(), String.valueOf(passWord.getPassword()))) {
                        JOptionPane.showMessageDialog(new JPanel(null), "登录成功, 欢迎!\n"+ userName.getText(), "通知", JOptionPane.INFORMATION_MESSAGE);
                        startGame = true;
                        Login.userName = userName.getText();
                        try {
                            Login.ip = InetAddress.getLocalHost().getHostAddress();
                        } catch (UnknownHostException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.out.println(Login.ip);
                        login.dispose();
                    } else {
                        JOptionPane.showMessageDialog(new JPanel(null), "密码错误", "警告", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        in.setBounds(332, 265, 80, 35);

        JButton noPass = new JButton("注册");
        noPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginVisible(false);
                Register.registerInterface();
            }
        });
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

    public static void loginVisible(boolean i) {
        login.setVisible(i);
    }

    public static boolean getStartGame() {
        return startGame;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getIp() {
        return ip;
    }

    public static void main(String[] args) {
        UsersTable.readAll();
        loginInterface();
        new GameWin().launch();//qww你之前所有的代码我都给你扔这里面了,给我们留一个干净的主函数吧
   //     GameWin gameWin = new GameWin();
     //   gameWin.launch();
    }
}


