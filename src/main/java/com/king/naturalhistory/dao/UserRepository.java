package com.king.naturalhistory.dao;

import com.king.naturalhistory.bean.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntry, Integer> {
    @Query(value = "select userid,username,password,telphone,realname,email,role,createtime,updatetime,lastlogintime from user where username=?", nativeQuery = true)
    UserEntry findByUserName(String Username);
}
