package PreWork;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5Test {
    public static String getMd5(String text) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(text.getBytes(StandardCharsets.UTF_8));

            StringBuilder builder = new StringBuilder();

            for (byte aByte : bytes) {
                builder.append(Integer.toHexString((0x000000FF & aByte) | 0xFFFFFF00).substring(6));
            }

            return builder.toString();
        } catch (Exception e) {
            System.out.println("MD5 error!");
            return null;
        }

    }
}
