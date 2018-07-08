package dcs.com.web.controller;

import dcs.com.web.constant.Constant;
import dcs.com.web.pojo.PhotoPO;
import dcs.com.web.pojo.bo.RestResponseBo;
import dcs.com.web.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping(value = {"","/"})
    public String getPhoto(@RequestParam(name = "pid") long pid, Model model){
        PhotoPO photo = photoService.getPhoto(pid);
        if (null == photo){
            return Constant.ERROR;
        }
        model.addAttribute("photo",photo);
        return Constant.PHOTO;
    }

    @PostMapping
    @ResponseBody
    public RestResponseBo clickLick(@RequestParam long pid, @RequestParam String methodName){
        photoService.addOnePhotoNum(pid, methodName);
        return RestResponseBo.ok();
    }

    public PhotoService getPhotoService() {
        return photoService;
    }

    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }
}
