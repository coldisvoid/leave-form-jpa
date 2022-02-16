package com.nhooo.demo.repository;

import com.nhooo.demo.model.UserRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRecord, Integer>
{
}