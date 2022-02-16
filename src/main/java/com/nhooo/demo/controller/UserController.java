package com.nhooo.demo.controller;
import com.nhooo.demo.model.UserRecord;
import com.nhooo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController
{
    @Autowired
    private UserService userService;
    //获取所有请假单
    @GetMapping
    public List<UserRecord> getAllUser()
    {

        System.out.println("get");
        return userService.getAllUsers();
    }
    //增加一个请假单
    @PostMapping
    public void addUser(@RequestBody UserRecord userRecord)
    {

        System.out.println("post");
        userService.addUser(userRecord);
    }
    //删除一个请假单
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id)
    {
        System.out.println("delete");
        userService.deleteUser(id);
    }
    //修改一个请假单
    @PutMapping
    public void updateUser(@RequestBody UserRecord userRecord)
    {
        System.out.println("update");
        userService.updateUser(userRecord);
    }
}