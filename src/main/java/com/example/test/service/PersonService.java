package com.example.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.test.pojo.dto.person.*;
import com.example.test.pojo.Person;
import com.example.test.pojo.vo.PersonVO;

/**
 * <p>
 *  业务接口类
 * </p>
 *
 * @author suhuamo
 * @date 2024-03-19
 */
public interface PersonService extends IService<Person>{


    /**
     * 校验添加数据的参数
     * @param personAddDTO
     * @return void
     */
    void validAddData(PersonAddDTO personAddDTO);

    /**
     * 校验更新数据的参数
     * @param personUpdateDTO
     * @return void
     */
    void validUpdateData(PersonUpdateDTO personUpdateDTO);

    /**
     * 校验查询数据的参数
     * @param personQueryDTO
     * @return void
     */
    void validQueryData(PersonQueryDTO personQueryDTO);

    /**
     * 校验指定id的数据是否存在
     * @param id
     * @return Boolean
     */
    Boolean idExists(Integer id);

    /**
     * 新增 personAddDTO
     * @param personAddDTO
     * @return Person
     */
    Person save(PersonAddDTO personAddDTO);

    /**
     * 修改 personAddDTO
     * @param personUpdateDTO
     * @return Person
     */
    Person update(PersonUpdateDTO personUpdateDTO);

    /**
     * 分页查询
     * @param personQueryDTO
     * @return Person
     */
    Page<Person> page(PersonQueryDTO personQueryDTO);

    /**
     * 将po对转换为vo对象--可视化
     * @param person
     * @return PersonVO
     */
    PersonVO poToVo(Person person);

    /**
     * 将po对转换为vo对象--可视化
     * @param personList
     * @return List<PersonVO>
     */
    List<PersonVO> poToVo(List<Person> personList);

    /**
     * 将分页的数据vo化
     * @param personPage
     * @return Page<PersonVO>
     */
    Page<PersonVO> pageToVo(Page<Person> personPage);

}
