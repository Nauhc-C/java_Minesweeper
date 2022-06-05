package panel;


import javax.swing.*;

public class game {
    public static void Optional(JPanel panel) {
        panel.setLayout(null);
        JLabel userLabel = new JLabel("地图的大小:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);
        JLabel passwordLabel = new JLabel("雷的密度:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);
        JTextField passwordText = new JTextField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);
        JButton loginButton = new JButton("开始游戏");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
    }

    public static void game(){
        JFrame frame = new JFrame("Minesweeper");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        Optional(panel);
        frame.setVisible(true);
    }
}
