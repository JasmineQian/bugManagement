package com.jasmine.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Desc: 页面控制器
 *
 * @author: aming
 * @Version: 2018-12-14 16:22
 * @since 1.0
 */
public class IndexController {

	@RequestMapping(value = "/index2")
	public String index() {
		System.out.println("返回index页面");
		//跳转到 templates 目录下的 index.html
		return "index";
	}


	@RequestMapping(value = "/HelloWorld")
	public String HelloWorld() {
		System.out.println("HelloWorld");
		return "HelloWorld";
	}
}
