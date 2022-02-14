package com.nhooo.demo.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserRecord
{
    @Id
    int id;
    String name;
    String email;

}