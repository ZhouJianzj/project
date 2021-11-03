package com.zj.dao;

import com.zj.entity.*;
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

    /**
     * 查询角色，支持模糊查询
     * @return
     */
    List<Role> roleSelect();

    /**
     * 根据id删除角色
     * @return
     */
    boolean roleDelete(int id);

    /**
     * 查询角色
     * @param roleName
     * @return
     */
    List<Role> roleSelect(String roleName);

    /**
     * 角色插入
     * @param role 插入参数
     * @return 返回结果
     */
    boolean roleInsert(Role role);

    /**
     * 查询所有的权限
     * @return 返回结果集
     */
    List<Perm> permSelect();

    boolean rolePermInsert(String roleId, String permId);

    /**
     *查询用户通过关键字
     * @param key
     */
    List<UserManager> userKeySelect(String key);


    UserManager userIdSelect(String id);
}
