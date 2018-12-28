package com.jasmine.demo.controller;


import com.jasmine.demo.bean.*;
import com.jasmine.demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @apiDefine spring boot demo
 * spring boot demo 接口
 */


@Controller
public class BugController {

    // 记录器
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private BugService bugService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmpolyService empolyService;

    @Autowired
    private TestTypeService testTypeService;

    @Autowired
    private CRService crService;

    /**
     * @api {post} /user/add   添加User的接口
     * @apiName 添加用户
     * @apiGroup user
     * @apiDescription 添加用户的接口
     * @apiParam userId 用户id
     * @apiParam username 用户名
     * @apiParam password 用户名密码
     * @apiParamExample {json}
     * Request-Example 1:
     * {
     *      "userId":"1001",
     *      "username":"caocao",
     *      "password":"123456"
     * }
     * @apiSuccess 1
     */

    @RequestMapping("/listpage")
    public String PageList(Model model) {
        logger.info("查询Buglist信息");
        List<Bug> bugs = bugService.findAll();

        if (bugs.size() > 0) {
            logger.info("查询Buglist信息成功");
        }
        model.addAttribute("list", bugs);
        logger.info(String.valueOf(bugs));
        return "list";
    }

    @RequestMapping("/findByIdpage")
    public String findByIdpage(Model model, @RequestParam("BugListId") int id) {
        logger.info("根据用户ID查询用户信息");
        Bug bug = bugService.findById(id);
        logger.info(String.valueOf(bug));
        if (bug != null) {
            logger.info("查询成功！");
            model.addAttribute("bug", bug);
            model.addAttribute("message", "查询成功");
            return "details";
        } else {
            model.addAttribute("message", "查询失败");
            return "auto";
        }
    }




    @PostMapping("/create")
    public String create(Model model,Bug bug) {
        logger.info("新增bug记录");
        int result = bugService.create(bug.getPname(),bug.getCrname(),bug.getCrnum(),bug.getTasknum(),bug.getOname(),bug.getDescription(),bug.getRca(),bug.getSolution(),bug.getDeveloper(),bug.getTester());
        if(result == 1) {
            logger.info("新增bug成功！");
            model.addAttribute("message","新增bug成功");
            return "auto";
        }else {
            model.addAttribute("message","新增bug失败");
            logger.info("新增bug失败!");
            return "auto";
        }

    }

    /**
     * @param
     * @return 删除数据
     */
    @GetMapping("/del")
    public String del(Model model, @RequestParam("BugListId") int id) {
        int temp = bugService.deleteByID(id);
        logger.info(String.valueOf(temp));
        if (temp > 0) {
            logger.info("删除成功!");
            model.addAttribute("message", "删除成功");
            return "auto";
        } else {
            model.addAttribute("message", "删除失败");
            return "auto";
        }

    }


    @RequestMapping("/toUpdate")
    public String update(Model model, @RequestParam("BugListId") int id, @RequestParam("pname") String pname,
                         @RequestParam("crname") String crname, @RequestParam("crnum") String crnum, @RequestParam("tasknum") String tasknum,
                         @RequestParam("oname") String oname, @RequestParam("tester") String tester, @RequestParam("developer") String developer,
                         @RequestParam("description") String description, @RequestParam("rca") String rca, @RequestParam("solution") String solution) {
        logger.info("转向更新页面,在页面提交之前，并未进行更新");
        model.addAttribute("id", id);
        model.addAttribute("pname", pname);
        model.addAttribute("crname", crname);
        model.addAttribute("crnum", crnum);
        model.addAttribute("tasknum", tasknum);
        model.addAttribute("oname", oname);
        model.addAttribute("tester", tester);
        model.addAttribute("developer", developer);
        model.addAttribute("description", description);
        model.addAttribute("rca", rca);
        model.addAttribute("solution", solution);
        List<Project> projects = projectService.findAll();
        List<Empoly> testers = empolyService.findTester();
        List<Empoly> developers = empolyService.findDeveloper();
        List<TestType> testType = testTypeService.findTestType();
        List<CR> crtype = crService.findCRType();
        model.addAttribute("projects", projects);
        model.addAttribute("testers", testers);
        model.addAttribute("developers", developers);
        model.addAttribute("testType", testType);
        model.addAttribute("crtype", crtype);
        return "update";
    }


    @GetMapping("/listapi")
    @ResponseBody
    public List<Bug> getList() {
        logger.info("从数据库中读取buglist集合");
        List<Bug> bugs = bugService.findAll();

        if(bugs.size()>0) {
            logger.info("查询Buglist信息成功");
        }
        return bugs;
    }

    @RequestMapping("/Update")
    public String update(Model model, Bug bug) {
        logger.info("修改用户"+bug);
        int result = bugService.update(bug.getId(),bug.getPname(),bug.getCrname(),bug.getCrnum(),bug.getOname(),bug.getTasknum(),bug.getDescription(),bug.getRca(),bug.getSolution(),bug.getDeveloper(),bug.getTester());
        if(result == 1) {
            logger.info("修改Bug信息成功！");
            model.addAttribute("message","修改Bug信息成功");
            return "auto";
        }else {
            logger.info("修改Bug信息失败!");
            model.addAttribute("message","修改Bug信息成功");
            return "auto";
        }
    }

}
