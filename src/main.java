import AboutTopList.ShowList;
import AboutTopList.TopList;
import Game.GameWin;
import PreWork.Login;
import PreWork.UsersTable;
import com.formdev.flatlaf.FlatLightLaf;

public class main {
    public static void main(String[] args) throws InterruptedException {
        UsersTable.readAll();
        TopList.readAll();
        Login.loginInterface();
        while (!Login.getStartGame()) {
            Thread.sleep(100);
        }
        TopList.add(10);
        ShowList.TopListInterFace();
    }
}
