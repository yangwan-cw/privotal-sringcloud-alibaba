package com.ioomex.user.app.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ioomex.module.app.dto.user.UserQueryRequest;
import com.ioomex.module.app.entity.SysUser;
import com.ioomex.module.app.vo.LoginUserVO;
import com.ioomex.module.app.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户服务
 *
 * @author ioomex
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
public interface UserService extends IService<SysUser> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);



    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    SysUser getLoginUser(HttpServletRequest request);

    /**
     * 获取当前登录用户（允许未登录）
     *
     * @param request
     * @return
     */
    SysUser getLoginUserPermitNull(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param sysUser
     * @return
     */
    boolean isAdmin(SysUser sysUser);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取脱敏的已登录用户信息
     *
     * @return
     */
    LoginUserVO getLoginUserVO(SysUser sysUser);

    /**
     * 获取脱敏的用户信息
     *
     * @param sysUser
     * @return
     */
    UserVO getUserVO(SysUser sysUser);

    /**
     * 获取脱敏的用户信息
     *
     * @param sysUserList
     * @return
     */
    List<UserVO> getUserVO(List<SysUser> sysUserList);

    /**
     * 获取查询条件
     *
     * @param userQueryRequest
     * @return
     */
    QueryWrapper<SysUser> getQueryWrapper(UserQueryRequest userQueryRequest);

}
