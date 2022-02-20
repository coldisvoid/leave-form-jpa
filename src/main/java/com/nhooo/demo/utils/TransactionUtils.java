package com.nhooo.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.annotation.Resource;

@Component
public class TransactionUtils {
    @Autowired
    private PlatformTransactionManager jpaTransactionManager;

    public TransactionStatus begin(){
        TransactionStatus transactionStatus= jpaTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return  transactionStatus;
    }

    public void commit(TransactionStatus transaction){
        jpaTransactionManager.commit(transaction);

    }

    public  void rollback(TransactionStatus transaction){
        jpaTransactionManager.rollback(transaction);
    }
}
