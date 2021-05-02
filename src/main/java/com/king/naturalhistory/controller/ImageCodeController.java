package com.king.naturalhistory.controller;

import com.king.naturalhistory.bean.UserEntry;
import com.king.naturalhistory.service.UserService;
import com.king.naturalhistory.utils.UtilFunctions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class ImageCodeController {
    @Autowired
    UserService userService;
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
            System.out.println(e.toString());
            /*UtilFunctions.log.error("msg: {}, exception: {}", e.toString(), e);
            UtilFunctions.reportError(e.toString(), e);*/
        }
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody Map<String,String> map) {
        String username = map.get("username");
        boolean empty = StringUtils.isEmpty(username);
        if(empty){
            throw new NullPointerException();
        }
        UserEntry user = userService.getUser(username);
        return user.getRelname();
    }
}
