package com.girl.enums;

import lombok.Data;

/**
 * <p>@author minghuiZhang.</p>
 * <p>description：</p>
 * <p>date: created in 15:36 2017/12/13</p>
 * <p>modified By: </p>
 */
public enum ResultEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    GO_PRIMARY(100,"你可能上小学了吧"),
    GO_JUNIOR_SCHOOL(101,"你可能上初中了吧"),
            ;
    private Integer code;
    private  String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
