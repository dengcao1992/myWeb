package dcs.com.web.util;


import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Md5Utils {
    public static String md5Hex(File file){
        byte[] encodes = null;
        StringBuilder result = new StringBuilder();
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
           encodes = DigestUtils.md5Digest(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (byte encode : encodes){
            String hex = Integer.toHexString(0xff & encode);
            if (hex.length() == 1){
                result.append(0);
            }
            result.append(hex);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        File file = new File("./src/main/resources/static/upload/18/06/21/18062121540326.jpg");
        System.out.println(md5Hex(file));
        File file2 = new File("./src/main/resources/static/upload/18/06/21/180621215424114.jpg");
        System.out.println(md5Hex(file2));
    }

}
