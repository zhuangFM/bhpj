package com.netease.fimi.bhpj.repository;

import com.netease.fimi.bhpj.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    void addUser(User user);

    void modifyUserById(User user);

    User getUserById(@Param("id") Integer id);

    List<User> getAllUserList();

    void deleteUserById(@Param("id") Integer id);

}
