package com.jasmine.demo.service;

import com.jasmine.demo.bean.Bug;

import java.util.List;

public interface BugService {

    List<Bug> findAll();

    Bug findById(int id);

    int create(String pname,String crname,String crnum, String tasknum, String oname, String description, String rca, String solution, String developer, String tester);

    int update(long id, String crnum, String tasknum, String oname, String description, String rca, String solution, String developer, String tester);

    int deleteByID(int id);


}
