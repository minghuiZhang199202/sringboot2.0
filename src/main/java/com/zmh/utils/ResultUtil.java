package com.zmh.utils;

import com.girl.enums.ResultEnum;
import com.girl.model.Result;

/**
 * <p>@author minghuiZhang.</p>
 * <p>description：</p>
 * <p>date: created in 14:48 2017/12/13</p>
 * <p>modified By: </p>
 */
public class ResultUtil {
    private static final Integer SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "成功";
    private static final Integer FAILURE_CODE = 1;

    public static Result success(Object object){
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMessage(SUCCESS_MESSAGE);
        result.setData(object);
        return result;
    }
    public static Result success(){
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMessage(SUCCESS_MESSAGE);
        return result;
    }

    public static Result failure(Integer code,String message){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    public static Result failure(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMsg());
        return result;
    }
}
