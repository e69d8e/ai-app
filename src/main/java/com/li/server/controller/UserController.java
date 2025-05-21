package com.li.server.controller;

import com.li.common.result.Result;
import com.li.pojo.dto.LoginDTO;
import com.li.pojo.dto.RegisterDTO;
import com.li.pojo.dto.UpdatePasswordDTO;
import com.li.pojo.dto.UserDTO;
import com.li.server.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    // 登录
    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    // 注册
    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    // 修改密码
    @Operation(summary = "修改密码")
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        return userService.updatePassword(updatePasswordDTO);
    }

    // 修改昵称
    @Operation(summary = "用户信息")
    @PutMapping
    public Result updateNickName(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }
}
