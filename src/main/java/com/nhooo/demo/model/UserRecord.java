package com.nhooo.demo.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class UserRecord
{
    @GeneratedValue(strategy = GenerationType.AUTO)   //生成策略，这里配置为自增
    @Id
    int id;
    String name;
    String department;
    String workLocation;
    String memberType;
    Date hireDate;
    Date writeDate;
    String leaveType;
    String job;
    Date birthDate;
    Date graduation;
    String reason;
    Date leaveStart;
    Date leaveEnd;
    Date returnToWork;



}