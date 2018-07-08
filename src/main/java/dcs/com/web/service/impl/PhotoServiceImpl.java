package dcs.com.web.service.impl;

import dcs.com.web.dao.PhotoDao;
import dcs.com.web.exception.UploadExseption;
import dcs.com.web.pojo.PhotoPO;
import dcs.com.web.pojo.UserPO;
import dcs.com.web.service.PhotoService;
import dcs.com.web.util.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class PhotoServiceImpl implements PhotoService{
    @Autowired
    private PhotoDao photoDao;

    @Override
    public Page<PhotoPO> getPhotos(int page, int onePageLimit) {
        Pageable pageable = new PageRequest(page - 1,onePageLimit,null,"pid");
        return photoDao.findAll(pageable);
    }

    @Override
    public PhotoPO getPhoto(long pid) {
        return photoDao.getOne(pid);
    }

    @Override
    public boolean upload(MultipartFile file, String title, String categories, UserPO user) {
        boolean result = true;
        String fileName = file.getOriginalFilename();
        int[] photoSize = new int[2];
        try {
            photoSize = FileUploadUtils.getSize(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UploadExseption uploadExseption) {
            uploadExseption.printStackTrace();
            return !result;
        }
        String fileUrl = FileUploadUtils.getUrl(fileName);
        try {
            FileUploadUtils.uploadFile(file.getInputStream(),fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return !result;
        }
        addPhoto(title,fileName,user.getUsername(),FileUploadUtils.getWebUrl(fileUrl),categories,photoSize[0],photoSize[1]);
        return result;
    }

    @Override
    public void addPhoto(String title, String name, String userName, String url, String categories,int width,int height) {
        PhotoPO photoPO = new PhotoPO();
        photoPO.setTitle(title);
        photoPO.setCreated(new Date());
        photoPO.setName(name);
        photoPO.setUsername(userName);
        photoPO.setUrl(url);
        photoPO.setCategories(categories);
        photoPO.setWidth(width);
        photoPO.setHeight(height);
        addPhoto(photoPO);
    }

    @Override
    public void addPhoto(PhotoPO photo) {
        photoDao.saveAndFlush(photo);
    }


///未实现
    @Override
    public void addPhotosByFile(File file) {

    }

    @Override
    public void addOnePhotoNum(long pid, String methodName) {
        final String photoHitNum = "hit";
        final String photoCollectNum = "collect";
        final String photoDownloadNum = "Download";
        final String photoLikeNum = "Like";
        if (methodName.equals(photoHitNum)){
            addPhotoHitNum(pid);
        }
        if (methodName.equals(photoCollectNum)){
            addPhotoCollectNum(pid);
        }
        if (methodName.equals(photoDownloadNum)){
            addPhotoDownloadNum(pid);
        }
        if (methodName.equals(photoLikeNum)){
            addPhotoLikeNum(pid);
        }
    }

    private void addPhotoHitNum(long pid) {
        PhotoPO photo = getPhoto(pid);
        photo.setHit(photo.getHit() + 1);
        addPhoto(photo);
    }

    private void addPhotoCollectNum(long pid) {
        PhotoPO photo = getPhoto(pid);
        photo.setCollectNum(photo.getCollectNum() + 1);
        addPhoto(photo);
    }

    private void addPhotoDownloadNum(long pid) {
        PhotoPO photo = getPhoto(pid);
        photo.setDownloadNum(photo.getDownloadNum() + 1);
        addPhoto(photo);
    }

    private void addPhotoLikeNum(long pid) {
        PhotoPO photo = getPhoto(pid);
        photo.setLickNum(photo.getLickNum() + 1);
        addPhoto(photo);
    }

    public PhotoDao getPhotoDao() {
        return photoDao;
    }

    public void setPhotoDao(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }
}
