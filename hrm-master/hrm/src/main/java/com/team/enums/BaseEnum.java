package com.team.enums;

import java.io.Serializable;

/**
 * 通用枚举接口
 */

public interface BaseEnum<T> extends Serializable {
    /**
     * 获取编号
     * 
     * @return
     */
    Integer getCode();

    /**
     * 消息内容
     * 
     * @return
     */
    String getMessage();
}
