package com.zmh.handler;

import com.girl.enums.ResultEnum;
import com.girl.girlexception.GirlException;
import com.girl.model.Result;
import com.zmh.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>@author minghuiZhang.</p>
 * <p>description：</p>
 * <p>date: created in 15:10 2017/12/13</p>
 * <p>modified By: </p>
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof GirlException){
            GirlException e1 = (GirlException) e;
            logger.error("[girlException]:{}",e1);
            return ResultUtil.failure(e1.getCode(),e1.getMessage());
        }
        logger.error("[系统异常]:{}",e);
        return ResultUtil.failure(ResultEnum.UNKNOW_ERROR);
    }
}
