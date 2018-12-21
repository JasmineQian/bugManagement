package com.jasmine.demo.jdbc;


import com.jasmine.demo.bean.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements RowMapper<Project> {


    @Override
    public Project mapRow(ResultSet resultSet, int i) throws SQLException {
        //此处要使用表中的字段，不能使用属性
        long pid = resultSet.getInt("PROJECT_ID");
        String pname = resultSet.getString("PROJECT_NAME");
        String pcreationdt = resultSet.getString("PROJECT_CREATION_DT");
        String pupdatedt = resultSet.getString("PROJECT_UPDATE_DT");

        Project project = new Project();
        project.setPid(pid);
        project.setPname(pname);
        project.setPcreationdt(pcreationdt);
        project.setPupdatedt(pupdatedt);

        return project;
    }

}
