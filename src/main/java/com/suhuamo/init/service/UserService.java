package com.suhuamo.init.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suhuamo.init.pojo.User;
import com.suhuamo.init.pojo.dto.user.LoginUserDTO;
import com.suhuamo.init.pojo.dto.user.UpdatePasswordDTO;
import com.suhuamo.init.pojo.dto.user.UserAddDTO;
import com.suhuamo.init.pojo.dto.user.UserQueryDTO;
import com.suhuamo.init.pojo.dto.user.UserUpdateDTO;
import com.suhuamo.init.pojo.vo.UserVO;

import java.util.List;

/**
 * <p>
 * 用户表 业务接口类
 * </p>
 *
 * @author suhuamo
 * @date 2024-03-19
 */
public interface UserService extends IService<User>{

    /**
     * 校验添加数据的参数
     * @param userAddDTO
     * @return void
     */
    void validAddData(UserAddDTO userAddDTO);

    /**
     * 校验更新数据的参数
     * @param userUpdateDTO
     * @return void
     */
    void validUpdateData(UserUpdateDTO userUpdateDTO);

    /**
     * 校验查询数据的参数
     * @param userQueryDTO
     * @return void
     */
    void validQueryData(UserQueryDTO userQueryDTO);

    /**
     * 校验更新密码的参数
     * @param updatePasswordDTO
     * @return void
     */
    void validUpdatePassword(UpdatePasswordDTO updatePasswordDTO);

    /**
     * 校验指定id的数据是否存在
     * @param id
     * @return Boolean
     */
    Boolean idExists(Integer id);

    /**
     *  获取当前登录用户
     * @param
     * @return User
     */
    User getNow();

    /**
     *  判断用户输入的账号密码是否正确，如果正确，则返回该用户的信息
     * @param loginUserDTO
     * @return User
     */
    User login(LoginUserDTO loginUserDTO);

    /**
     *  更新用户密码
     * @param updatePasswordDTO
     * @return void
     */
    void updatePassword(UpdatePasswordDTO updatePasswordDTO);

    /**
     * 新增 userAddDTO
     * @param userAddDTO
     * @return User
     */
    User save(UserAddDTO userAddDTO);

    /**
     * 修改 userAddDTO
     * @param userUpdateDTO
     * @return User
     */
    User update(UserUpdateDTO userUpdateDTO);

    /**
     * 分页查询 user
     * @param userQueryDTO
     * @return User
     */
    Page<User> page(UserQueryDTO userQueryDTO);

    /**
     * 将po对转换为vo对象--可视化
     * @param user
     * @return UserVO
     */
    UserVO poToVo(User user);

    /**
     * 将po对转换为vo对象--可视化
     * @param userList
     * @return List<UserVO>
     */
    List<UserVO> poToVo(List<User> userList);

    /**
     * 将分页的数据vo化
     * @param userPage
     * @return Page<UserVO>
     */
    Page<UserVO> pageToVo(Page<User> userPage);
}
