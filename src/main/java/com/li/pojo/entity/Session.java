package com.li.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "会话")
public class Session {
    @Schema(description = "会话id")
    private ObjectId id;
    @Schema(description = "会话名称")
    private String name;
    @Schema(description = "用户id")
    private Long userId;
}
