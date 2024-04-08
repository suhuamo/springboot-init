package com.suhuamo.init.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author suhuamo
 * @slogan 今天的早餐是：早苗的面包、秋子的果酱和观铃的果汁~
 * @date 2024-03-20
 * @description 登录的实体类
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LoginUserDTO {
    /**
     * 账号
     */
    @NotEmpty(message = "账号不能为空")
    private String name;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;
}
