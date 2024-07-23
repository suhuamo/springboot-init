package com.suhuamo.init.util;

import com.suhuamo.init.enums.CodeEnum;
import com.suhuamo.init.exception.CustomException;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 * @author suhuamo
 * @date 2023-12-31
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * Jwt鉴权工具类
 */
public class JwtUtil {
    /**
     *  签名私钥,默认值 y20160606t
     */
    private static final String key = "yTANGTANG20160606tYUANYUAN";
    /**
     *  签名的有效时间，单位毫秒, 默认60分钟， 1000 * 60 * 60
     */
    private static final Long ttl = 36000000L;
    /**
     *  作者名，默认值 suhuamo
     */
    private static final String author = "suhuamo";

    /**
     * 前端请求头中用于存储token认证的属性名称
     */
    public static final String AUTHORIZATION_TEXT = "Authorization";
    /**
     * 前端请求头中用于存储token认证的属性对应的内容起始内容，属性完整内容为：Bearer {token}
     */
    public static final String BEARER_TEXT = "Bearer";


    /**
     * 设置认证 token
     *    id:登录用户 id
     *   subject：登录用户名
     * @param id
     * @return String
     */
    public static String createToken(String id) {
        //1.创建失效时间
        long now = System.currentTimeMillis();//当前毫秒
        long exp = now + ttl;
        //2.创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id) // 设置id
                .setSubject(author) // 设置作者
                .setIssuedAt(new Date()) // 设置签发时间
                .signWith(SignatureAlgorithm.HS256, key); // 指定签名算法和key值
        // 3.设置失效时间
        jwtBuilder.setExpiration(new Date(exp));
        //4.创建token
        return jwtBuilder.compact();
    }


    /**
     *  解析 token 字符串获取 claims
     * @param token
     * @return Claims
     */
    public static Claims parseClaims(String token) throws CustomException {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key) // 设置解析key
                    .parseClaimsJws(token)// 设置需要解析的token
                    .getBody();
            // 输入错误的token值会出现该异常 || 格式错误异常，例如至少两个 - 符号
        } catch (SignatureException | MalformedJwtException e) {
            throw new CustomException(CodeEnum.UNAUTHORIZED_ERROR);
            // token过期异常
        } catch (ExpiredJwtException e) {
            throw new CustomException(CodeEnum.UNAUTHORIZED_EXPIRE);
        }
        return claims;
    }

    /**
     *  判断当前 token 是否过期了，已过期返回 true
     * @param claims
     * @return Boolean
     */
    public static Boolean isExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
