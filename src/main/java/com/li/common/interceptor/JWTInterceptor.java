package com.li.common.interceptor;

import com.li.common.exception.UserException;
import com.li.common.properties.JWTProperties;
import com.li.common.utils.JWTUtil;
import com.li.common.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@RequiredArgsConstructor
public class JWTInterceptor implements HandlerInterceptor {

    private final ThreadLocalUtil threadLocalUtil;
    private final JWTProperties jwtProperties;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            log.info("拦截到的不是动态方法，直接放行");
            return true;
        }
        // 获取请求头中的token
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            log.info("请求头中没有token");
            throw new UserException();
        }
        token = token.replace("Bearer ", "");
        threadLocalUtil.set(token); // 将token放入ThreadLocal中
        // JWT校验
        try {
            JWTUtil.parseJWT(jwtProperties.getSecretKey(), token);
        } catch (Exception e) {
            log.info("JWT校验失败");
            throw new UserException();
        }
        return true;
    }
}
