package com.nhooo.demo.controller;
import com.nhooo.demo.model.PageResult;
import com.nhooo.demo.model.UserRecord;
import com.nhooo.demo.repository.UserRepository;
import com.nhooo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController
{
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    //根据分页获取请假单
    @GetMapping("/{page}/{size}")
    public PageResult<UserRecord> findAll(@PathVariable Integer page ,@PathVariable Integer size ){

        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,"id"));
        Page<UserRecord> all = userRepository.findAll(pageRequest);
        //总记录数
        long totalElements = all.getTotalElements();
        //数据列表
        List<UserRecord> list = all.getContent();
        //总页数
        int totalPages = all.getTotalPages();
        //返回的数据集
        PageResult<UserRecord> pageResult = new PageResult<>();
        pageResult.setCount(totalElements);
        pageResult.setResult(list);
        pageResult.setPage(totalPages);
        return pageResult;
    }

//    //获取所有请假单
//    @GetMapping
//    public List<UserRecord> getAllUser()
//    {
//
//        System.out.println("get");
//        return userService.getAllUsers();
//    }
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