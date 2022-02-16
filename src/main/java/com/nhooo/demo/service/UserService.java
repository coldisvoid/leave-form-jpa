package com.nhooo.demo.service;
import java.util.List;
import java.util.ArrayList;

import com.nhooo.demo.model.UserRecord;
import com.nhooo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    //获取所有
    public List<UserRecord> getAllUsers()
    {
        List<UserRecord>userRecords = new ArrayList<>();
        userRepository.findAll().forEach(userRecords::add);
        return userRecords;
    }
    //增加
    public void addUser(UserRecord userRecord)
    {
        userRepository.save(userRecord);
    }
    //
    public void deleteUser(int id)
    {
        userRepository.deleteById(id);

    }
    //
    public void updateUser(UserRecord userRecord)
    {

    }
}