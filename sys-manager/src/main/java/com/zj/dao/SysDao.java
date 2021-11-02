package com.zj.dao;

import com.zj.entity.User;
import com.zj.entity.UserManager;
import org.springframework.stereotype.Repository;

/**
 * @author zhoujian
 */
@Repository
public interface SysDao {
    /**
     * 根据username和password查询用户
     */
    UserManager userSelect(User user);
}
