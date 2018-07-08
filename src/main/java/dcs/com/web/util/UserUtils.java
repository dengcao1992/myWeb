package dcs.com.web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class UserUtils {

    //md5加盐加密密码
    public static String passwordEncode(String password){
        Random r = new Random();
        StringBuilder stringBuilder = new StringBuilder(16);
        stringBuilder.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = stringBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                stringBuilder.append("0");
            }
        }
        String salt = stringBuilder.toString();
        password = md5Hex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    public static boolean verifyPassword(String encode,String password){
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = encode.charAt(i);
            cs1[i / 3 * 2 + 1] = encode.charAt(i + 2);
            cs2[i / 3] = encode.charAt(i + 1);
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(new String(cs1));
    }

    private static String md5Hex(String password){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] encodes = messageDigest.digest(password.getBytes());
        StringBuilder result = new StringBuilder();
        for (byte encode : encodes){
            String hex = Integer.toHexString(0xff & encode);
            if (hex.length() == 1){
                result.append(0);
            }
            result.append(hex);
        }
        return result.toString();
    }
}
