package com.nhooo.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
public class HistoryRecord
{
    @GeneratedValue(strategy = GenerationType.AUTO)   //生成策略
    @Id
    int id;
    Timestamp createTime;
    Integer uid;
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
    String leaveStartPeriod;
    Date leaveEnd;
    String leaveEndPeriod;
    Integer leaveTotal;

    Date returnToWork;
    String returnPeriod;
    Integer returnTotal;


}