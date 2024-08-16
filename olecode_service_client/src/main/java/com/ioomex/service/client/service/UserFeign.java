package com.ioomex.service.client.service;

import cn.hutool.core.util.ObjUtil;
import com.ioomex.common.app.common.ErrorCode;
import com.ioomex.common.app.exception.BusinessException;
import com.ioomex.module.app.entity.SysUser;
import com.ioomex.module.app.enums.UserRoleEnum;
import com.ioomex.module.app.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.ioomex.common.app.constant.UserConstant.USER_LOGIN_STATE;


@FeignClient(name="olecode-user-service",path="/api/user/inner")
public interface UserFeign {



    @PostMapping("/get/id")
    SysUser getById(long userId);

    @PostMapping("/get/ids")
    List<SysUser> listByIds(Set<Long> idList);



    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
     default SysUser getLoginUser(HttpServletRequest request){
         // 先判断是否已登录
         Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
         SysUser currentSysUser = (SysUser) userObj;
         if (ObjUtil.isEmpty(currentSysUser) || ObjUtil.isEmpty(currentSysUser.getId())){
             throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
         }
         return currentSysUser;
     }



    /**
     * 是否为管理员
     *
     */
     default boolean isAdmin(SysUser sysUser){
         return sysUser != null && UserRoleEnum.ADMIN.getValue().equals(sysUser.getUserRole());
     }


    /**
     * 获取脱敏的用户信息
     *
     */
     default UserVO getUserVO(SysUser sysUser){
         if (sysUser == null) {
             return null;
         }
         UserVO userVO = new UserVO();
         BeanUtils.copyProperties(sysUser, userVO);
         return userVO;
     }



}
