package com.jasmine.demo.service.impl;

import com.jasmine.demo.bean.Bug;
import com.jasmine.demo.jdbc.BugRowMapper;
import com.jasmine.demo.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BugServiceImpl implements BugService {



    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Bug> findAll() {

        String sql = "SELECT BUG_ID,PROJECT_NAME,CR_NAME,BUG_CR_NUM,BUG_TASK_NUM,OBJECT_NAME,BUG_DESCRIPTION,BUG_RCA,BUG_SOLUTION,B.EMPLOY_NAME DEVELOPER,A.EMPLOY_NAME TESTER,QA_CREATIONDT,QA_UPDATEDT,BUG_DELETED_FLAG\n" +
                " FROM QA_BUGLIST\n" +
                "JOIN QA_PROJECT ON BUG_PROJECT_ID = PROJECT_ID\n" +
                "JOIN QA_CRTYPE ON CR_ID = BUG_CR_TYPE_ID\n" +
                "JOIN QA_RTYPE ON OBJECT_ID = QA_TYPE_ID\n" +
                "JOIN QA_EMPLOY A ON A.EMPLOY_ID = QA_TESTER_ID AND A.EMPLOY_GROUP = 1 \n" +
                "JOIN QA_EMPLOY B ON B.EMPLOY_ID= QA_ASSIGNEE_ID AND B.EMPLOY_GROUP = 2\n" +
                "WHERE BUG_DELETED_FLAG =0 order by 1 desc";
        List<Bug> bugs = jdbcTemplate.query(sql,new BugRowMapper() );
        return bugs;

    }

    @Override
    public Bug findById(int id) {
        String sql = "SELECT BUG_ID,PROJECT_NAME,CR_NAME,BUG_CR_NUM,BUG_TASK_NUM,OBJECT_NAME,BUG_DESCRIPTION,BUG_RCA,BUG_SOLUTION,B.EMPLOY_NAME DEVELOPER,A.EMPLOY_NAME TESTER,QA_CREATIONDT,QA_UPDATEDT,BUG_DELETED_FLAG\n" +
                "FROM QA_BUGLIST  JOIN QA_PROJECT ON BUG_PROJECT_ID = PROJECT_ID\n" +
                "JOIN QA_CRTYPE ON CR_ID = BUG_CR_TYPE_ID\n" +
                "JOIN QA_RTYPE ON OBJECT_ID = QA_TYPE_ID\n" +
                "JOIN QA_EMPLOY A ON A.EMPLOY_ID = QA_TESTER_ID AND A.EMPLOY_GROUP = 1 \n" +
                "JOIN QA_EMPLOY B ON B.EMPLOY_ID= QA_ASSIGNEE_ID AND B.EMPLOY_GROUP = 2\n" +
                "WHERE BUG_DELETED_FLAG =0 and BUG_ID = ?";
        Bug bug = jdbcTemplate.queryForObject(sql,new BugRowMapper(),id);
        return bug;
    }

    @Override
    public int create(String pname,String crname,String crnum, String tasknum, String oname, String description, String rca, String solution, String developer, String tester) {
        Date dt =new Date();
        DateFormat bf = DateFormat.getDateTimeInstance();
        String date =bf.format(dt);

        String sql ="INSERT QA_BUGLIST(BUG_PROJECT_ID,BUG_CR_TYPE_ID,BUG_CR_NUM,BUG_TASK_NUM,QA_TYPE_ID,BUG_DESCRIPTION,BUG_RCA,BUG_SOLUTION,QA_ASSIGNEE_ID,QA_TESTER_ID,QA_CREATIONDT,QA_UPDATEDT)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,pname,crname,crnum,tasknum,oname,description,rca,solution,developer,tester,date,date);
    }

    @Override
    public int update(long id, String pname,String crname,String crnum,String oname, String tasknum,  String description, String rca, String solution, String developer, String tester){
        Date dt =new Date();
        DateFormat bf = DateFormat.getDateTimeInstance();
        String date =bf.format(dt);
        String sql = "update QA_BUGLIST set BUG_PROJECT_ID = ?,BUG_CR_TYPE_ID=?, BUG_CR_NUM =?, QA_TYPE_ID =?,BUG_TASK_NUM =?,BUG_DESCRIPTION=?,BUG_RCA=?,BUG_SOLUTION= ?,QA_ASSIGNEE_ID = ? ,QA_TESTER_ID =? ,QA_UPDATEDT =? where BUG_ID = ?";
        System.out.println("sql:"+sql);
        System.out.println(pname);
        System.out.println(crname);
        System.out.println(crnum);
        System.out.println(oname);
        System.out.println(tasknum);
        System.out.println(description);
        System.out.println(rca);
        System.out.println(solution);
        System.out.println(developer);
        System.out.println(tester);
        System.out.println(date);
        System.out.println(id);
        return jdbcTemplate.update(sql,pname,crname,crnum,oname,tasknum,description,rca,solution,developer,tester,date,id);
    }

    @Override
    public int deleteByID(int id) {
        String sql = "update QA_BUGLIST set BUG_DELETED_FLAG = 1,  BUG_DELETED_COMMENT = '逻辑删除' where BUG_ID = ?";
         int count = jdbcTemplate.update(sql,id);
        return count;
    }
}
