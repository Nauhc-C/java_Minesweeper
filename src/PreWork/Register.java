package PreWork;

import PreWork.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Register {
    private static JFrame register = new JFrame("PreWork.Register");
    public static void registerInterface() {
        register.addWindowListener(new WindowListener() {


            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                Login.loginVisible(true);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        register.setSize(640, 360);
        register.setResizable(false);
        register.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel regJPanel = new JPanel(null);

        JTextField userName = new JTextField();
        userName.setBounds(212, 50, 200, 30);
        JPasswordField passWord = new JPasswordField();
        passWord.setBounds(212, 110, 200, 30);
        JPasswordField passWordAgain = new JPasswordField();
        passWordAgain.setBounds(212, 170, 200, 30);

        JLabel textFirst = new JLabel("用户名:");
        JLabel textSecond = new JLabel("密码:");
        JLabel textThird = new JLabel("再次输入密码:");
        textFirst.setBounds(170, 50, 200, 30);
        textSecond.setBounds(182, 110, 200, 30);
        textThird.setBounds(134, 170, 200, 30);

        JButton reg = new JButton("确认");
        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (UsersTable.find(userName.getText())) {
                    JOptionPane.showMessageDialog(new JPanel(null), "该用户已存在", "警告", JOptionPane.WARNING_MESSAGE);
                } else if (String.valueOf(passWord.getPassword()).length() < 8) {
                    JOptionPane.showMessageDialog(new JPanel(null), "密码少于八位", "警告", JOptionPane.WARNING_MESSAGE);
                } else if (!String.valueOf(passWord.getPassword()).equals(String.valueOf(passWordAgain.getPassword()))) {
                    JOptionPane.showMessageDialog(new JPanel(null), "两次密码不一样", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    UsersTable.add(userName.getText(), String.valueOf(passWord.getPassword()));
                    JOptionPane.showMessageDialog(new Panel(null), "注册成功", "通知", JOptionPane.INFORMATION_MESSAGE);
                    register.dispose();
                    Login.loginVisible(true);
                }
            }
        });
        reg.setBounds(273, 230, 80, 35);

        regJPanel.add(userName);
        regJPanel.add(passWord);
        regJPanel.add(passWordAgain);
        regJPanel.add(textFirst);
        regJPanel.add(textSecond);
        regJPanel.add(textThird);
        regJPanel.add(reg);

        register.setContentPane(regJPanel);
        register.setLocationRelativeTo(null);
        registerVisible(true);
    }

    public static void registerVisible(boolean i) {
        register.setVisible(i);
    }

    public static void main(String[] args) {
        registerInterface();
    }

}
