package com.king.naturalhistory.controller;

import com.king.naturalhistory.bean.UserEntry;
import com.king.naturalhistory.bean.respone.ServerResponse;
import com.king.naturalhistory.properties.JwtProperties;
import com.king.naturalhistory.service.UserService;
import com.king.naturalhistory.utils.CookieUtils;
import com.king.naturalhistory.utils.ObjectUtils;
import com.king.naturalhistory.utils.UtilFunctions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;

@Controller
@Slf4j
public class ImageCodeController {
    @Autowired
    UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    @RequestMapping(value = "/verified/img", method = RequestMethod.GET)
    public void generateImgVerificationCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            int width = 129;
            int height = 40;
            BufferedImage verifyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // 生成对应宽高的初始图片
            // 生成验证码字符并加上噪点，干扰线，返回值为验证码字符
            String randomText = UtilFunctions.drawRandomText(width, height, verifyImg);

            request.getSession().setAttribute("img_verify_code", randomText.toUpperCase());
            request.getSession().setAttribute("img_verify_time", System.currentTimeMillis());

            response.setContentType("image/png"); // 必须设置响应内容类型为图片，否则前台不识别
            OutputStream os = response.getOutputStream(); // 获取文件输出流
            ImageIO.write(verifyImg, "png", os); // 输出图片流

            os.flush();
            os.close();
        } catch (Exception e) {
            log.error("msg: {}, exception: {}", e.toString(), e);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Map<String, Object>> login(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> map) {
        Map<String, Object> user = userService.getUser(map);
        String token = (String) user.get("token");
        UserEntry userinfo = (UserEntry) user.get("userinfo");
        if (!token.isEmpty() && null != userinfo) {
            // 不使用Cookie来存储cookies
            // CookieUtils.setCookie(request, response, jwtProperties.getCookieName(), token, jwtProperties.getExpire() * 60);
            //使用工具类，设置cookie并返回给浏览器，需要cookie名称，cookie的值，过期时间，配置的是分，默认使用的秒，注意乘以60
            return ServerResponse.createBySuccess("登录成功",user);
        }
        return ServerResponse.createByErrorMessage("用户名或者密码错误");
    }
}
