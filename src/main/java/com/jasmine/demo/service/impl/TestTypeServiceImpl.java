package com.jasmine.demo.service.impl;

import com.jasmine.demo.bean.TestType;
import com.jasmine.demo.jdbc.TestTypeMapper;
import com.jasmine.demo.service.TestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestTypeServiceImpl implements TestTypeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<TestType> findTestType() {
        String sql = "SELECT OBJECT_ID,OBJECT_NAME FROM QA_RTYPE where OBJECT_DELETED_FLAG =0";
        List<TestType> crtype= jdbcTemplate.query(sql,new TestTypeMapper() );
        return crtype;
    }
}
