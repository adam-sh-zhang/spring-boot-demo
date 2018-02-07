package com.test.demo.controller;

import com.test.demo.model.PageResult;
import com.test.demo.model.ResponseEntity;
import com.test.demo.model.User;
import com.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Adam.Zhang
 * @date 2018/1/23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser);
    }

    @GetMapping("/list")
    public ResponseEntity<PageResult<User>> getUserList(
            @RequestParam(value = "page_index", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "page_size", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "order_by", required = false, defaultValue = "username asc") String orderBy,
            @RequestParam(value = "keyword", required = false) String keyword) {
        PageResult<User> userPageResult = userService.getUserList(pageIndex, pageSize, orderBy, keyword);
        return new ResponseEntity<>(userPageResult);
    }

}
