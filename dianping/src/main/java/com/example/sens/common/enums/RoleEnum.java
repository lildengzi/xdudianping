package com.example.sens.common.enums;

/**
 * <pre>
 *     角色枚举
 * </pre>
 */
public enum RoleEnum {

    /**
     * 管理员
     */
    ADMIN("admin"),

    /**
     * 商家
     */
    BUSINESS("business"),

    /**
     * 客户
     */
    CUSTOMER("customer")


    ;

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
