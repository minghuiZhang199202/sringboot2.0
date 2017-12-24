package com.girl.model;

import lombok.Data;

/**
 * <p>@author minghuiZhang.</p>
 * <p>description：http请求返回最外层结果</p>
 * <p>date: created in 14:33 2017/12/13</p>
 * <p>modified By: </p>
 */
@Data
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;

    /**
     * 具体的内容
     */
    private T data;

}
