package com.zj.dao;

import com.zj.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author zhoujian
 */
@Repository
public interface UserDao {
    /**
     * 根据username和password查询用户
     */
    User userSelect(User user);
}
