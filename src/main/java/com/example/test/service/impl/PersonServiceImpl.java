package com.example.test.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.test.mapper.PersonMapper;
import com.example.test.pojo.Person;
import com.example.test.pojo.dto.person.PersonAddDTO;
import com.example.test.pojo.dto.person.PersonQueryDTO;
import com.example.test.pojo.dto.person.PersonUpdateDTO;
import com.example.test.pojo.vo.PersonVO;
import com.example.test.service.PersonService;
import com.suhuamo.init.constant.CommonConstant;
import com.suhuamo.init.enums.CodeEnum;
import com.suhuamo.init.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * <p>
 *  业务实现类
 * </p>
 *
 * @author suhuamo
 * @date 2024-03-19
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    @Autowired
    PersonMapper personMapper;


    @Override
    public void validAddData(PersonAddDTO personAddDTO) {
        // 1. 如姓名不能长度不能小于20，可使用注解校验
    }

    @Override
    public void validUpdateData(PersonUpdateDTO personUpdateDTO) {
        // 1. 如id不能为空，可以使用注解校验
        if(personUpdateDTO.getId() == null) {
            throw new CustomException(CodeEnum.PARAM_ERROR);
        }
        // 2. 其他校验
    }

    @Override
    public void validQueryData(PersonQueryDTO personQueryDTO) {
        long pageSize = personQueryDTO.getPageSize();
        // 1. 每次查询数量不可大于限定数量，可使用注解校验
        if(pageSize >= CommonConstant.PAGE_DATA_MAX_LIMIT) {
             personQueryDTO.setPageSize(CommonConstant.PAGE_DATA_MAX_LIMIT);
        }
        // 2. 其他校验
    }


    @Override
    public Boolean idExists(Integer id) {
        // 1. 查询数据
        Person person = personMapper.selectById(id);
        // 2. 返回结果
        if(person == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Person save(PersonAddDTO personAddDTO) {
        // 1. 转换为实体Pojo
        Person person = addDtoToPo(personAddDTO);
        // 2. 入库
        personMapper.insert(person);
        // 3. 返回入库的数据
        return person;
    }

    @Override
    public Person update(PersonUpdateDTO personUpdateDTO) {
        // 1. 转换为实体Pojo
        Person person = updateDtoToPo(personUpdateDTO);
        // 2. 入库
        personMapper.updateById(person);
        // 3. 返回入库的数据
        return person;
    }

    @Override
    public Page<Person> page(PersonQueryDTO personQueryDTO) {
        // 1.创建分页对象
        Page<Person> personPage = new Page<Person>(personQueryDTO.getPageNum(), personQueryDTO.getPageSize());
        // 2.分页查询
        personPage = personMapper.selectByPage(personPage, personQueryDTO);
        // 3. 返回分页查询到的数据
        return personPage;
    }

    @Override
    public Page<PersonVO> pageToVo(Page<Person> personPage) {
        return (Page<PersonVO>) personPage.convert(this::poToVo);
    }

    /**
     * 将addDto对象转换为po对象--标准化
     * @param personAddDTO
     * @return Person
     */
    public Person addDtoToPo(PersonAddDTO personAddDTO) {
        // 1. 创建实体
        Person person = new Person();
        // 2. 基础参数复制
        BeanUtils.copyProperties(personAddDTO, person);
        // 3. 其他业务参数处理后set
        return person;
    }

    /**
     * 将updateDto对象转换为po对象--标准化
     * @param personUpdateDTO
     * @return Person
     */
    public Person updateDtoToPo(PersonUpdateDTO personUpdateDTO) {
        // 1. 创建实体
        Person person = new Person();
        // 2. 基础参数复制
        BeanUtils.copyProperties(personUpdateDTO, person);
        // 3. 其他业务参数处理后set
        return person;
    }

    @Override
    public PersonVO poToVo(Person person) {
        // 1. 创建实体
        PersonVO personVO = new PersonVO();
        // 2. 基础参数复制
        BeanUtils.copyProperties(person, personVO);
        // 3. 其他业务参数处理后set
        return personVO;
    }


    @Override
    public List<PersonVO> poToVo(List<Person> personList) {
        return personList.stream().map(this::poToVo).collect(Collectors.toList());
    }

}
