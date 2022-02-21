package com.nhooo.demo.service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.nhooo.demo.aop.MyTransactional;
import com.nhooo.demo.model.UserRecord;
import com.nhooo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    //获取所有
    public List<UserRecord> getAllUsers()
    {
        List<UserRecord>userRecords = new ArrayList<>();

        userRepository.findAll(Sort.by(Sort.Direction.ASC,"id")).forEach(userRecords::add);
        return userRecords;
    }
    //增加
    @MyTransactional
    public UserRecord addUser(UserRecord userRecord)
    {
        userRecord.setDeleted(false);
        return userRepository.save(userRecord);
    }
    //
    public void setDeletedFlag(int id)
    {

        Optional<UserRecord> userRecord=userRepository.findById(id);
        UserRecord a= userRecord.get();
        a.setDeleted(true);
        userRepository.save(a);
    }
    //
    public void deleteUser(int id)
    {

        userRepository.deleteById(id);

    }
    //
    @MyTransactional
    public void updateUser(UserRecord userRecord)
    {
        userRepository.save(userRecord);
    }
    //
    public Page<UserRecord> getThePage(Integer page,Integer size){
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,"id"));
        Page<UserRecord> all = userRepository.findAll(pageRequest);
        return all;
    }
    //
    public Page<UserRecord> getThePageOfSearchByName(Integer page,Integer size,String name){
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,"id"));
        Page<UserRecord> all = userRepository.findByNameLike(name,pageRequest);
        return all;
    }
    public Page<UserRecord> getThePageOfSearchByNameOrLeaveType(
            Integer page,Integer size,String name,String leaveType){
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,"id"));
        Page<UserRecord> all=null;
        if(name.equals("")){
            if(leaveType.equals("")){
                all = userRepository.findByDeleted(false,pageRequest);
            }else{
                all=userRepository.findByLeaveTypeAndDeleted(leaveType,false,pageRequest);
            }
        }else{
            if(leaveType.equals("")){
                all = userRepository.findByNameLikeAndDeleted("%"+name+"%",false,pageRequest);
            }else{
                all=userRepository.findByNameLikeAndLeaveTypeAndDeleted("%"+name+"%",leaveType,false,pageRequest);
            }
        }
        return all;
    }
//    public Page<UserRecord> getThePageOfSearchByNameOrLeaveType(
//            Integer page,Integer size,String name,String leaveType){
//        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,"id"));
//        Page<UserRecord> all=null;
//        if(name.equals("")){
//            if(leaveType.equals("")){
//                all = userRepository.findAll(pageRequest);
//            }else{
//                all=userRepository.findByLeaveType(leaveType,pageRequest);
//            }
//        }else{
//            if(leaveType.equals("")){
//                all = userRepository.findByNameLike("%"+name+"%",pageRequest);
//            }else{
//                all=userRepository.findByNameLikeAndLeaveType("%"+name+"%",leaveType,pageRequest);
//            }
//        }
//        return all;
//    }
}