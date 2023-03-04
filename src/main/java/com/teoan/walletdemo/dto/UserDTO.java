package com.teoan.walletdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Teoan
 * @description 用户DO类
 * @date 2023/3/2 10:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户DTO类")
public class UserDTO {


    /**
     * 用户唯一标识ID
     */
    @ApiModelProperty(name = "用户标识")
    private Integer id ;

    /**
     * 用户名称
     */
    @ApiModelProperty(name = "用户名称")
    private String name;

    /**
     * 用户余额
     */
    @ApiModelProperty(name = "用户余额")
    private Integer balance;


    /**
     * 省略其他用户相关的字段
     */

}
