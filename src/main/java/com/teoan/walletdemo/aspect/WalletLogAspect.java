package com.teoan.walletdemo.aspect;


import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjectUtil;
import com.teoan.walletdemo.domain.WalletLogDO;
import com.teoan.walletdemo.dto.HandelWalletDTO;
import com.teoan.walletdemo.mapstruct.WalletLogMapStruct;
import com.teoan.walletdemo.repository.WalletLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Teoan
 * @description 钱包日志切面类
 */
@Component
@Aspect
@Slf4j
public class WalletLogAspect {

    @Resource
    WalletLogRepository walletLogRepository;

    @Resource
    WalletLogMapStruct mapStruct;




    @Pointcut("@annotation(com.teoan.walletdemo.aspect.WalletLog)")
    public void walletLogPointcut() {
    }



    @Around("walletLogPointcut()")
    public Object aroundHandle(ProceedingJoinPoint joinPoint) throws Throwable{

        Object result = joinPoint.proceed();

        //主体业务函数执行后 保存操作日志 为了不影响接口响应性能异步处理日志保存逻辑
        ThreadUtil.execute(()->{
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            //获取接入点方法
            Method method = methodSignature.getMethod();
            WalletLog walletLog = method.getAnnotation(WalletLog.class);
            // 获取入参
            Object[] args = joinPoint.getArgs();
            HandelWalletDTO handelWalletDTO = null;
            for (Object arg : args) {
                if(arg instanceof HandelWalletDTO){
                    handelWalletDTO = (HandelWalletDTO )arg;
                }
            }
            if(ObjectUtil.isNotEmpty(handelWalletDTO)){
                log.info("[wallet][操作日志]保存操作日志标题[{}],操作类型[{}]",walletLog.title(),
                        handelWalletDTO.getHandelEnum().getMsg());
            }
            WalletLogDO walletLogDO = mapStruct.handelWalletToLog(handelWalletDTO);
            // 操作时间
            walletLogDO.setHandelTime(new Date());
            walletLogRepository.save(walletLogDO);
        });
        return result;
    }
}
