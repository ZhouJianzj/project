package com.zj.service;

import com.zj.dao.UserDao;
import com.zj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author zhoujian
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 用户登录
     */
    @Override
    public User userLogin(User user, HttpServletRequest request) {
        User dbUser = userDao.userSelect(user);
        //正确的用户放入redissession
        if (null != dbUser){
            HttpSession session = request.getSession();
            session.setAttribute("user",dbUser);
        }
        return dbUser;
    }
}
