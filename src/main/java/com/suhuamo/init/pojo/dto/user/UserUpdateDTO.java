package com.suhuamo.init.pojo.dto.user;import lombok.Data;import lombok.NoArgsConstructor;import lombok.experimental.Accessors;import javax.validation.constraints.NotNull;import java.io.Serializable;/*** <p>* 用户表 后端->前端数据显示类* </p>** @author suhuamo* @date 2024-03-19*/@Data@NoArgsConstructor@Accessors(chain = true)public class UserUpdateDTO implements Serializable {    private static final long serialVersionUID = 1L;    /**     * 主键     */    @NotNull(message = "用户id不能为空")    private Integer id;    /**     * 账号     */    private String name;    /**     * 密码     */    private String password;    /**     * 头像地址     */    private String avatar;    /**     * 邮箱     */    private String email;    /**     * 电话号码     */    private String telephone;}