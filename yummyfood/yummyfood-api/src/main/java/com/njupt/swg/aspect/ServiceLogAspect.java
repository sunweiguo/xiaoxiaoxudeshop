package com.njupt.swg.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author swg.
 * @Date 2020/3/22 15:02
 * @CONTACT 317758022@qq.com
 * @DESC 切面类来监听service方法的执行时间
 */
@Component
@Aspect
@Slf4j
public class ServiceLogAspect {

    /**
     * AOP通知
     * 1、前置通知：在方法调用之前通知
     * 2、后置通知：在方法调用之后通知
     * 3、环绕通知：在方法调用之前和之后分别进行通知
     * 4、异常通知：方法调用过程中发生异常则通知
     * 5、最终通知：方法调用之后的最后执行，相当于finally是必定执行的一种
     */

    /**
     * 切面表达式，execution表示索要执行的表达式主体
     * 第一个*表示方法返回类型，*表示所有类型
     * 第二个表示目标方法所在的包
     * 第三处..表示该包和子包下的所有类方法
     * 第四处*表示类名，*表示所有类
     * 第五处*(..) *表示类中的方法名，(..)表示方法中任何参数
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.njupt.swg.service..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("========开始执行{}.{}=======",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName());
        long begin = System.currentTimeMillis();
//        执行目标service
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long taskTime = end - begin;
        if(taskTime > 3000){
            log.error("=======执行结束，耗时：{}毫秒=======",taskTime);
        }else if(taskTime > 2000){
            log.warn("=======执行结束，耗时：{}毫秒=======",taskTime);
        }else{
            log.info("=======执行结束，耗时：{}毫秒=======",taskTime);
        }
        return result;
     }
}
