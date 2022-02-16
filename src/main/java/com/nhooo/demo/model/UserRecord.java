package com.nhooo.demo.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UserRecord
{
    @GeneratedValue(strategy = GenerationType.AUTO)   //生成策略，这里配置为自增
    @Id
    int id;
    String name;
    String email;


}