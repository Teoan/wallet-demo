package com.teoan.walletdemo.service.Impl;

import com.teoan.walletdemo.aspect.WalletLog;
import com.teoan.walletdemo.constant.RedissonConstant;
import com.teoan.walletdemo.domain.UserDO;
import com.teoan.walletdemo.dto.HandelResultDTO;
import com.teoan.walletdemo.dto.HandelWalletDTO;
import com.teoan.walletdemo.enums.HandelEnum;
import com.teoan.walletdemo.enums.HandelStatusEnum;
import com.teoan.walletdemo.repository.UserRepository;
import com.teoan.walletdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Teoan
 * @description 用户服务实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    RedissonClient redissonClient;

    HashMap<HandelEnum, Function<HandelWalletDTO, HandelResultDTO>> handelMap = new HashMap<>(8);


    @PostConstruct
    public void init() {
        //初始化处理映射MAP 后续可根据需求添加处理类型
        handelMap.put(HandelEnum.PAYMENT, this::handelPayment);
        handelMap.put(HandelEnum.REFUND, this::handelRefund);
    }


    @Override
    @Transactional
    public Integer getUserBalance(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("未找到对应用户，请核实，id[" + id + "]")).getBalance();
    }


    @Override
    @WalletLog(title = "钱包余额处理")
    public HandelResultDTO handelWallet(HandelWalletDTO handelWalletDTO) {
        return handelMap.get(handelWalletDTO.getHandelEnum()).apply(handelWalletDTO);
    }

    /**
     * 处理退款
     *
     * @param handelWalletDTO 操作对象
     * @return 处理结果
     */
    private HandelResultDTO handelRefund(HandelWalletDTO handelWalletDTO) {
        Optional<UserDO> optional = userRepository.findById(handelWalletDTO.getUserId());
        if(optional.isEmpty()){
            log.warn("[wallet][退款处理]未找到对应用户，请核实，id[" + handelWalletDTO.getUserId() + "]");
            return HandelResultDTO.builder().code(HandelStatusEnum.FAIL.getCode()).msg("未找到对应用户，请核实，id[" + handelWalletDTO.getUserId() + "]").build();
        }
        UserDO userDO = optional.get();

        userDO.setBalance(userDO.getBalance() + handelWalletDTO.getAmount());
        // 敏感操作需上锁保证原子性，分布式应用可以使用分布式锁，单体应用的话，用jdk自带的锁即可
        RLock walletLock = redissonClient.getLock(RedissonConstant.PREFIX + handelWalletDTO.getUserId().toString());
        walletLock.lock(2, TimeUnit.SECONDS);
        userRepository.save(userDO);
        walletLock.unlock();
        log.info("[wallet][退款处理]用户[{}],处理退款成功，退款金额[{}],剩余余额[{}]", userDO.getName(), handelWalletDTO.getAmount(), userDO.getBalance());
        return HandelResultDTO.builder().code(HandelStatusEnum.SUCCESS.getCode()).msg("退款处理成功！").build();
    }


    /**
     * 处理付款
     *
     * @param handelWalletDTO 操作对象
     * @return 处理结果
     */

    private HandelResultDTO handelPayment(HandelWalletDTO handelWalletDTO) {
        Optional<UserDO> optional = userRepository.findById(handelWalletDTO.getUserId());
        if(optional.isEmpty()){
            log.warn("[wallet][付款处理]未找到对应用户，请核实，id[" + handelWalletDTO.getUserId() + "]");
            return HandelResultDTO.builder().code(HandelStatusEnum.FAIL.getCode()).msg("未找到对应用户，请核实，id[" + handelWalletDTO.getUserId() + "]").build();
        }
        UserDO userDO = optional.get();
        // 余额不足的情况
        if (userDO.getBalance() < handelWalletDTO.getAmount()){
            log.warn("[wallet][付款处理]用户[{}],付款处理失败，付款金额[{}],剩余余额[{}]", userDO.getName(), handelWalletDTO.getAmount(), userDO.getBalance());
            return HandelResultDTO.builder().code(HandelStatusEnum.FAIL.getCode()).msg("付款处理失败！余额不足，当前余额为【"+userDO.getBalance()+"】").build();
        }
        userDO.setBalance(userDO.getBalance() - handelWalletDTO.getAmount());
        // 敏感操作需上锁保证原子性，分布式应用可以使用分布式锁，单体应用的话，用jdk自带的锁即可
        RLock walletLock = redissonClient.getLock(RedissonConstant.PREFIX + handelWalletDTO.getUserId().toString());
        walletLock.lock(2, TimeUnit.SECONDS);
        userRepository.save(userDO);
        walletLock.unlock();
        log.info("[wallet][付款处理]用户[{}],付款处理成功，付款金额[{}],剩余余额[{}]", userDO.getName(), handelWalletDTO.getAmount(), userDO.getBalance());
        return HandelResultDTO.builder().code(HandelStatusEnum.SUCCESS.getCode()).msg("付款处理成功！").build();
    }

}
