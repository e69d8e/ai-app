package com.li.pojo.entity;

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
@Schema(description = "会话")
public class Session {
    @Schema(description = "记忆id")
    private String id;
    @Schema(description = "名称")
    private String name;
    @Schema(description = "用户id")
    private Long userId;
    @Schema(description = "时间")
    private LocalDateTime time;
}
