package com.test.demo.service;

import com.test.demo.model.PageResult;
import com.test.demo.model.User;

/**
 * Created by Adam.Zhang on 2018/1/23.
 */
public interface UserService {

    User getUserByUsername(String username);

    User createUser(User user);

    PageResult<User> getUserList(int pageIndex, int pageSize, String orderBy, String keyword);

}
