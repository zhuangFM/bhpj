package com.netease.fimi.bhpj.service.impl;

import com.netease.fimi.bhpj.domain.User;
import com.netease.fimi.bhpj.repository.UserMapper;
import com.netease.fimi.bhpj.service.UserService;
import com.netease.fimi.bhpj.util.TimeGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        user.setCreateTime(TimeGetter.getCurrentTimeStr());
        userMapper.addUser(user);
    }

    @Override
    public void modifyUserById(User user) {
        userMapper.modifyUserById(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByUname(String uname) {
        return userMapper.getUserByUname(uname);
    }

    @Override
    public List<User> getAllUserList() {
        return userMapper.getAllUserList();
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }
}
