package com.teoan.walletdemo.controller;

import com.teoan.walletdemo.dto.HandelResultDTO;
import com.teoan.walletdemo.dto.HandelWalletDTO;
import com.teoan.walletdemo.dto.WalletLogDTO;
import com.teoan.walletdemo.service.UserService;
import com.teoan.walletdemo.service.WalletLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Teoan
 * @description 钱包demoController
 * @date 2023/3/2 10:26
 */
@RestController
@Api(tags = "钱包业务API")
public class WalletController {


    @Resource
    UserService userService;


    @Resource
    WalletLogService walletLogService;

    /**
     * 获取用户余额
     * @param id 用户id
     * @return 用户余额
     */
    @ApiOperation("获取用户余额")
    @GetMapping("/user/balance/{id}")
    public Integer getUserBalance(@ApiParam(name = "id",value = "用户ID") @PathVariable("id")Integer id){
        return userService.getUserBalance(id);
    }


    @ApiOperation("用户钱包操作")
    @PostMapping("/user/wallet")
    public HandelResultDTO handelWallet(@ApiParam(name = "handelWalletDTO",value = "用户钱包操作类") @RequestBody HandelWalletDTO handelWalletDTO){
        return userService.handelWallet(handelWalletDTO);
    }


    @ApiOperation("查询用户钱包操作")
    @GetMapping("/user/wallet/log/{userId}")
    public List<WalletLogDTO> handelWallet(@ApiParam(name = "userId",value = "用户ID") @PathVariable("userId")Integer userId){
        return walletLogService.getUserWalletLog(userId);
    }

}
