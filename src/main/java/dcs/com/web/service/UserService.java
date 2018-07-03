package dcs.com.web.service;


import dcs.com.web.exception.LoginException;
import dcs.com.web.pojo.PhotoPO;
import dcs.com.web.pojo.UserPO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    UserPO getUser(String userName);
    void login(String userName, String password, HttpSession session) throws LoginException;
    void addUser(UserPO user);
}
