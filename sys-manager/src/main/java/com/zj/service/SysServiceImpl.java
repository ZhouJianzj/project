package com.zj.service;

import com.github.pagehelper.PageHelper;
import com.zj.dao.SysDao;
import com.zj.entity.Organize;
import com.zj.entity.User;
import com.zj.entity.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author zhoujian
 */
@Service
public class SysServiceImpl implements SysService {
    @Autowired
    private SysDao sysDao;

    /**
     * 用户登录
     */
    @Override
    public UserManager userLoginService(User user, HttpServletRequest request) {
        String password = user.getPassword();
//        //md5加密
//        try {
//            String ps = Md5.EncoderByMd5(password);
//            user.setPassword(ps);
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        //查询
        UserManager userManager = sysDao.userSelect(user);
        //正确的用户放入redissession
        if (null != userManager){
            HttpSession session = request.getSession();
            session.setAttribute("user",userManager);

        }
        return userManager;
    }

    /**
     * 机构查询
     * @param pageNo
     * @param pageSize
     * @param orgName
     * @return
     */
    @Override
    public List<Organize> findOrganzieService(String pageNo, String pageSize, String orgName) {
        Integer num = Integer.valueOf(pageNo);
        Integer size = Integer.valueOf(pageSize);
        PageHelper.startPage((num-1)*size,size);
        return sysDao.organizeSelect(orgName);
    }

    /**
     * 机构的增减
     * @return
     */
    @Override
    public boolean addOrganizeService(Organize organize) {
        return sysDao.organizeInsert(organize);
    }
}
