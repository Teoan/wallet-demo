package com.teoan.walletdemo.dto;


import com.teoan.walletdemo.enums.HandelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("操作日志DTO")
public class WalletLogDTO {


    /**
     * 用户id
     */
    @ApiModelProperty(name = "用户标识")
    public Integer userId;

    /**
     * 操作类型
     */

    @ApiModelProperty(name = "操作类型")
    public HandelEnum handelEnum;

    /**
     * 操作金额
     */
    @ApiModelProperty(name = "操作金额")
    public Integer amount;

    /**
     * 操作时间
     */
    @ApiModelProperty(name = "操作时间")
    public Date handelTime;

}
