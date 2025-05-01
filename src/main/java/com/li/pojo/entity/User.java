package com.li.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户")
public class User {
    @Schema(description = "用户id")
    private Long id;
    @Schema(description = "账号")
    private String account;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "昵称")
    private String nickName;
}
