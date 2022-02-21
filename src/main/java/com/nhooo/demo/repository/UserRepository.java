package com.nhooo.demo.repository;

import com.nhooo.demo.model.UserRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRecord, Integer>
{
    Page<UserRecord> findByName(String name, Pageable pageable);

    Page<UserRecord> findByLeaveType(String leaveType, Pageable pageable);
    Page<UserRecord> findByNameOrLeaveType(String name, String leaveType,Pageable pageable);
    Page<UserRecord> findByNameAndLeaveType(String name, String leaveType,Pageable pageable);

}