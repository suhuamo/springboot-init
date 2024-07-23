package com.suhuamo.init.interceptor;

import com.suhuamo.init.enums.CodeEnum;
import com.suhuamo.init.exception.CustomException;
import com.suhuamo.init.util.JwtUtil;
import com.suhuamo.init.util.ThreadLocalUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suhuamo
 * @date 2023-12-31
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * jwt鉴权处理
 */
public class JwtInterceptor implements HandlerInterceptor {

    /**
     * 空格字符
     */
    private static final String EMPTY_STRING = " ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是OPTIONS则默认直接通过，使得OPTIONS的下一个请求能够正常调用--实现跨域的关键
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
        // 1.通过request获取请求token信息
        String authorization = request.getHeader(JwtUtil.AUTHORIZATION_TEXT);
        //判断请求头信息是否为空，或者是否已Bearer开头
        if (StringUtils.hasLength(authorization) && authorization.startsWith(JwtUtil.BEARER_TEXT)) {
            //获取token数据
            String token = authorization.replace(JwtUtil.BEARER_TEXT + EMPTY_STRING, "");
            //解析token获取claims
            Claims claims = JwtUtil.parseClaims(token);
            if (claims != null) {
                // 判断当前 token 是否过期
                if(Boolean.TRUE.equals(JwtUtil.isExpired(claims))) {
                    throw new CustomException(CodeEnum.UNAUTHORIZED_EXPIRE);
                }
                // 设置当前登录用户的id
                ThreadLocalUtil.setUserId(Integer.valueOf(claims.getId()));
                return true;
            }
            // 否则抛出未授权信息错误
        } else {
            throw new CustomException(CodeEnum.UNAUTHORIZED_NONE);
        }
        return false;
    }
}
