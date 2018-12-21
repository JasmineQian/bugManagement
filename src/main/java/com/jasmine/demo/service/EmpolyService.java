package com.jasmine.demo.service;

import com.jasmine.demo.bean.Empoly;

import java.util.List;

public interface EmpolyService {

    List<Empoly> findTester();

    List<Empoly> findDeveloper();
}
