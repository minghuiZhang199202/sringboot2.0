package com.girl.girlexception;

import com.girl.enums.ResultEnum;
import lombok.Data;

/**
 * <p>@author minghuiZhang.</p>
 * <p>description：</p>
 * <p>date: created in 15:19 2017/12/13</p>
 * <p>modified By: </p>
 */
@Data
public class GirlException extends RuntimeException {
    /**
     * 异常代码
     */
    private Integer code;

    public GirlException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
