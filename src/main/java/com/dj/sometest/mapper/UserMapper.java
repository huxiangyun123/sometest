package com.dj.sometest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.sometest.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * @author Chris
 * @date 2020-09-25 18:23:35
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
