package dcs.com.web.controller.admin;

import dcs.com.web.constant.Constant;
import dcs.com.web.exception.LoginException;
import dcs.com.web.pojo.UserPO;
import dcs.com.web.service.PhotoService;
import dcs.com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private PhotoService photoServiceImpl;

    @GetMapping(value = {"", "/"})
    public String getAdmin(HttpServletRequest request,Model model){
        UserPO user = (UserPO) request.getSession().getAttribute("user");
        user.setPassword("");
        model.addAttribute("user",user);
        return Constant.ADMIN_HOME;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLogin(){
        return Constant.ADMIN_LOGIN;
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(value = "username") String userName, @RequestParam(value = "password") String password, HttpServletRequest request,
                        Model model){
        HttpSession session = request.getSession();
        try {
            userServiceImpl.login(userName,password,session);
        } catch (LoginException e) {
            ///应该返回错误提示
            model.addAttribute("error",e.getMessage());
            return Constant.ADMIN_LOGIN;
        }
        return Constant.ADMIN_HOME;

    }

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String getUpload(){
        return Constant.ADMIN_UPLOAD;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile[] files,@RequestParam String title, @RequestParam String categories,
                         Model model,HttpServletRequest request){
        UserPO user = (UserPO)request.getSession().getAttribute("user");
        int succeedNum = 0;
        int fileNum = files.length;
        for (MultipartFile file : files){
            if (photoServiceImpl.upload(file,title,categories,user)){
                succeedNum ++;
            }
        }
        String result = "成功上传" + succeedNum + "个文件，" + "失败" + (fileNum - succeedNum) + "个文件";
        ///需要返回body
        return "test-ok" ;
    }

    @GetMapping(value = "/signin")
    public String getSignIn(){
        return Constant.ADMIN_SIGNIN;
    }

    @PostMapping(value = "/signin")
    public String signIn(UserPO user, HttpServletRequest request){
        userServiceImpl.addUser(user);
        user.setPassword("");
        request.getSession().setAttribute("user",user);
        return Constant.ADMIN_HOME;
    }

    public UserService getUserServiceImpl() {
        return userServiceImpl;
    }

    public void setUserServiceImpl(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public PhotoService getPhotoServiceImpl() {
        return photoServiceImpl;
    }

    public void setPhotoServiceImpl(PhotoService photoServiceImpl) {
        this.photoServiceImpl = photoServiceImpl;
    }
}
