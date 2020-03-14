package com.caohao.bookshop.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 图书类型枚举类
 */
@Getter
public enum  Category {
    SELECTED(1,"精选图书"),RECOMMENED(1,"推荐图书"),BARGAIN(1,"特价图书");
    Category(int code, String descp){
        this.code = code;
        this.descp = descp;
    }

    @EnumValue//标记数据库中存的值
    private final int code;
    private final String descp;
}
