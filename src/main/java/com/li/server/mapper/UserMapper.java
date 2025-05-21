package com.li.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select id, account, password, nick_name, time from user where account = #{account}")
    User selectByAccount(String account);
}
