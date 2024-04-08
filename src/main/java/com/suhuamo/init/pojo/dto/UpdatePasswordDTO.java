package com.suhuamo.init.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author suhuamo
 * @slogan 巨人给你鞠躬，是为了让阳光也照射到你。
 * @date 2024-03-21
 * @description
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UpdatePasswordDTO {
    /**
     * 主键
     */
    @NotNull(message = "用户id不能为空")
    private Integer id;
    /**
     * 原密码
     */
    @NotEmpty(message = "原密码不能为空")
    private String oldPassword;
    /**
     * 新密码
     */
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;
}
