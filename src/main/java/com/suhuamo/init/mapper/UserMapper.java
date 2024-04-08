package com.suhuamo.init.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suhuamo.init.pojo.User;
import com.suhuamo.init.pojo.dto.user.UserQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author suhuamo
 * @date 2024-03-19
 */
@Mapper
public interface UserMapper  extends BaseMapper<User> {

    /**
     *  自定义分页查询
     * @param userPage 分页对象
     * @param userQueryDTO 分页查询条件
     * @return Page<Person>
     */
    Page<User> selectByPage(Page<User> userPage, @Param("user") UserQueryDTO userQueryDTO);
    /**
     * 根据主键id查询对象--忽略逻辑删除
     * @param id 主键id
     * @return User
     */
    User selectRealById(@Param("id") Integer id);
}
