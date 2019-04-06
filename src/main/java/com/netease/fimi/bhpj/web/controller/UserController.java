package com.netease.fimi.bhpj.web.controller;

import com.google.common.collect.Maps;
import com.netease.fimi.bhpj.domain.User;
import com.netease.fimi.bhpj.service.UserService;
import com.netease.fimi.bhpj.util.ConstField;
import com.netease.fimi.bhpj.util.MD5Helper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private Map<String, Object> login(@RequestBody User user, HttpServletRequest request) {
        Map<String, Object> json = Maps.newHashMap();
        User temp = userService.getUserByUname(user.getUname());
        if (null == temp) {
            json.put("code", 0);
            json.put("msg", "用户不存在！");
        } else if (temp.getPassword().equals(user.getPassword())) {
            json.put("code", 1);
            json.put("user", temp);
            HttpSession session = request.getSession();
            session.setAttribute(ConstField.USER, temp);
            session.setMaxInactiveInterval(900);
            json.put("msg", "登录成功！");
        } else {
            json.put("code", 0);
            json.put("msg", "密码不正确！");
        }
        return json;
    }

    @ApiOperation(value = "用户登出", notes = "用户登出")
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    private Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> json = Maps.newHashMap();
        HttpSession session = request.getSession();
        session.removeAttribute(ConstField.USER);
        json.put("msg", "退出登陆成功");
        return json;
    }

    @ApiOperation(value = "用户注册或者用户信息修改（后台根据有无传入id自动进行判断）", notes = "传入user 进行save操作")
    @ApiImplicitParam(name = "user", value = "user对象", required = true, dataType = "User")
    @ResponseBody
    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    private Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> json = Maps.newHashMap();
        if (null == user.getId()) {
            User temp = userService.getUserByUname(user.getUname());
            if (null == temp) {
                userService.addUser(user);
                json.put("code", 1);
                json.put("user", user);
                json.put("mgs", "注册成功");
            } else {
                json.put("code", 0);
                json.put("msg", "用户名已经存在");
            }
        } else {
            userService.modifyUserById(user);
            json.put("code", 1);
            json.put("msg", "用户信息修改成功");
            json.put("user", "user");
        }

        return json;
    }


}
