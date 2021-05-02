package com.king.naturalhistory.service;

import com.king.naturalhistory.bean.UserEntry;
import com.king.naturalhistory.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public UserEntry getUser(String name){
        UserEntry byUserName = userRepository.findByUserName(name);
        return byUserName;
    }
}
