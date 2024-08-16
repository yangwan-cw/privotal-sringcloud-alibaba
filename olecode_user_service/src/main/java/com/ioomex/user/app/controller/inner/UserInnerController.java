package com.ioomex.user.app.controller.inner;


import com.ioomex.module.app.entity.SysUser;
import com.ioomex.service.client.service.UserFeign;
import com.ioomex.user.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/inner")
@Slf4j
@Api(tags = "用户管理-内部接口")
public class UserInnerController implements UserFeign {

    @Resource
    private UserService userService;


    @Override
    @ApiOperation(value = "根据用户 ID 获取用户", notes = "通过用户 ID 获取用户信息")
    @PostMapping("/get/id")
    public SysUser getById(@RequestBody long userId) {
        SysUser byId = userService.getById(userId);
        return byId;
    }

    @Override
    @ApiOperation(value = "根据用户 ID 列表获取用户列表", notes = "通过用户 ID 列表获取用户信息")
    @PostMapping("/get/ids")
    public List<SysUser> listByIds(@RequestBody Set<Long> idList) {
        List<SysUser> sysUsers = userService.listByIds(idList);
        return sysUsers;
    }
}
