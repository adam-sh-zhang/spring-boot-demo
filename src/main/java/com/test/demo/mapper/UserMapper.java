package com.test.demo.mapper;

import com.test.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Adam.Zhang
 * @date 2018/1/23
 */
@Mapper
public interface UserMapper {

    User getUserByUsername(String username);

    void createUser(User user);

    void updateUser(User user);

    List<User> getUserList(@Param("keyword") String keyword);

}
