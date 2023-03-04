package com.teoan.walletdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Teoan
 * @description 余额操作结果DTO
 * @date 2023/3/2 17:24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HandelResultDTO {
    Integer code;
    String msg;
}
