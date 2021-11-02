package com.zj.service;

import com.zj.dao.SysDao;
import com.zj.entity.User;
import com.zj.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


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
    public User userLogin(User user, HttpServletRequest request) {
        String password = user.getPassword();
        try {
            String ps = Md5.EncoderByMd5(password);
            user.setPassword(ps);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //查询
        User dbUser = userDao.userSelect(user);
        //正确的用户放入redissession
        if (null != dbUser){
            HttpSession session = request.getSession();
            session.setAttribute("user",dbUser);

        }
        return dbUser;
    }
}
