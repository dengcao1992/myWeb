package dcs.com.web.controller;


import dcs.com.web.constant.Constant;
import dcs.com.web.pojo.PhotoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import dcs.com.web.service.PhotoService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private PhotoService photoService;
    @RequestMapping(value ={"/","/index"})
    public String getHome(Model model){
        return getHome(1,model,"default");
    }
/*
p 页数
ONE_PAGE_LIMIT 每页图片个数
 */
    @RequestMapping(value ="/page/{p}")
    public String getHome(@PathVariable int p, Model model, @RequestParam(value = "category",defaultValue = "default") String category){
        Page<PhotoPO> photos =photoService.getPhotos(p,Constant.ONE_PAGE_LIMIT);
        model.addAttribute("photos",photos);
        model.addAttribute("category",category);
        return Constant.HOME;
    }

}
