package com.zj.contoller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.annotation.IgnoreResponseAdvice;
import com.zj.entity.*;
import com.zj.service.SysService;
import com.zj.util.MyPageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author zhoujian
 */

@RestController
@RequestMapping("sys")
public class SysController {
    @Resource
    private SysService sysService;

    /**
     * 测试网关
     *
     * @return 测试网关
     */
    @IgnoreResponseAdvice
    @RequestMapping("test")
    public String test() {
        return "test";
    }

    /**
     * 用户登录
     *
     * @return 用户对象
     */
    @PostMapping("login")
    public UserManager userLoginController(@RequestBody User user, HttpServletRequest request) {
        return sysService.userLoginService(user, request);
    }

    /**
     * 公司查询，支持模糊查询和分页
     *
     * @param pageNo   页码
     * @param pageSize 页面数据量
     * @param orgName  查询关键字
     * @return 结果集
     */
    @GetMapping("orga")
    public Page findOrganizeController(@RequestParam("orgName") String orgName,
                                                     @RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
                                                     @RequestParam(value = "pageSize", defaultValue = "8") String pageSize) {

        return MyPageHelper.myPageHelper(new Page(sysService.findOrganizeService(orgName),Integer.parseInt(pageNo),Integer.parseInt(pageSize)));
    }

    /**
     * 添加机构
     *
     * @param organize 插入参数
     * @return 插入是否成功
     */
    @PostMapping("orga")
    public CommonResponse<Object> addOrganizeController(@RequestBody Organize organize) {
        return sysService.addOrganizeService(organize);
    }

    /**
     * 角色查询
     *
     * @param pageNo   页码
     * @param pageSize 每一个展示的数据
     * @param roleName 查询关键字
     * @return role结果集
     */
    @GetMapping("role")
    public Page findRoleController(String roleName,
                                             @RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
                                             @RequestParam(value = "pageSize", defaultValue = "8") String pageSize) {

      return   MyPageHelper.myPageHelper(new Page(sysService.findRoleService(roleName),Integer.parseInt(pageNo), Integer.parseInt(pageSize)));

    }

    /**
     * 添加角色
     *
     * @param role 添加对象参数
     * @return 返回结果
     */
    @PostMapping("role")
    public CommonResponse<Object> addRoleController(@RequestBody Role role) {
        return sysService.addRoleService(role);
    }

    /**
     * 删除角色
     */
    @DeleteMapping("role")
    public CommonResponse<Boolean> deleteRoleController(int id) {
        return sysService.deleteRoleService(id);
    }

    /**
     * 权限查询
     *
     * @return 权限结果集
     */
    @GetMapping("perm")
    public List<Perm> findPermController() {
        return sysService.findPermService();

    }

    /**
     * 角色分配权限
     * 支持role权限的分配，permId可以是数组
     *
     * @return 统一响应体
     */
    @PostMapping("role/perm")
    public CommonResponse<Object> addRolePermController(@RequestBody RolePerm rolePerm) {

        if (sysService.addRolePermService(rolePerm)){
            return new CommonResponse<>(200, "修改成功！", true);
        }else {
            return new CommonResponse<>(400, "修改失败！", false);
        }

    }


    /**
     * 根据用户的手机号 或者 用户名模糊查询
     */
    @GetMapping("user/key")
    public Page findUserController(@RequestParam(value = "key", defaultValue = "1") String key,
                                   @RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
                                   @RequestParam(value = "pageSize", defaultValue = "8") String pageSize) {
        return MyPageHelper.myPageHelper(new Page(sysService.finUserService(key), Integer.parseInt(pageNo), Integer.parseInt(pageSize)));

    }

    /**
     * 根据id修改用户username phone roleId
     */
    @PutMapping("user/modify")
    public CommonResponse<Boolean> modifyUserController(@RequestBody User user) {

        if (sysService.modifyUserService(user)) {
            return new CommonResponse<>(200, "修改成功！", true);
        }
        return new CommonResponse<>(400, "修改失败！",false);
    }

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 结果集
     */
    @GetMapping("user/id")
    public UserManager findUserIdController(@RequestParam("id") String id) {
        return sysService.findUserIdService(id);
    }

    /**
     * 新增用户
     *
     * @return 统一响应体
     */
    @PostMapping("user")
    public CommonResponse<Boolean> addUserManagerController(@RequestBody User user) {
        return sysService.addUserManagerService(user);
    }

    /**
     * 根据name查询用户
     * */
    @GetMapping("user/name")
    public Boolean findUserNameController(@RequestParam("name") String name){
        return sysService.findUserNameService(name);
    }

    /**
     * 删除用户
     *
     * @param id 用户地
     * @return 统一响应体
     */
    @DeleteMapping("user")
    public CommonResponse<Boolean> deleteUserManagerController(@RequestParam("id") int id) {
        return sysService.deleteUserManagerService(id);
    }

    /**
     * 查询日志
     *
     * @param pageNo   页码
     * @param pageSize 单页面数据量
     * @return 数据
     */
    @GetMapping("log")
    public PageInfo<Log> findLogController(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
                                           @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end,
                                            @RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
                                           @RequestParam(value = "pageSize", defaultValue = "8") String pageSize) {
        PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        return new PageInfo<Log>(sysService.findLogService(from,end));
    }

    /**
     * 退出登录
     */
    @GetMapping("logout")
    public CommonResponse<Object> signOutController(HttpServletRequest request) {
        return sysService.signOutService(request);

    }

    /**
     * 修改密码
     */
    @PutMapping("user/password")
    public CommonResponse<Boolean> modifyPasswordController(@RequestBody UserManager userManager) {
        return sysService.modifyPasswordService(userManager);
    }

    /**
     * 删除权限
     * */
    @DeleteMapping("perm")
    public CommonResponse<Boolean> deletePermController(@RequestParam("id") int id){
        return sysService.deletePermService(id);
    }

    /**
     * 添加权限
     * */
    @PostMapping("perm")
    public CommonResponse<Boolean> insertPermController(@RequestBody Perm perm){
        return sysService.insertPermService(perm);
    }

    /**
     * 修改权限
     * */
    @PutMapping("perm")
    public CommonResponse<Boolean> modifyPermController(@RequestBody Perm perm){
        return sysService.modifyPermService(perm);
    }

    /**
     * 根据orgaId查询用户
     * */
    @GetMapping("/user/orga")
    public Page selectUserByOrgaIdController(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo, @RequestParam(value = "pageSize", defaultValue = "8") String pageSize,@RequestParam("orgaId") int orgaId,@RequestParam("username")String username){
        return MyPageHelper.myPageHelper(new Page(sysService.selectUserByOrgaIdService(orgaId, username),Integer.parseInt(pageNo),Integer.parseInt(pageSize)));
    }

    /**
     * 查询所有组织类型
     * */
    @GetMapping("/orgaType")
    public Page selectOrgaTypeController(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo, @RequestParam(value = "pageSize", defaultValue = "8") String pageSize){
        return MyPageHelper.myPageHelper(new Page(sysService.selectOrgaTypeService(),Integer.parseInt(pageNo),Integer.parseInt(pageSize)));
    }

    /**
     * 根据id批量删除用户
     * */
    @DeleteMapping("user/ids")
    public CommonResponse<Boolean> deleteUserListController(@RequestParam Integer[] idArrays){
        return sysService.deleteUserListService(idArrays);
    }

    /**
     * 给组织批量新增用户(就是将用户的orga_id修改为当前组织id)
     * */
    @PutMapping("user/orgaId")
    public CommonResponse<Boolean> insertUsersIntoOrgaController(@RequestBody User user){
        return sysService.insertUsersIntoOrgaService(user);
    }

    /**
     * 查询除指定组织以外的所有员工
     * */
    @GetMapping("user/orgaId")
    public Page selectUserOtherController(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo, @RequestParam(value = "pageSize", defaultValue = "8") String pageSize,@RequestParam("orgaId") int orgaId, @Param("key")String key){
        return MyPageHelper.myPageHelper(new Page(sysService.selectUserOtherService(orgaId,key),Integer.parseInt(pageNo),Integer.parseInt(pageSize)));
    }

    /**
     * 修改组织信息
     * */
    @PutMapping("orga")
    public CommonResponse<Boolean> modifyOrganizeController(@RequestBody Organize organize){
        return sysService.modifyOrgaService(organize);
    }
}