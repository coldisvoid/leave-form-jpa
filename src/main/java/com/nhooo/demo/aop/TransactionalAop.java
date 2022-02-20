package com.nhooo.demo.aop;


import com.nhooo.demo.utils.TransactionUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

@Component
@Slf4j
@Aspect
public class TransactionalAop {

    @Autowired
    private TransactionUtils transactionUtils;

    @Around(value = "@annotation(com.nhooo.demo.aop.MyTransactional)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        TransactionStatus begin=null;
        try{
            begin= transactionUtils.begin();
            Object result = joinPoint.proceed();
            log.info("commit");
            transactionUtils.commit(begin);
            return result;
        } catch (Throwable throwable){
            throwable.printStackTrace();
            if(begin!=null){
                log.info("rollback");
                transactionUtils.rollback(begin);
            }
            return "错误";
        }

    }
}
