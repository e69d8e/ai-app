package com.li.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.pojo.entity.Session;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper extends BaseMapper<Session> {
}
