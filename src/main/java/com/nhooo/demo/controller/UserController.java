package com.nhooo.demo.controller;
import com.nhooo.demo.model.HistoryRecord;
import com.nhooo.demo.model.PageResult;
import com.nhooo.demo.model.UserRecord;
import com.nhooo.demo.repository.UserRepository;
import com.nhooo.demo.service.HistoryService;
import com.nhooo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
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
    private HistoryService historyService;

    //获取所有
    @GetMapping("/history/{uid}")
    public List<HistoryRecord> findHistory(@PathVariable Integer uid){
        return historyService.getAllRecords(uid);
    }

    //根据分页获取请假单
    @GetMapping("/{page}/{size}")
    public PageResult<UserRecord> findAll(@PathVariable Integer page ,@PathVariable Integer size ){
        Page<UserRecord> all = userService.getThePage(page,size);
        //总记录数
        long totalElements;
        totalElements = all.getTotalElements();
        //数据列表
        List<UserRecord> list;
        list = all.getContent();
        //总页数
        int totalPages = all.getTotalPages();
        //返回的数据集
        PageResult<UserRecord> pageResult = new PageResult<>();
        pageResult.setCount(totalElements);
        pageResult.setResult(list);
        pageResult.setPage(totalPages);
        return pageResult;
    }

    //分页搜索
    @GetMapping("/search")
    public PageResult<UserRecord> searchAll(@RequestParam("page") Integer page ,@RequestParam("size") Integer size,
                                            @RequestParam("name") String name,@RequestParam("leaveType") String leaveType ){
        System.out.println("q"+page+size+name+leaveType);
        Page<UserRecord> all=userService.getThePageOfSearchByNameOrLeaveType(page, size, name, leaveType);
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
        userRecord.setDeleted(false);
        userService.addUser(userRecord);
    }
    //删除一个请假单
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id)
    {
        System.out.println("delete");
        userService.setDeletedFlag(id);
        //userService.deleteUser(id);
    }
    //修改一个请假单
    @PutMapping
    public void updateUser(@RequestBody UserRecord userRecord)
    {
        System.out.println("update");
        userService.updateUser(userRecord);
    }
}