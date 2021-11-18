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
    List<Organize> organizeSelect(String name);

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

    boolean rolePermInsert(Integer roleId, Integer permId);

    /**
     * 添加用户
     *
     * @param
     * @return 返回结果
     */
    boolean userManagerInsert(User user);

    /**
     * 删除用户
     */
    boolean userRoleDelete(int id);

    boolean userDelete(int id);


    /**
     * 查询用户通过关键字
     */
    List<UserManager> userKeySelect(String key);


    /**
     * 查询用户根据id
     */
    UserManager userIdSelect(String id);

    /**
     * 查询日志
     *
     * @return 返回log list
     */
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
    Boolean userInsertRole(Integer id, Integer roleId);

    /**
     * 在插入user——role表前检查是否已经存在
     */
    List<UserRole> userSelectRole(Integer id, Integer roleId);


    List<RolePerm> rolePermSelect(int id);

    List<UserRole> roleUserSelect(int id);


    boolean roleUserDelete(int id);

    boolean roleDelete(int id);

    boolean rolePermDelete(int id);

    Boolean passwordModify(int id, String password);

    boolean userRoleInsert(Integer userid, int roleid);

    int useridGet();

    /**
     * 编辑user的组织id
     */
    Boolean userUpdateOrgaId(Integer id, Integer orgaId);

    Boolean deleteRolePerm(int roleId);

    List<UserManager> userNameSelect(String username);

    /**
     * 删除权限
     * */
    boolean permDelete(int id);

    /**
     * 权限新增
     * */
    boolean permInsert(Perm perm);

    boolean permModify(Perm perm);

    List<UserManager> userByOrgaIdSelect(int orgaId);

    List<OrgaType> orgaTypeSelect();

    boolean userOrgaInsert(Integer id,Integer orgaId);
}
