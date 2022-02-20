package com.nhooo.demo.aop;


import com.nhooo.demo.model.HistoryRecord;
import com.nhooo.demo.model.UserRecord;
import com.nhooo.demo.repository.HistoryRepository;
import com.nhooo.demo.repository.UserRepository;
import com.nhooo.demo.service.HistoryService;
import com.nhooo.demo.service.UserService;
import com.nhooo.demo.utils.TransactionUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

@Component
@Slf4j
@Aspect
public class TransactionalAop {

    @Autowired
    private TransactionUtils transactionUtils;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private HistoryRepository historyRepository;

    @Around(value = "@annotation(com.nhooo.demo.aop.MyTransactional)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        TransactionStatus begin=null;
        try{
            begin= transactionUtils.begin();

            Object arg = joinPoint.getArgs()[0];



            HistoryRecord target = new HistoryRecord();
            BeanUtils.copyProperties(arg,target);
            System.out.println(arg.toString());
            System.out.println(target.toString());
            historyService.addUser(target);
            UserRecord result = (UserRecord)joinPoint.proceed();
            System.out.println("res:"+result.toString());

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
