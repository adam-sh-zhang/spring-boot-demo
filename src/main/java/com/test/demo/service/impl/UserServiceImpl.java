package com.test.demo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.test.demo.mapper.UserMapper;
import com.test.demo.model.PageResult;
import com.test.demo.model.User;
import com.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Adam.Zhang
 * @date 2018/1/23
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.createUser(user);
        return user;
    }

    @Override
    public PageResult<User> getUserList(int pageIndex, int pageSize, String orderBy, String keyword) {
        Page<User> userPageList = PageHelper.startPage(pageIndex + 1, pageSize, orderBy).doSelectPage(() -> {
            userMapper.getUserList(keyword);
        });
        PageResult<User> userPageResult = new PageResult<>();
        userPageResult.setResult(userPageList.getResult());
        userPageResult.setPageIndex(pageIndex);
        userPageResult.setPageSize(pageSize);
        userPageResult.setTotalCount(userPageList.getTotal());
        userPageResult.setTotalPages(userPageList.getPages());

        return userPageResult;
    }
}
