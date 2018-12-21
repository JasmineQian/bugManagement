package com.jasmine.demo.jdbc;

import com.jasmine.demo.bean.Empoly;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpolyMapper implements RowMapper<Empoly> {


    @Override
    public Empoly mapRow(ResultSet resultSet, int i) throws SQLException {
        //此处要使用表中的字段，不能使用属性
        long eid = resultSet.getInt(1);
        String ename = resultSet.getString(2);

        Empoly empoly = new Empoly();
        empoly.setEid(eid);
        empoly.setEname(ename);
        return empoly;
    }
}
