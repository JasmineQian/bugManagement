package com.jasmine.demo.service.impl;


import com.jasmine.demo.bean.Project;
import com.jasmine.demo.jdbc.ProjectMapper;
import com.jasmine.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Project> findAll() {
        String sql = "SELECT PROJECT_ID,PROJECT_NAME,PROJECT_CREATION_DT,PROJECT_UPDATE_DT FROM QA_PROJECT where PROJECT_DELETED_FLAG = 0";
        List<Project> projects = jdbcTemplate.query(sql,new ProjectMapper() );
        return projects;

    }
}
