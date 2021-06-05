package Utils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class StringUtils {
    public static String generateRandomString() {
        byte[] array = new byte[12];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}