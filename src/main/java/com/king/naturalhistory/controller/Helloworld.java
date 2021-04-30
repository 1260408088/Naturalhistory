package com.king.naturalhistory.controller;

import com.alibaba.fastjson.JSON;
import com.king.naturalhistory.service.PoetrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class Helloworld {
    @Autowired
    PoetrieService poetrieService;
    @RequestMapping("/hello")
    public String index(Map<String,Object> valueMap) {
        List<Map<String,Object>> listByTitle = poetrieService.getListByParam(valueMap);
        String result = JSON.toJSONString(listByTitle);
        return result;
    }
}
