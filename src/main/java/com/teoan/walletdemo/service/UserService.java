package com.teoan.walletdemo.service;

import com.teoan.walletdemo.dto.HandelResultDTO;
import com.teoan.walletdemo.dto.HandelWalletDTO;

/**
 * @author Teoan
 * @description 用户服务类
 */

public interface UserService {

    Integer getUserBalance(Integer id);

    HandelResultDTO handelWallet(HandelWalletDTO handelWalletDTO);

}
