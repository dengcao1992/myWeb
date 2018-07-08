package dcs.com.web.service;


import dcs.com.web.pojo.PhotoPO;
import dcs.com.web.pojo.UserPO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


public interface PhotoService {
    Page<PhotoPO> getPhotos(int page, int onePageLimit);
    PhotoPO getPhoto(long id);
    void addPhoto(PhotoPO photo);
    void addPhoto(String title,String name,String userName,String url,String categories,int width,int height);
    boolean upload(MultipartFile file, String title, String categories, UserPO user);
//    void addPhotoHitNum(long pid);
//    void addPhotoCollectNum(long pid);
//    void addPhotoDownloadNum(long pid);
//    void addPhotoLikeNum(long pid);
    void addOnePhotoNum(long pid, String methodName);
    void addPhotosByFile(File file);
}
