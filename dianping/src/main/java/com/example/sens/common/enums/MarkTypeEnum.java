package com.example.sens.common.enums;

/**
 * <pre>
 *     1店铺，2菜品
 * </pre>
 */
public enum MarkTypeEnum {

    /**
     * 店铺
     */
    STORE(1),

    /**
     * 菜品
     */
    DISH(2)


    ;

    private Integer value;

    MarkTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
