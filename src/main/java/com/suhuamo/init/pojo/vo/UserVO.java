package com.suhuamo.init.pojo.vo;import com.fasterxml.jackson.annotation.JsonFormat;import lombok.Data;import lombok.NoArgsConstructor;import lombok.experimental.Accessors;import java.io.Serializable;import java.time.LocalDateTime;/*** <p>* 用户表 后端->前端数据显示类* </p>** @author suhuamo* @date 2024-03-19*/@Data@NoArgsConstructor@Accessors(chain = true)public class UserVO implements Serializable {    private static final long serialVersionUID = 1L;    /**     * 主键     */    private Integer id;    /**     * 账号     */    private String name;    /**     * 密码     */    private String password;    /**     * 头像地址     */    private String avatar;    /**     * 邮箱     */    private String email;    /**     * 电话号码     */    private String telephone;    /**     * 类型，0-男；1-女     * @with {@link com.suhuamo.init.enums.SexEnum}     */    private Byte sex;    /**     * 类型     */    private Byte type;    /**     * 创建时间     */    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")    private LocalDateTime createTime;    /**     * 更新时间     */    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")    private LocalDateTime updateTime;}