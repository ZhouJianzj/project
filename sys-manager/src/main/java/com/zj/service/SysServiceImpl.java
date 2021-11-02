package com.zj.service;

import com.zj.dao.SysDao;
import com.zj.entity.User;
import com.zj.entity.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author zhoujian
 */
@Service
public class SysServiceImpl implements SysService {
    @Autowired
    private SysDao userDao;

    /**
     * 用户登录
     */
    @Override
    public UserManager userLogin(User user, HttpServletRequest request) {
        String password = user.getPassword();
//        //md5加密
//        try {
//            String ps = Md5.EncoderByMd5(password);
//            user.setPassword(ps);
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        //查询
        UserManager userManager = userDao.userSelect(user);
        //正确的用户放入redissession
        if (null != userManager){
            HttpSession session = request.getSession();
            session.setAttribute("user",userManager);

        }
        return userManager;
    }
}
