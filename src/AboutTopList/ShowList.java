package AboutTopList;

import PreWork.Login;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Record implements Comparable {
    public String getUserName() {
        return userName;
    }

    private String userName;

    public String getTime() {
        return time;
    }

    public int getUseTime() {
        return useTime;
    }

    public String getIp() {
        return ip;
    }

    private String time;
    private int useTime;
    private String ip;

    public Record(String userName, String time, int useTime, String ip) {
        this.useTime = useTime;
        this.userName = userName;
        this.ip = ip;
        this.time = time;
    }

    @Override
    public int compareTo(Object o) {
        Record s = (Record) o;
        if (this.useTime >= s.useTime) {
            return 1;
        } else {
            return -1;
        }
    }
}


public class ShowList {

    public static void TopListInterFace() {
        JFrame topList = new JFrame("TopList");
        topList.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        topList.setLocationRelativeTo(null);

        JPanel rank = new JPanel();


        String[] colunmNames = {"用户", "时间", "用时", "IP"};
        Object[][] myData = new Object[50][4];
        for (int i = 0; i < TopList.getNum() && i < 50; i++) {
            Record temp = TopList.getForCode(i);
            myData[i][0] = temp.getUserName();
            myData[i][1] = temp.getTime();
            myData[i][2] = temp.getUseTime();
            myData[i][3] = temp.getIp();
        }

        DefaultTableModel myTable = new DefaultTableModel(myData, colunmNames);
        JTable table = new JTable(myTable);

        JScrollPane scrollPane = new JScrollPane(table);

        rank.add(scrollPane);

        topList.setContentPane(rank);
        topList.pack();
        topList.setVisible(true);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        TopList.readAll();

        TopListInterFace();
    }
}
