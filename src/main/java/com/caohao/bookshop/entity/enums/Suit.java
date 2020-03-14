package com.caohao.bookshop.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 套装枚举
 */
@Getter
public enum Suit {
    YES(1,"是"),NO(2,"否");

    Suit(int code, String descp){
        this.code = code;
        this.descp = descp;
    }

    @EnumValue//标记数据库中存的值
    private final int code;
    private final String descp;
}
