package com.example.sens.common.enums;

/**
 * <pre>
 *     评价状态
 * </pre>
 */
public enum CommentStatusEnum {

    /**
     * 未审核
     */
    NOT_CHECK(0),

    /**
     * 已审核
     */
    HAS_CHECK(1),

    /**
     * 已驳回
     */
    HAS_REJECT(3),

    /**
     * 已回复
     */
    HAS_REPLY(2)


    ;

    private Integer value;

    CommentStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
