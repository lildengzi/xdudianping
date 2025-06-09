package com.example.sens.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author saysky
 * @since 2024/2/18 20:15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("biz_note_comment")
public class NoteComment extends Model<NoteComment> implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 笔记id
     */
    private Long noteId;

    /**
     * 用户id
     */
    private Long userId;


    /**
     * 评论内容
     */
    private String content;

    /**
     * 父评论id
     */
    private Long pid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 子评论
     */
    @TableField(exist = false)
    private List<NoteComment> childCommentList;

    /**
     * 用户
     */
    @TableField(exist = false)
    private User user;

    /**
     * 笔记
     */
    @TableField(exist = false)
    private Note note;
}
