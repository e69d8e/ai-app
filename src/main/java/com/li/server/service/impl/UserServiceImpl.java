package com.li.server.service.impl;

import com.li.common.properties.JWTProperties;
import com.li.common.result.Result;
import com.li.common.utils.JWTUtil;
import com.li.common.utils.PasswordUtil;
import com.li.common.utils.SecureRandomStringUtil;
import com.li.common.utils.ThreadLocalUtil;
import com.li.server.mapper.UserMapper;
import com.li.pojo.dto.LoginDTO;
import com.li.pojo.dto.RegisterDTO;
import com.li.pojo.dto.UpdatePasswordDTO;
import com.li.pojo.dto.UserDTO;
import com.li.pojo.entity.User;
import com.li.pojo.vo.TokenVO;
import com.li.server.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final JWTProperties jwtProperties;
    private final ThreadLocalUtil threadLocalUtil;

    @Override
    public Result login(LoginDTO loginDTO) {
        User user = userMapper.selectByAccount(loginDTO.getAccount());
        if (user == null) {
            return Result.fail("账号不存在");
        }
        if (!PasswordUtil.checkPassword(loginDTO.getPassword(), user.getPassword())) {
            return Result.fail("密码错误");
        }
        Map<String, Object> claims = new HashMap<>();
        String token = getToken(claims, user);
        return Result.success(TokenVO.builder().token(token).build());
    }


    @Override
    public Result register(RegisterDTO registerDTO) {
        log.info(registerDTO.getAccount());
        if (registerDTO.getAccount() == null || registerDTO.getAccount().isEmpty()
                || registerDTO.getAccount().length() < 6 || registerDTO.getAccount().length() > 18) {
            return Result.fail("账号格式错误");
        }
        if (checkPassword(registerDTO.getPassword())) {
            return Result.fail("密码格式错误");
        }
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            return Result.fail("两次输入密码不一致");
        }
        User user = userMapper.selectByAccount(registerDTO.getAccount());
        if (user != null) {
            return Result.fail("账号已存在");
        }
        User newUser = User.builder()
                .account(registerDTO.getAccount())
                .password(PasswordUtil.encryptPassword(registerDTO.getPassword()))
                .nickName("用户_" + SecureRandomStringUtil.generate(12))
                .time(LocalDateTime.now())
                .build();
        userMapper.insert(newUser);
        // 生成token
        Map<String, Object> claims = new HashMap<>();
        String token = getToken(claims, newUser);
        return Result.success(TokenVO.builder().token(token).build());
    }

    @Override
    public Result updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        if (!updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getConfirmPassword())) {
            return Result.fail("两次输入密码不一致");
        }
        String account = getAccount();
        User user = userMapper.selectByAccount(account);
        if (user != null) {
            if (!PasswordUtil.checkPassword(updatePasswordDTO.getPassword(), user.getPassword())) {
                return Result.fail("原密码错误");
            }
            if (checkPassword((updatePasswordDTO.getNewPassword()))) {
                return Result.fail("密码格式错误");
            }
            user.setPassword(PasswordUtil.encryptPassword(updatePasswordDTO.getNewPassword()));
            userMapper.updateById(user);
            return Result.success();
        }
        return Result.fail("账号不存在");
    }

    @Override
    public Result update(UserDTO userDTO) {
        String account = getAccount();
        User user = userMapper.selectByAccount(account);
        User newUser = User.builder().
                id(user.getId()).
                account(userDTO.getAccount() != null ? userDTO.getAccount() : user.getAccount()).
                nickName(userDTO.getNickName() != null ? userDTO.getNickName() : user.getNickName()).
                build();
        userMapper.updateById(newUser);
        // 生成token
        Map<String, Object> claims = new HashMap<>();
        String token = getToken(claims, newUser);
        return Result.success(TokenVO.builder().token(token).build());
    }

    // 检查密码格式
    private boolean checkPassword(String password) {
        return password == null || password.length() < 6 || password.length() > 18;
    }

    // 获取账号
    private String getAccount() {
        Claims claims = JWTUtil.parseJWT(jwtProperties.getSecretKey(), threadLocalUtil.get().toString());
        return claims.get("account").toString();
    }
    @NotNull
    private String getToken(Map<String, Object> claims, User user) {
        claims.put("account", user.getAccount());
        claims.put("nickName", user.getNickName());
        return jwtProperties.getTokenHead() + JWTUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtlMillis(), claims);
    }
}
