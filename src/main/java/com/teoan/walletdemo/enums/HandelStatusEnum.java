package com.teoan.walletdemo.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Teoan
 * @description 处理结果枚举枚举
 * @date 2022-06-14 15:32
 */
@AllArgsConstructor
@NoArgsConstructor
public enum HandelStatusEnum {

    /**
     * 操作类型
     */
    SUCCESS(0,"正常"),
    FAIL(1,"异常");


    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
