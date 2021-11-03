package com.zj.dao;

import com.zj.entity.Organize;
import com.zj.entity.User;
import com.zj.entity.UserManager;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoujian
 */
@Repository
public interface SysDao {
    /**
     * 根据username和password查询用户
     */
    UserManager userSelect(User user);

    /**
     *
     * 根据公司的名字模糊查询，使用到分页
     */
    List<Organize> organizeSelect(String orgName);

    /**
     * 机构插入
     * @param organize
     * @return
     */
    boolean organizeInsert(Organize organize);
}
