package AboutTopList;

import PreWork.Login;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class TopList {
    private static ArrayList<Record> table = new ArrayList<Record>();
    private static Path dataPath = Paths.get("data/toptable.txt");


    public static void add(int useTime) {
        Record single = new Record(Login.getUserName(),
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()),
                useTime, Login.getIp());
        table.add(single);
        Collections.sort(table);
        try {
            Files.writeString(dataPath,
                    single.getUserName() + "," +
                            single.getTime() + "," + single.getUseTime()
                                + "," + single.getIp() + "\n",
                                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("write error!");
        }
    }

    public static void readAll() {
        try {
            for (String singleUser : Files.readAllLines(dataPath)) {
                String[] single = singleUser.split(",");
                table.add(new Record(single[0], single[1], Integer.parseInt(single[2]), single[3]));
            }
        } catch (Exception e) {
            System.out.println("read data error!");
        }
        Collections.sort(table);
    }
    public static int getNum() {
        return table.size();
    }

    public static Record getForCode(int i) {
        return table.get(i);
    }
}
