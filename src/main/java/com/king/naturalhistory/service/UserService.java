package com.king.naturalhistory.service;

import com.king.naturalhistory.bean.UserEntry;
import com.king.naturalhistory.properties.JwtProperties;
import com.king.naturalhistory.bean.vo.UserVo;
import com.king.naturalhistory.dao.UserRepository;
import com.king.naturalhistory.utils.JwtUtils;
import com.king.naturalhistory.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Resource
    private JwtProperties jwtProperties;

    public Map<String,Object> getUser(Map<String, String> map) {
        Map<String, Object> reMap=new HashMap<>();
        String username = map.get("username");
        String password = map.get("password");
        if (!StringUtils.isEmpty(username)) {
            UserEntry user = userRepository.findByUserName(username);
            if (MD5Utils.md5(password.trim()).equals(user.getPassword())) {
                UserVo userVo = new UserVo(user.getUserid(), user.getPassword());
                try {
                    String token = JwtUtils.generateToken(userVo, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
                    reMap.put("token",token);
                    reMap.put("userinfo",user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return reMap;
    }
}
