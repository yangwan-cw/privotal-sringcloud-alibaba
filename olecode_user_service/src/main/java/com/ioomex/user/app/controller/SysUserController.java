package com.ioomex.user.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ioomex.common.app.annotation.AuthCheck;
import com.ioomex.common.app.common.BaseResponse;
import com.ioomex.common.app.common.DeleteRequest;
import com.ioomex.common.app.common.ErrorCode;
import com.ioomex.common.app.common.ResultUtils;
import com.ioomex.common.app.constant.UserConstant;
import com.ioomex.common.app.exception.BusinessException;
import com.ioomex.common.app.exception.ThrowUtils;
import com.ioomex.module.app.dto.user.*;
import com.ioomex.module.app.entity.SysUser;
import com.ioomex.module.app.vo.LoginUserVO;
import com.ioomex.module.app.vo.UserVO;
import com.ioomex.user.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.ioomex.user.app.service.impl.UserServiceImpl.SALT;


/**
 * 用户接口
 *
 * @author ioomex
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
@RestController
@RequestMapping("/")
@Slf4j
@Api(tags = "用户接口")
public class SysUserController {

    @Resource
    private UserService userService;



    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户通过此接口进行注册")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (ObjectUtils.isEmpty(userRegisterRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户通过此接口进行登录")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (ObjectUtils.isEmpty(userLoginRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(loginUserVO);
    }



    /**
     * 用户注销
     *
     */
    @PostMapping("/logout")
    @ApiOperation(value = "用户注销", notes = "用户通过此接口进行注销")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     */
    @GetMapping("/get/login")
    @ApiOperation(value = "获取当前登录用户", notes = "获取当前登录用户的信息")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        SysUser sysUser = userService.getLoginUser(request);
        return ResultUtils.success(userService.getLoginUserVO(sysUser));
    }

    /**
     * 创建用户
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @ApiOperation(value = "创建用户", notes = "管理员通过此接口创建新用户")
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest, HttpServletRequest request) {
        if (userAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userAddRequest, sysUser);
        // 默认密码 12345678
        String defaultPassword = "12345678";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + defaultPassword).getBytes());
        sysUser.setUserPassword(encryptPassword);
        boolean result = userService.save(sysUser);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(sysUser.getId());
    }

    /**
     * 删除用户
     *
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @ApiOperation(value = "删除用户", notes = "管理员通过此接口删除用户")
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新用户
     *
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @ApiOperation(value = "更新用户", notes = "管理员通过此接口更新用户信息")
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest,
                                            HttpServletRequest request) {
        if (userUpdateRequest == null || userUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userUpdateRequest, sysUser);
        boolean result = userService.updateById(sysUser);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 根据 id 获取用户（仅管理员）
     *
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @ApiOperation(value = "根据 ID 获取用户", notes = "管理员通过此接口获取用户信息")
    public BaseResponse<SysUser> getUserById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SysUser sysUser = userService.getById(id);
        ThrowUtils.throwIf(sysUser == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(sysUser);
    }

    /**
     * 根据 id 获取包装类
     *
     */
    @GetMapping("/get/vo")
    @ApiOperation(value = "根据 ID 获取用户包装类", notes = "通过此接口获取用户包装类信息")
    public BaseResponse<UserVO> getUserVOById(long id, HttpServletRequest request) {
        BaseResponse<SysUser> response = getUserById(id, request);
        SysUser sysUser = response.getData();
        return ResultUtils.success(userService.getUserVO(sysUser));
    }

    /**
     * 分页获取用户列表（仅管理员）
     *
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @ApiOperation(value = "分页获取用户列表", notes = "管理员通过此接口分页获取用户列表")
    public BaseResponse<Page<SysUser>> listUserByPage(@RequestBody UserQueryRequest userQueryRequest,
                                                      HttpServletRequest request) {
        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        Page<SysUser> userPage = userService.page(new Page<>(current, size),
          userService.getQueryWrapper(userQueryRequest));
        return ResultUtils.success(userPage);
    }

    /**
     * 分页获取用户封装列表
     *
     */
    @PostMapping("/list/page/vo")
    @ApiOperation(value = "分页获取用户封装列表", notes = "分页获取用户封装类信息")
    public BaseResponse<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryRequest userQueryRequest,
                                                       HttpServletRequest request) {
        if (userQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<SysUser> userPage = userService.page(new Page<>(current, size),
          userService.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVOPage = new Page<>(current, size, userPage.getTotal());
        List<UserVO> userVO = userService.getUserVO(userPage.getRecords());
        userVOPage.setRecords(userVO);
        return ResultUtils.success(userVOPage);
    }


    /**
     * 更新个人信息
     *
     */
    @PostMapping("/update/my")
    @ApiOperation(value = "更新个人信息", notes = "用户通过此接口更新自己的个人信息")
    public BaseResponse<Boolean> updateMyUser(@RequestBody UserUpdateMyRequest userUpdateMyRequest,
                                              HttpServletRequest request) {
        if (userUpdateMyRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SysUser loginSysUser = userService.getLoginUser(request);
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userUpdateMyRequest, sysUser);
        sysUser.setId(loginSysUser.getId());
        boolean result = userService.updateById(sysUser);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }
}
