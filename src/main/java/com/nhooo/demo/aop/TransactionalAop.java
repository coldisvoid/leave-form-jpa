package com.nhooo.demo.aop;


import com.nhooo.demo.model.HistoryRecord;
import com.nhooo.demo.model.UserRecord;
import com.nhooo.demo.repository.HistoryRepository;
import com.nhooo.demo.service.HistoryService;

import com.nhooo.demo.utils.TransactionUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.sql.Date;
import java.sql.Timestamp;


@Component
@Slf4j
@Aspect
//一个切面
public class TransactionalAop {

    @Autowired
    private TransactionUtils transactionUtils;
    @Autowired
    private HistoryService historyService;
    //环绕增强,用注解指定被增强的方法
    @Around(value = "@annotation(com.nhooo.demo.aop.MyTransactional)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        TransactionStatus begin=null;
        try{
            //开启事务
            begin= transactionUtils.begin();
            //获得方法的参数
            Object arg = joinPoint.getArgs()[0];
            //target保存变更记录
            HistoryRecord target = new HistoryRecord();
            //所以同名属性复制
            BeanUtils.copyProperties(arg,target);
            //被增强方法开始执行
            UserRecord result = (UserRecord)joinPoint.proceed();
            //获得UserRecord Id
            target.setUid(result.getId());
            Timestamp timestamp =  new java.sql.Timestamp(System.currentTimeMillis());
            target.setCreateTime(timestamp);
            historyService.addRecord(target);
            //提交
            log.info("commit");
            transactionUtils.commit(begin);
            return result;
        } catch (Throwable throwable){
            throwable.printStackTrace();
            if(begin!=null){
                //异常回滚
                log.info("rollback");
                transactionUtils.rollback(begin);
            }
            return "错误";
        }

    }
}
