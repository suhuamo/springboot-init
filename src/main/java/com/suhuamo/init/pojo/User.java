package com.suhuamo.init.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
* 用户表 数据表实体类
* </p>
*
* @author suhuamo
* @date 2024-03-19
*/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 账号
     */
    @TableField(value = "name")
    private String name;
    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;
    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;
    /**
     * 类型，admin-管理员；user-普通用户
     */
    @TableField(value = "type")
    private String type;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 逻辑删除标识（0：未删除；1：已删除）
     */
    @TableField(value = "delete_flag")
    private Boolean deleteFlag;


}
