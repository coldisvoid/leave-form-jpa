package com.nhooo.demo.controller;
import com.nhooo.demo.model.UserRecord;
import com.nhooo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class UserController
{
    @Autowired
    private UserService userService;
    //获取所有请假单
    @RequestMapping("/")
    public List<UserRecord> getAllUser()
    {
        return userService.getAllUsers();
    }
    //增加一个请假单
    @RequestMapping(value="/add-user", method=RequestMethod.POST)
    public void addUser(@RequestBody UserRecord userRecord)
    {
        userService.addUser(userRecord);
    }
    //删除一个请假单
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public void deleteUser(@RequestBody UserRecord userRecord)
    {
        userService.deleteUser(userRecord.getId());
    }

    //修改一个请假单
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public void updateUser(@RequestBody UserRecord userRecord)
    {
        userService.addUser(userRecord);
    }
}