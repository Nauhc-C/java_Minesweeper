import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

public class UsersTable {
    private static Path dataPath = Paths.get("data/userstable.txt");
    private static HashMap<String, String> map = new HashMap();

    static void readAll() throws IOException {
        for (String singleUser : Files.readAllLines(dataPath)) {
            String[] nameAndCode = singleUser.split(",");
            map.put(nameAndCode[0], nameAndCode[1]);
        }
    }

    static void add(String userName, String code) {
        String codeMd5 = MD5Test.getMd5(code);
        map.put(userName, codeMd5);
        try {
            Files.writeString(dataPath, userName + "," + codeMd5, StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println("write error!");
        }
    }

    static boolean find(String userName) {
        return (map.get(userName) != null);
    }

    public static void main(String[] args) {
        add("苏凌川", "slc带我飞");
    }
}
