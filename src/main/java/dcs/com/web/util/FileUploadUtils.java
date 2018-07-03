package dcs.com.web.util;


import dcs.com.web.exception.UploadExseption;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUploadUtils  {
    public static void uploadFile(InputStream in, String fileUrl) throws IOException{
        File file = new File(fileUrl);
        FileOutputStream out = new FileOutputStream(file);
        FileCopyUtils.copy(in,out);
    }
    public static String getUrl(String name){
        String uploadName = getUploadNmae(name);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        String[] time = format.format(date).split("-");
        String paperFileUrl = getPath() + "/upload/"+time[0]+"/"+time[1]+"/"+time[2];
        if (!new File(paperFileUrl).exists()){
            new File(paperFileUrl).mkdirs();
        }
        while (new File(paperFileUrl + "/" + uploadName).exists()){
            uploadName = getUploadNmae(name);
        }
        return paperFileUrl + "/" + uploadName;
    }
    public static int[] getSize(InputStream in) throws IOException,UploadExseption{
        BufferedImage image = ImageIO.read(in);
        int[] size = new int[2];
        if (null == image){
            throw new UploadExseption("不为图片");
        }
        size[0] = image.getWidth();
        size[1] = image.getHeight();
        return size;
    }

    public static String getPath(){
        File file = new File("");
        return file.getAbsolutePath()+"/src/main/resources/static/";
    }

    public static String getUploadNmae(String name){
        String[] nameSplit = name.split("\\.");
        String type = nameSplit[nameSplit.length - 1];
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSS");
        return format.format(date) + "." + type;
    }

    public static String getWebUrl(String url){
        String path = getPath();
        System.out.println(url);
        path = path.replaceAll("\\\\","\\\\\\\\");
        String webUrl = url.replaceAll(path,"");
        return webUrl;
    }
}
