package com.suhuamo.init.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suhuamo.init.common.ResponseResult;
import com.suhuamo.init.enums.CodeEnum;
import com.suhuamo.init.pojo.User;
import com.suhuamo.init.pojo.dto.user.LoginUserDTO;
import com.suhuamo.init.pojo.dto.user.UpdatePasswordDTO;
import com.suhuamo.init.pojo.dto.user.UserAddDTO;
import com.suhuamo.init.pojo.dto.user.UserQueryDTO;
import com.suhuamo.init.pojo.dto.user.UserUpdateDTO;
import com.suhuamo.init.pojo.vo.UserVO;
import com.suhuamo.init.service.UserService;
import com.suhuamo.init.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author suhuamo
 * @date 2024-03-19
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param loginUserDTO
     * @return ResponseResult<String>
     * @version 1.0
     * @author suhuamo
     */
    @PostMapping("/login")
    public ResponseResult<String> login(@Valid @RequestBody LoginUserDTO loginUserDTO) {
        // 1. 根据账号密码查询用户
        User login = userService.login(loginUserDTO);
        // 2. 判断是否输入正确
        if(login == null) {
            return ResponseResult.error("账号或密码输入错误");
        } else {
            // 3. 验证通过，则创建token返回
            String token = JwtUtil.createToken(String.valueOf(login.getId()));
            return ResponseResult.ok(token);
        }
    }

    /**
     * 返回当前登录用户的消息
     * @param
     * @return ResponseResult<UserVO>
     * @version 1.0
     * @author suhuamo
     */
    @GetMapping("/who")
    public ResponseResult<UserVO> who() {
        // 1. 获取当前用户
        User user = userService.getNow();
        // 2. 判断是否已经登录
        if(user == null) {
            return ResponseResult.error(CodeEnum.UNAUTHORIZED_NONE.getCode(), "未登录");
        } else {
            // 3. 返回数据
            return ResponseResult.ok(userService.poToVo(user));
        }
    }

    /**
     * 更新用户密码
     * @param updatePasswordDTO
     * @return ResponseResult<UserVO>
     * @version 1.0
     * @author suhuamo
     */
    @PutMapping("/password")
    public ResponseResult<UserVO> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        // 1. 校验更新密码数据
        userService.validUpdatePassword(updatePasswordDTO);
        // 2. 更新密码
        userService.updatePassword(updatePasswordDTO);
        // 3. 返回数据
        return ResponseResult.ok(userService.poToVo(userService.getById(updatePasswordDTO.getId())));
    }

    /**
     * 新增 user
     * @param userAddDTO 新增数据实体类
     * @return ResponseResult<User>
     */
    @PostMapping("/register")
    public ResponseResult<UserVO> registerUser(@Valid @RequestBody UserAddDTO userAddDTO) {
        // 1. 校验数据
        userService.validAddData(userAddDTO);
        // 2. 插入数据
        User user = userService.save(userAddDTO);
        // 3. 转换为vo返回给前端
        return ResponseResult.ok(userService.poToVo(user));
    }

    /**
       * 新增 user
       * @param userAddDTO 新增数据实体类
       * @return ResponseResult<User>
       */
    @PostMapping
    public ResponseResult<UserVO> addUser(@Valid @RequestBody UserAddDTO userAddDTO) {
        // 1. 校验数据
        userService.validAddData(userAddDTO);
        // 2. 插入数据
        User user = userService.save(userAddDTO);
        // 3. 转换为vo返回给前端
        return ResponseResult.ok(userService.poToVo(user));
    }

    /**
     * 根据 id 删除 user
     * @param id 主键
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("/{id}")
    public ResponseResult<Boolean> deleteUserById(@PathVariable("id") Integer id) {
        // 1. 校验 id 是否存在
        if(Boolean.FALSE.equals(userService.idExists(id))) {
            return ResponseResult.error(CodeEnum.NOT_FOUND_ERROR);
        }
        // 2. 删除数据
        userService.removeById(id);
        return ResponseResult.ok(true);
    }

    /**
     * 根据 id 修改 user
     * @param userUpdateDTO 修改数据实体类
     * @return ResponseResult<User>
     */
    @PutMapping("/{id}")
    public ResponseResult<UserVO> updateUserById(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        // 1. 校验数据
        userService.validUpdateData(userUpdateDTO);
        // 2. 更新数据库
        User user = userService.update(userUpdateDTO);
        // 3. 返回vo给前端
        return ResponseResult.ok(userService.poToVo(user));
    }

    /**
     * 分页查询 user, 由传入参数限制条件
     * @param userQueryDTO 分页查询条件
     * @return ResponseResult<List<UserVO>>
     */
    @GetMapping
    public ResponseResult<Page<UserVO>> getUserByPage(UserQueryDTO userQueryDTO) {
        // 1. 校验数据
        userService.validQueryData(userQueryDTO);
        // 2. 进行查询
        Page<User> userPage = userService.page(userQueryDTO);
        // 3. 返回vo给前端
        return ResponseResult.ok(userService.pageToVo(userPage));
    }

    /**
     * 查询所有 user
     * @return ResponseResult<List<UserVO>>
     */
    @GetMapping("/list")
    public ResponseResult<Page<UserVO>> getUserList() {
        // 1. 查询数据
        List<User> userList = userService.list();
        // 4. 返回vo给前端
        return ResponseResult.ok(userService.poToVo(userList));
    }

    /**
     * 根据 id 查询 user
     * @param id 主键
     * @return ResponseResult<User>
     */
    @GetMapping("/{id}")
    public ResponseResult<UserVO> getUserById(@PathVariable("id") Integer id) {
        // 1. 校验数据
        if(Boolean.FALSE.equals(userService.idExists(id))) {
            return ResponseResult.error(CodeEnum.NOT_FOUND_ERROR);
        }
        // 2. 查询数据
        User user = userService.getById(id);
        // 3. 返回vo给前端
        return ResponseResult.ok(userService.poToVo(user));
    }
}
