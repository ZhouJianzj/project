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
     * 根据公司的名字模糊查询，使用到分页
     */
    List<Organize> organizeSelect(String orgName);

    /**
     * 机构插入
     *
     * @param organize
     * @return
     */
    boolean organizeInsert(Organize organize);

    /**
     * 查询角色，支持模糊查询
     *
     * @return
     */
    List<Role> roleSelect();

    /**
     * 根据id删除角色
     *
     * @return
     */
    boolean roleUserPermDelete(int id);

    /**
     * 查询角色
     *
     * @param roleName
     * @return
     */
    List<Role> roleSelect(String roleName);

    /**
     * 角色插入
     *
     * @param role 插入参数
     * @return 返回结果
     */
    boolean roleInsert(Role role);

    /**
     * 查询所有的权限
     *
     * @return 返回结果集
     */
    List<Perm> permSelect();

    boolean rolePermInsert(String roleId, String permId);

    /**
     * 添加用户
     *
     * @param userManager
     * @return 返回结果
     */
    boolean userManagerInsert(UserManager userManager);

    /**
     * 删除用户
     *
     * @param id
     * @return 返回结果
     */
    boolean userManagerDelete(int id);

    boolean userDelete(int id);

    /**
     * 查询用户通过关键字
     *
     * @param key
     */
    List<UserManager> userKeySelect(String key);


    /**
     * 查询用户根据id
     *
     * @param id
     * @return
     */
    UserManager userIdSelect(String id);

    List<Log> logSelect();

    /**
     * 查询所有用户
     */
    List<UserManager> allUserSelect();

    /**
     * 修改用户通过id修改name phone
     *
     * @return
     */
    Boolean userUpdate(User user);

    /**
     * 给用户添加角色
     */
    Boolean userInsertRole(User user);

    /**
     * 根据id查询user_role表数据
     */
    UserRole userRoleIdSelect(int id);


    Object rolePermSelect(int id);

    Object roleUserSelect(int id);

    boolean roleUserDelete(int id);

    boolean roleDelete(int id);

    boolean rolePermDelete(int id);
}
