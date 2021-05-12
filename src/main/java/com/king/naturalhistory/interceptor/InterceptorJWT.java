package com.king.naturalhistory.interceptor;

import com.king.naturalhistory.config.JwtConfig;
import com.king.naturalhistory.properties.JwtProperties;
import com.king.naturalhistory.utils.CookieUtils;
import com.sun.deploy.net.MessageHeader;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * 请求api服务器时，对请求进行拦截判断，有效则可以访问接口，否则返回错误
 *
 * @author Win7
 */
@Component
public class InterceptorJWT extends HandlerInterceptorAdapter {
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private JwtProperties jwtProperties;
    private static final Set filterSet=new HashSet<>();

    static {
        filterSet.add("/login");
        filterSet.add("/register");
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 地址过滤
        String uri = request.getRequestURI();
        if(filterSet.contains(uri)){
            return true;
        }

        // Token 验证
        /*String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtConfig.getHeader());
        }*/
        String token = request.getParameter(jwtConfig.getHeader());
        if (StringUtils.isEmpty(token)) {
            throw new Exception(jwtConfig.getHeader() + "不能为空");
        }
        Claims claims = jwtConfig.getTokenClaim(token);
        if (claims == null || jwtConfig.isTokenExpired(claims.getExpiration())) {
            throw new Exception(jwtConfig.getHeader() + "失效，请重新登录");
        }
        //设置 identityId 用户身份ID
        request.setAttribute("identityId", claims.getSubject());
        return true;
    }
}
