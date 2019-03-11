package com.netease.fimi.bhpj.service;

import com.netease.fimi.bhpj.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void modifyUserById(User user);

    User getUserById(Integer id);

    User getUserByUname(String uname);

    List<User> getAllUserList();

    void deleteUserById(Integer id);
}
