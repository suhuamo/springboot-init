package com.example.test.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.test.pojo.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.test.pojo.dto.person.PersonQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author suhuamo
 * @date 2024-03-19
 */
@Mapper
public interface PersonMapper  extends BaseMapper<Person> {

    /**
     *  自定义分页查询
     * @param personPage 分页对象
     * @param personQueryDTO 分页查询条件
     * @return Page<Person>
     */
    Page<Person> selectByPage(Page<Person> personPage, @Param("person") PersonQueryDTO personQueryDTO);
}
