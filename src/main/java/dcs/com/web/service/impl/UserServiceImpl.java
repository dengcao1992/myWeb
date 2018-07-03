package dcs.com.web.service.impl;

import dcs.com.web.dao.UserDao;
import dcs.com.web.exception.LoginException;
import dcs.com.web.pojo.UserPO;
import dcs.com.web.service.UserService;
import dcs.com.web.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserPO getUser(String userName) {
        return userDao.findByUsername(userName);
    }

    @Override
    public void addUser(UserPO user) {
        user.setPassword(UserUtils.passwordEncode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public void login(String userName, String password, HttpSession session) throws LoginException {
        UserPO user = getUser(userName);
        if (null == user){
            throw new LoginException("该用户不存在");
        }
        if (!UserUtils.verifyPassword(user.getPassword(),password)){
            throw new LoginException("用户名或密码错误");
        }
        user.setPassword("");
        session.setAttribute("user",user);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
