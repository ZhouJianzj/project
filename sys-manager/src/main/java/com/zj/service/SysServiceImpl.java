package com.zj.service;

import com.zj.dao.SysDao;
import com.zj.entity.*;
import com.zj.util.ListToTree;
import com.zj.util.MD5Util;
import com.zj.util.OrgaTree;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


/**
 * @author zhoujian
 */
@Service
public class SysServiceImpl implements SysService {
    @Resource
    private SysDao sysDao;

    /**
     * 用户登录
     */
    @Override
    public UserManager userLoginService(User user, HttpServletRequest request) {
        String password = user.getPassword();
        //md5加密
        //用户传输过来的是123
        String s = MD5Util.addMD5(password);
        System.out.println(s);
        user.setPassword(s);
        //查询
        UserManager userManager = sysDao.userSelect(user);
        //正确的用户放入redis session
        if (null != userManager) {
            HttpSession session = request.getSession();
            List<Role> roles = userManager.getRoles();
            //去除子菜单
            for (Role role : roles){
                List<Perm> perms = role.getPerms();
                perms.removeIf(perm -> perm.getParentId() != 0);
            }
            userManager.setRoles(roles);
            userManager.setPassword(password);
            session.setAttribute("user", userManager);
        }
        return userManager;
    }


    /**
     * 机构查询
     */
    @Override
    public List<Organize> findOrganizeService(String orgName) {
        List<Organize> organizes = sysDao.organizeSelect(orgName);
        if (orgName == null){
            OrgaTree orgaTree = new OrgaTree(organizes);
            organizes=orgaTree.buildTree();
        }
        return organizes;
    }

    /**
     * 添加机构
     */
    @Override
    public CommonResponse<Object> addOrganizeService(Organize organize) {
        CommonResponse<Object> response = new CommonResponse<>();
        if (sysDao.organizeInsert(organize)) {
            response.setStatus(200);
            response.setMsg("添加成功！");
            return response;
        } else {
            response.setStatus(400);
            response.setMsg("添加失败！");
            return response;
        }

    }


    /**
     * 删除角色
     * 首先删除角色表中的角色，
     * 然后去role_perm表中删除对应关系
     * 然后再去user_role中删除对应的关系
     */
    @Override
    @Transactional
    public CommonResponse<Boolean> deleteRoleService(int id) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        //删除角色表中的对应的角色id
        if (sysDao.roleDelete(id)) {
            //用户user_role查询，查到就删除
            if (sysDao.roleUserSelect(id).size() != 0) {
                sysDao.roleUserDelete(id);
            }
            // 查询role_perm表中对应的关系，查到就删
            if (sysDao.rolePermSelect(id).size() != 0) {
                sysDao.rolePermDelete(id);
            }
            response.setStatus(200);
            response.setMsg("删除成功");
        } else {
            response.setStatus(400);
            response.setMsg("删除失败");

        }
        return response;
    }

    /**
     * 角色查询
     *
     * @param roleName 角色字段
     * @return 结果集
     */
    @Override
    public List<Role> findRoleService(String roleName) {
        List<Role> roleList =  sysDao.roleSelect(roleName);
        return roleList;

    }

    /**
     * 角色添加
     *
     * @param role 参数
     * @return 返回结果
     */
    @Override
    public CommonResponse<Object> addRoleService(Role role) {
        CommonResponse<Object> response = new CommonResponse<>();
        if (role.getName() != null && role.getExt() != null && sysDao.roleInsert(role)) {
            response.setStatus(200);
            response.setMsg("添加成功！");
            return response;
        } else {
            response.setStatus(400);
            response.setMsg("添加失败！");
            return response;
        }
    }

    /**
     * 查询所有权限
     *
     * @return 权限结果集
     */
    @Override
    public List<Perm> findPermService() {
        List<Perm> perms = sysDao.permSelect();
        ListToTree tree = new ListToTree(perms);
        perms = tree.buildTree();
        return perms;
    }

    /**
     * 给角色添加权限
     */
    @Override
    @Transactional
    public Boolean addRolePermService(RolePerm rolePerm) {

        //先全部删除之前的 role_perm关系然后
        Integer[] permIdArrays = rolePerm.getPermIdArrays();
        Boolean b = false;
        if (permIdArrays.length != 0){
            //删除所有的对应关系
            sysDao.deleteRolePerm(rolePerm.getRoleId());
            //循环添加
            for (Integer permId : permIdArrays) {
                b = sysDao.rolePermInsert(rolePerm.getRoleId(), permId);
            }
          return b;
        }else {
            //删除所有的对应关系
            return sysDao.deleteRolePerm(rolePerm.getRoleId());

        }

    }


    /**
     * 查询用户
     * 支持模糊查询查询关键字可以是手机号或者是用户名关键直段为空的时候表示查询所有
     */
    @Override
    public List<UserManager> finUserService(String Key) {
        List<UserManager> userManagers = null;
        if (Key == null || "".equals(Key) ) {
            userManagers = sysDao.allUserSelect();
        } else {
            userManagers = sysDao.userKeySelect(Key);
        }
        return userManagers;
    }

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 查询到对应id的用户
     */
    @Override
    public UserManager findUserIdService(String id) {
        return sysDao.userIdSelect(id);
    }

    /**
     * 用户添加
     */
    @Override
    public CommonResponse<Boolean> addUserManagerService(User user) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        // 对新增用户的密码进行加密操作
        String s = MD5Util.addMD5("123456");
        //密码加密
        user.setPassword(s);
        //获取角色id
        Integer[] roleIdArrays = user.getRoleIdArrays();
        //user表插入用户
        if (sysDao.userManagerInsert(user)) {
            //新增user的自增主键
            int userid = sysDao.useridGet();
            //获取role id 循环添加
            for (int i = 0; i < roleIdArrays.length; i++) {
                sysDao.userRoleInsert(userid, roleIdArrays[i]);
            }
            response.setMsg("添加成功");
            response.setStatus(200);
        } else {
            response.setStatus(400);
            response.setMsg("添加失败");
        }
        return response;
    }

    /**
     * 修改用户 修改user表中的username phone
     * 先查询user_role中是否已经有对应的关系然后
     * 添加 user_role 中的对应关系
     */
    @Override
    @Transactional
    public Boolean modifyUserService(User user) {

        //修改user   name 和  phone
        if (user.getUsername() != null && user.getPhone() != null) {
            //修改user表
            sysDao.userUpdate(user);
            //修改user的 orgaId，如何传来的是空就表示去除用户的组织
            sysDao.userUpdateOrgaId(user.getId(), user.getOrgaId());
        }

        Integer[] roleId = user.getRoleIdArrays();
        //添加user_role表中的对应权限
        if (roleId.length != 0) {
            //先全部删除
            sysDao.userRoleDelete(user.getId());
            Boolean insert = false;
            //在依次的添加
            for (Integer role : roleId) {
                //取决于sql操作
                insert = sysDao.userInsertRole(user.getId(), role);

            }
            return insert;

        }else {
           return sysDao.userRoleDelete(user.getId());
        }
    }

    /**
     * 修改密码，修改密码前的旧密码给前端校验
     */
    @Override
    public CommonResponse<Boolean> modifyPasswordService(UserManager userManager) {
        String password = MD5Util.addMD5(userManager.getPassword());
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (sysDao.passwordModify(userManager.getId(), password)) {
            response.setMsg("修改密码成功");
            response.setStatus(200);
        } else {
            response.setMsg("修改密码失败");
            response.setStatus(400);
        }
        return response;
    }

    /**
     * 查询user的name是否存在
     */
    @Override
    public Boolean findUserNameService(String name) {
        Boolean b = true;
        b = sysDao.userNameSelect(name).size() == 0;
        return b;
    }

    /**
     * 删除权限
     * */
    @Override
    public CommonResponse<Boolean> deletePermService(int id) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (sysDao.permDelete(id)){
            response.setMsg("删除成功");
            response.setStatus(200);
            response.setData(true);
        }else {
            response.setStatus(400);
            response.setMsg("删除失败");
        }
        return response;
    }

    /**
     * 新增权限
     * */
    @Override
    public CommonResponse<Boolean> insertPermService(Perm perm) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (sysDao.permInsert(perm)){
            response.setMsg("新增成功");
            response.setStatus(200);
        }else {
            response.setStatus(400);
            response.setMsg("新增失败");
        }
        return response;
    }

    /**
     * 修改权限
     * */
    @Override
    public CommonResponse<Boolean> modifyPermService(Perm perm) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (sysDao.permModify(perm)){
            response.setMsg("修改成功");
            response.setStatus(200);
        }else {
            response.setStatus(400);
            response.setMsg("修改失败");
        }
        return response;
    }

    /**
     * 根据组织id查询用户
     * */
    @Override
    public List<UserManager> selectUserByOrgaIdService(int orgaId,String username) {
        return sysDao.userByOrgaIdSelect(orgaId,username);
    }

    @Override
    public List<OrgaType> selectOrgaTypeService() {
        return sysDao.orgaTypeSelect();
    }

    /**
     * 批量删除用户
     * */
    @Override
    public CommonResponse<Boolean> deleteUserListService(Integer[] idArrays) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        System.out.println(idArrays + "===================================");
        //for (int i = 0; i < ids.size(); i++) {
            if (sysDao.userOrgaModify(idArrays)) {
                response.setMsg("删除成功");
                response.setStatus(200);
            } else {
                response.setStatus(400);
                response.setMsg("删除失败");
            }
        //}
        return response;
    }

    /**
     * 批量给orga添加用户
     * */
    @Override
    public CommonResponse<Boolean> insertUsersIntoOrgaService(User user) {
        CommonResponse<Boolean> response  = new CommonResponse<>();
        int orgaId = user.getOrgaId();
        Integer[] array = user.getIdArrays();
        for (int i = 0; i < array.length; i++) {
            if (sysDao.userOrgaInsert(array[i],orgaId)){
                response.setMsg("成功");
                response.setStatus(200);
            }else {
                response.setMsg("失败");
                response.setStatus(400);
            }
        }
        return response;
    }

    /**
     * 查询除指定组织以外的所有的用户
     * */
    @Override
    public List<User> selectUserOtherService(int orgaId,String key) {
        return sysDao.otherUserSelect(orgaId,key);
    }

    /**
     * 修改组织信息
     * */
    @Override
    public CommonResponse<Boolean> modifyOrgaService(Organize organize) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        if (sysDao.orgaModify(organize)){
            response.setMsg("修改成功");
            response.setStatus(200);
        }else {
            response.setStatus(400);
            response.setMsg("修改失败");
        }
        return response;
    }

    /**
     * 删除用户，首先删除用户表，然后根据user的id到user_role表中删除对应的关系
     */
    @Override
    @Transactional
    public CommonResponse<Boolean> deleteUserManagerService(int id) {
        CommonResponse<Boolean> response = new CommonResponse<>();
        //到user表中删除对应id的user   删除user_role中删除对应的关系
        if (sysDao.userDelete(id) && sysDao.userRoleDelete(id)) {
            response.setMsg("删除成功");
            response.setStatus(200);
        } else {
            response.setStatus(400);
            response.setMsg("删除失败");
        }
        return response;

    }


    /**
     * 实现log分页查询
     * @return 结果集合
     */
    @Override
    public List<Log> findLogService(Date from,Date end) {

        return sysDao.logSelect(from,end);

    }

    /**
     * 退出登录
     */
    @Override
    public CommonResponse<Object> signOutService(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (null != session) {
            session.removeAttribute("user");
            return new CommonResponse<>(200, "用户退出登录！");
        }
        return new CommonResponse<>(400, "退出登录失败！");
    }


}
