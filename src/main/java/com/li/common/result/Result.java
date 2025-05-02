package com.li.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code; // 状态码 1为成功 0为失败
    private String message;
    private Object data;

    public static Result success() {
        return new Result(1, "success", null);
    }

    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    public static Result fail(String message) {
        return new Result(0, message, null);
    }

    public static Result fail() {
        return new Result(0, "服务异常", null);
    }
}
