package com.nhooo.demo.repository;

import com.nhooo.demo.model.UserRecord;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserRecord, String>
{
}