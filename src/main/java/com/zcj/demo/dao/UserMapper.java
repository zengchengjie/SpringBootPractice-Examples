package com.zcj.demo.dao;

//import com.zcj.demo.core.Mapper;
import com.zcj.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Auther: 10062376
 * @Date: 2018/9/4 17:43
 * @Description:
 */
@Mapper//声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface UserMapper extends com.zcj.demo.core.Mapper<User> {
    User selectUserByName(String name);
}
