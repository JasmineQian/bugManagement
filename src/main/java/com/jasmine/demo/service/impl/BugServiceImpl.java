package com.jasmine.demo.service.impl;

import com.jasmine.demo.bean.Bug;
import com.jasmine.demo.jdbc.BugRowMapper;
import com.jasmine.demo.service.BugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugServiceImpl implements BugService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Bug> findAll() {

        String sql = "SELECT BUG_ID,PROJECT_NAME,CR_NAME,BUG_CR_NUM,BUG_TASK_NUM,OBJECT_NAME,BUG_DESCRIPTION\n" +
                ",BUG_RCA,BUG_SOLUTION,B.EMPLOY_NAME DEVELOPER,A.EMPLOY_NAME TESTER,QA_CREATIONDT,QA_UPDATEDT,BUG_DELETED_FLAG\n" +
                "FROM QA_BUGLIST\n" +
                "JOIN QA_PROJECT ON BUG_PROJECT_ID = PROJECT_ID\n" +
                "JOIN QA_CRTYPE ON CR_ID = BUG_CR_TYPE_ID\n" +
                "JOIN QA_RTYPE ON OBJECT_ID = QA_TYPE_ID\n" +
                "JOIN QA_EMPLOY A ON A.[EMPLOY_ID] = QA_TESTER_ID AND A.[EMPLOY_GROUP] = 1 ---表示测试\n" +
                "JOIN QA_EMPLOY B ON B.[EMPLOY_ID] = QA_ASSIGNEE_ID AND B.[EMPLOY_GROUP] = 2 ---表示开发人员\n" +
                "WHERE BUG_DELETED_FLAG =0 order by 1 desc";
        List<Bug> bugs = jdbcTemplate.query(sql,new BugRowMapper() );
        return bugs;

    }

    @Override
    public Bug findById(int id) {
        String sql = "SELECT BUG_ID,PROJECT_NAME,CR_NAME,isnull(BUG_CR_NUM,'') as BUG_CR_NUM,isnull(BUG_TASK_NUM,'') as BUG_TASK_NUM,OBJECT_NAME,isnull(BUG_DESCRIPTION,'') as BUG_DESCRIPTION\n" +
                ",isnull(BUG_RCA,'') as BUG_RCA,isnull(BUG_SOLUTION,'') as BUG_SOLUTION,B.EMPLOY_NAME DEVELOPER,A.EMPLOY_NAME TESTER,QA_CREATIONDT,QA_UPDATEDT,BUG_DELETED_FLAG\n" +
                "FROM QA_BUGLIST\n" +
                "JOIN QA_PROJECT ON BUG_PROJECT_ID = PROJECT_ID\n" +
                "JOIN QA_CRTYPE ON CR_ID = BUG_CR_TYPE_ID\n" +
                "JOIN QA_RTYPE ON OBJECT_ID = QA_TYPE_ID\n" +
                "JOIN QA_EMPLOY A ON A.[EMPLOY_ID] = QA_TESTER_ID AND A.[EMPLOY_GROUP] = 1 ---表示测试\n" +
                "JOIN QA_EMPLOY B ON B.[EMPLOY_ID] = QA_ASSIGNEE_ID AND B.[EMPLOY_GROUP] = 2 ---表示开发人员\n" +
                "WHERE BUG_DELETED_FLAG =0 and BUG_ID = ?";
        Bug bug = jdbcTemplate.queryForObject(sql,new BugRowMapper(),id);
        return bug;
    }

    @Override
    public int create(String pname,String crname,String crnum, String tasknum, String oname, String description, String rca, String solution, String developer, String tester) {
        String sql ="INSERT QA_BUGLIST(BUG_PROJECT_ID,BUG_CR_TYPE_ID,BUG_CR_NUM,BUG_TASK_NUM,QA_TYPE_ID,BUG_DESCRIPTION,BUG_RCA,BUG_SOLUTION,QA_ASSIGNEE_ID,QA_TESTER_ID,QA_CREATIONDT,QA_UPDATEDT)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,GETDATE(),GETDATE())";
        return jdbcTemplate.update(sql,pname,crname,crnum,tasknum,oname,description,rca,solution,developer,tester);
    }

    @Override
    public int update(long id, String crnum, String tasknum,  String description, String rca, String solution, String developer, String tester,String oname){
        String sql = "update QA_BUGLIST set BUG_CR_NUM =?,BUG_TASK_NUM =?,BUG_DESCRIPTION=?,BUG_RCA=?,BUG_SOLUTION= ?,QA_UPDATEDT =getdate() where BUG_ID = ?";
        return jdbcTemplate.update(sql,crnum,tasknum,description,rca,solution,id);
    }

    @Override
    public int deleteByID(int id) {
        String sql = "update QA_BUGLIST set BUG_DELETED_FLAG = 1,  BUG_DELETED_COMMENT = '逻辑删除' where BUG_ID = ?";
         int count = jdbcTemplate.update(sql,id);
        return count;
    }
}
