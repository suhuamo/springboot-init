package com.example.test.controller;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.suhuamo.init.enums.CodeEnum;
import com.suhuamo.init.common.ResponseResult;
import com.example.test.service.PersonService;
import com.example.test.pojo.Person;
import com.example.test.pojo.dto.person.*;
import com.example.test.pojo.vo.PersonVO;

/**
 *
 * @author suhuamo
 * @date 2024-03-19
 */
@Slf4j
@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
       * 新增 person
       * @param personAddDTO 新增数据实体类
       * @return ResponseResult<Person>
       */
    @PostMapping
    public ResponseResult<PersonVO> addPerson(@RequestBody PersonAddDTO personAddDTO) {
        // 1. 校验数据
        personService.validAddData(personAddDTO);
        // 2. 插入数据
        Person person = personService.save(personAddDTO);
        // 3. 转换为vo返回给前端
        return ResponseResult.ok(personService.poToVo(person));
    }

    /**
     * 根据 id 删除 person
     * @param id 主键
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("/{id}")
    public ResponseResult<Boolean> deletePersonById(@PathVariable("id") Integer id) {
        // 1. 校验 id 是否存在
        if(!personService.idExists(id)) {
            return ResponseResult.error(CodeEnum.NOT_FOUND_ERROR);
        }
        // 2. 删除数据
        personService.removeById(id);
        return ResponseResult.ok(true);
    }

    /**
     * 根据 id 修改 person
     * @param personUpdateDTO 修改数据实体类
     * @return ResponseResult<Person>
     */
    @PutMapping("/{id}")
    public ResponseResult<PersonVO> updatePersonById(@RequestBody PersonUpdateDTO personUpdateDTO) {
        // 1. 校验数据
        personService.validUpdateData(personUpdateDTO);
        // 2. 更新数据库
        Person person = personService.update(personUpdateDTO);
        // 3. 返回vo给前端
        return ResponseResult.ok(personService.poToVo(person));
    }

    /**
     * 分页查询 person, 由传入参数限制条件
     * @param personQueryDTO 分页查询条件
     * @return ResponseResult<List<PersonVO>>
     */
    @GetMapping
    public ResponseResult<Page<PersonVO>> getPersonByPage(PersonQueryDTO personQueryDTO) {
        // 1. 校验数据
        personService.validQueryData(personQueryDTO);
        // 2. 进行查询
        Page<Person> personPage = personService.page(personQueryDTO);
        // 3. 返回vo给前端
        return ResponseResult.ok(personService.pageToVo(personPage));
    }

    /**
     * 查询所有 person
     * @return ResponseResult<List<PersonVO>>
     */
    @GetMapping("/list")
    public ResponseResult<Page<PersonVO>> getPersonList() {
        // 1. 查询数据
        List<Person> personList = personService.list();
        // 2. 返回vo给前端
        return ResponseResult.ok(personService.poToVo(personList));
    }

    /**
     * 根据 id 查询 person
     * @param id 主键
     * @return ResponseResult<Person>
     */
    @GetMapping("/{id}")
    public ResponseResult<PersonVO> getPersonById(@PathVariable("id") Integer id) {
        // 1. 校验数据
        if(!personService.idExists(id)) {
            return ResponseResult.error(CodeEnum.NOT_FOUND_ERROR);
        }
        // 2. 查询数据
        Person person = personService.getById(id);
        // 3. 返回vo给前端
        return ResponseResult.ok(personService.poToVo(person));
    }
}
