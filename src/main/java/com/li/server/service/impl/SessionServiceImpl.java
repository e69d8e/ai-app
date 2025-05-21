package com.li.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.bean.Messages;
import com.li.common.properties.JWTProperties;
import com.li.common.result.PageResult;
import com.li.common.result.Result;
import com.li.common.utils.JWTUtil;
import com.li.common.utils.ThreadLocalUtil;
import com.li.server.mapper.SessionMapper;
import com.li.server.mapper.UserMapper;
import com.li.pojo.entity.Session;
import com.li.pojo.entity.User;
import com.li.server.service.SessionService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final ThreadLocalUtil threadLocalUtil;
    private final JWTProperties jwtProperties;
    private final UserMapper  userMapper;
    private final SessionMapper sessionMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public Result create() {
        return Result.success(UUID.randomUUID().toString());
    }

    @Override
    public Result delete(String sessionId) {
        if (sessionMapper.deleteById(sessionId) > 0) {
            return Result.success();
        }
        return Result.fail();
    }

    @Override
    public Result getSessions(Integer page, Integer size) {
        log.info("获取会话");
        // 获取当前用户id
        User user = userMapper.selectByAccount(getAccount());
        // 分页查询会话
        Page<Session> sessionPage = new Page<>(page, size);
        LambdaQueryWrapper<Session> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Session::getUserId, user.getId()); // 条件
        Page<Session> session = sessionMapper.selectPage(sessionPage, queryWrapper);
        return Result.success(new PageResult<>(session.getTotal(), session.getRecords()));
    }

    @Override
    public Result getSession(String sessionId) {
        Criteria criteria = Criteria.where("memoryId").is(sessionId);
        Query query = Query.query(criteria);
        String content = Objects.requireNonNull(mongoTemplate.findOne(query, Messages.class)).getContent();
        return Result.success(content);
    }

    private String getAccount() {
        Claims claims = JWTUtil.parseJWT(jwtProperties.getSecretKey(), threadLocalUtil.get().toString());
        return claims.get("account").toString();
    }
}
