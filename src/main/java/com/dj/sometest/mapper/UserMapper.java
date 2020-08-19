package com.dj.sometest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.sometest.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author leifengyang
 * @email lfy@atguigu.com
 * @date 2020-07-29 20:26:06
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
	
}
