package com.netease.fimi.bhpj.web.controller;

import com.google.common.collect.Maps;
import com.netease.fimi.bhpj.domain.User;
import com.netease.fimi.bhpj.service.UserService;
import com.netease.fimi.bhpj.util.MD5Helper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登陆", notes = "传入user 进行登陆操作")
    @ApiImplicitParam(name = "user", value = "user对象", required = true, dataType = "User")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> json = Maps.newHashMap();
        User temp = userService.getUserByUname(user.getUname());
        if (null == temp) {
            json.put("code", 0);
            json.put("msg", "用户不存在！");
        } else if (MD5Helper.checkMD5Str(temp.getPassword(),user.getPassword())) {
            json.put("code", 1);
            json.put("user", temp);
            json.put("msg", "登录成功！");
        } else {
            json.put("code", 0);
            json.put("msg", "密码不正确！");
        }
        return json;
    }


}
