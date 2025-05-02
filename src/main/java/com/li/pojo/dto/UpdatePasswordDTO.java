package com.li.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "修改密码")
public class UpdatePasswordDTO {
    @Schema(description = "原密码")
    private String password;
    @Schema(description = "新密码")
    private String newPassword;
    @Schema(description = "确认密码")
    private String confirmPassword;
}
