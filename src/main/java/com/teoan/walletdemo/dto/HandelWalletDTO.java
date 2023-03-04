package com.teoan.walletdemo.dto;

import com.teoan.walletdemo.enums.HandelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Teoan
 * @description 钱包操作类
 * @date 2023/3/2 17:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("钱包操作DTO类")
public class HandelWalletDTO {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(name = "用户标识")
    public Integer userId;


    /**
     * 操作类型
     */
    @NotNull(message = "操作类型不能为空")
    @ApiModelProperty(name = "操作类型")
    public HandelEnum handelEnum;

    /**
     * 操作金额
     */
    @NotNull(message = "操作金额不能为空")
    @ApiModelProperty(name = "操作金额")
    public Integer amount;


}
