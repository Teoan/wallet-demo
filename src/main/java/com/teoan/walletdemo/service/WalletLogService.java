package com.teoan.walletdemo.service;


import com.teoan.walletdemo.dto.WalletLogDTO;

import java.util.List;

/**
 * @author Teoan
 * @description 日志服务类
 */
public interface WalletLogService {

    /**
     * 根据用户获取操作
     * @param userId 用户id
     * @return 操作消息列表
     */
    List<WalletLogDTO> getUserWalletLog(Integer userId);



}
