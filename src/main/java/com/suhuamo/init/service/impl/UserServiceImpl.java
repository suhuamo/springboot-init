package com.suhuamo.init.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suhuamo.init.enums.CodeEnum;
import com.suhuamo.init.enums.RoleTypeEnum;
import com.suhuamo.init.exception.CustomException;
import com.suhuamo.init.mapper.UserMapper;
import com.suhuamo.init.pojo.User;
import com.suhuamo.init.pojo.dto.user.LoginUserDTO;
import com.suhuamo.init.pojo.dto.user.UpdatePasswordDTO;
import com.suhuamo.init.pojo.dto.user.UserAddDTO;
import com.suhuamo.init.pojo.dto.user.UserQueryDTO;
import com.suhuamo.init.pojo.dto.user.UserUpdateDTO;
import com.suhuamo.init.pojo.vo.UserVO;
import com.suhuamo.init.service.UserService;
import com.suhuamo.init.util.ThreadLocalUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * <p>
 * 用户表 业务实现类
 * </p>
 *
 * @author suhuamo
 * @date 2024-03-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void validAddData(UserAddDTO userAddDTO) {
        // 1. 判断用户名是否存在
        User user = this.getOne(Wrappers.lambdaQuery(User.class).eq(User::getName, userAddDTO.getName()));
        // 存在则报错
        if ((user != null)) {
            throw new CustomException(CodeEnum.PARAM_ERROR, "用户名已存在，请重新输入");
        }

    }

    @Override
    public void validUpdateData(UserUpdateDTO userUpdateDTO) {
        // 1. 如id不能为空，可以使用注解校验
        if(userUpdateDTO.getId() == null) {
            throw new CustomException(CodeEnum.PARAM_ERROR);
        }
        // 2. 判断用户是否存在
        if(!idExists(userUpdateDTO.getId())) {
            throw new CustomException(CodeEnum.PARAM_ERROR, "用户不存在");
        }
    }

    @Override
    public void validQueryData(UserQueryDTO userQueryDTO) {
        // 1. 如每次查询数量不可大于限定数量，可使用注解校验
        /*long pageSize = userQueryDTO.getPageSize();
        if(pageSize >= CommonConstant.PAGE_DATA_MAX_LIMIT) {
             userQueryDTO.setPageSize(CommonConstant.PAGE_DATA_MAX_LIMIT);
        }*/
    }

    @Override
    public void validUpdatePassword(UpdatePasswordDTO updatePasswordDTO) {
        // 1. 获取用户
        User user = userMapper.selectById(updatePasswordDTO.getId());
        // 2. 判断用户是否存在
        if(user == null) {
            throw new CustomException(CodeEnum.PARAM_ERROR, "用户不存在");
        }
        // 3. 判断密码是否正确
        if(!user.getPassword().equals(updatePasswordDTO.getOldPassword())) {
            throw new CustomException(CodeEnum.PARAM_ERROR, "原密码输入错误");
        }
    }

    @Override
    public Boolean idExists(Integer id) {
        // 1. 查询数据
        User user = userMapper.selectById(id);
        // 2. 返回结果
        return user != null;
    }

    @Override
    public User getNow() {
        // 1. 获取用户id
        Integer userId = ThreadLocalUtil.getUserId();
        // 2. 查询用户
        return userMapper.selectRealById(userId);
    }

    @Override
    public User login(LoginUserDTO loginUserDTO) {
        // 1. 根据账号密码查询用户
        return userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getName, loginUserDTO.getName())
                .eq(User::getPassword, loginUserDTO.getPassword()));
    }

    @Override
    public void updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        // 1. 查询用户
        User user = userMapper.selectById(updatePasswordDTO.getId());
        // 2. 设置新密码
        user.setPassword(updatePasswordDTO.getNewPassword());
        // 3. 更新密码
        userMapper.updateById(user);
    }

    @Override
    public User save(UserAddDTO userAddDTO) {
        // 1. 转换为实体Pojo
        User user = addDtoToPo(userAddDTO);
        // 2. 入库
        userMapper.insert(user);
        // 3. 返回入库的数据
        return user;
    }

    @Override
    public User update(UserUpdateDTO userUpdateDTO) {
        // 1. 转换为实体Pojo
        User user = updateDtoToPo(userUpdateDTO);
        // 2. 入库
        userMapper.updateById(user);
        // 3. 返回入库的数据
        return user;
    }

    @Override
    public Page<User> page(UserQueryDTO userQueryDTO) {
        // 1.创建分页对象
        Page<User> userPage = new Page<User>(userQueryDTO.getPageNum(), userQueryDTO.getPageSize());
        // 2.分页查询
        userPage = userMapper.selectByPage(userPage, userQueryDTO);
        // 3. 返回分页查询到的数据
        return userPage;
    }

    /**
     * 将addDto对象转换为po对象--标准化
     * @param userAddDTO
     * @return User
     */
    public User addDtoToPo(UserAddDTO userAddDTO) {
        User user = new User();
        user.setName(userAddDTO.getName());
        user.setPassword(userAddDTO.getPassword());
        user.setAvatar(userAddDTO.getAvatar());
        user.setEmail(userAddDTO.getEmail());
        user.setTelephone(userAddDTO.getTelephone());
        user.setSex(userAddDTO.getSex());
        user.setType(RoleTypeEnum.USER.getType());
        return user;
    }

    /**
     * 将updateDto对象转换为po对象--标准化
     * @param userUpdateDTO
     * @return User
     */
    public User updateDtoToPo(UserUpdateDTO userUpdateDTO) {
        // 1. 创建实体
        User user = new User();
        // 2. 基础参数复制
        BeanUtils.copyProperties(userUpdateDTO, user);
        // 3. 其他业务参数处理后set
        return user;
    }

    @Override
    public UserVO poToVo(User user) {
        // 1. 创建实体
        UserVO userVO = new UserVO();
        // 2. 基础参数复制
        BeanUtils.copyProperties(user, userVO);
        // 3. 其他业务参数处理后set
        return userVO;
    }


    @Override
    public List<UserVO> poToVo(List<User> userList) {
        return userList.stream().map(this::poToVo).collect(Collectors.toList());
    }

    @Override
    public Page<UserVO> pageToVo(Page<User> userPage) {
        return (Page<UserVO>) userPage.convert(this::poToVo);
    }

}
