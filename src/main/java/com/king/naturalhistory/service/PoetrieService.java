package com.king.naturalhistory.service;

import com.king.naturalhistory.dao.PoetriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class PoetrieService {
    @Autowired
    PoetriesRepository poetriesRepository;

    public List<Map<String,Object>> getListByParam(Map<String,Object> map) {
        List<Map<String,Object>> poetriList = poetriesRepository.findAllByParam("2014-06-02","","低头","李白",null,0,20);
        return poetriList;
    }
}
