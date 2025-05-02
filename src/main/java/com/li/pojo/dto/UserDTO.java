package com.li.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "用户")
public class UserDTO {
    @Schema(description = "账号")
    private String account;
    @Schema(description = "昵称")
    private String nickName;
}
