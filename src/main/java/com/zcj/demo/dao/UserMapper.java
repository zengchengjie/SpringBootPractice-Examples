package com.zcj.demo.dao;

//import com.zcj.demo.core.Mapper;

import com.zcj.demo.core.Mapper;
import com.zcj.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

//import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: zengchengjie
 * @Date: 2018/9/4 17:43
 * @Description:
 */
//@Mapper//声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface UserMapper extends Mapper<User> {
    User selectUserByName(String name);

    @Override
    int updateByPrimaryKey(User record);

    List<String> selectTestPageHelperData();
}
