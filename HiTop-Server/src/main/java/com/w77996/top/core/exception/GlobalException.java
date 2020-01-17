package com.w77996.top.core.exception;

import com.w77996.top.core.result.ResultCode;
import lombok.Data;

/**
 * @description: 全局错误
 * @author: straw
 **/
@Data
public class GlobalException extends RuntimeException {

    /**
     * 返回状态
     */
    private ResultCode code;

    public GlobalException(ResultCode code) {
        super(code.toString());
        this.code = code;
    }
}
