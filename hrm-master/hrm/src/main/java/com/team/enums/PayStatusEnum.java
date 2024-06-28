package com.team.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.ss.formula.functions.T;
@Getter
@AllArgsConstructor
public enum PayStatusEnum implements BaseEnum<T> {

    PAY_NO(0, "未支付"),
    PAY_YES(1, "已支付"),
    PAY_FAIl(2, "支付失败");

    @EnumValue
    private final Integer code;
    @JsonValue
    private final String message;
}
