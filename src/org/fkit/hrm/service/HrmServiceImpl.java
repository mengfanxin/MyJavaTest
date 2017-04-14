package org.fkit.hrm.service;

import jdk.nashorn.internal.scripts.JO;
import org.fkit.hrm.dao.*;
import org.fkit.hrm.domain.*;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService{

    /**
     * 自动注入持久层Dao对象
     * @param loginname
     * @param password
     * @return
     */
    @Autowired
    private UserDao userDao;
    @Autowired
    private DeptDao depDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private JobDao jobDao;
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private DocumentDao documentDao;

    /**
     * 用户接口实现
     * @param loginname
     * @param password
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public User login(String loginname, String password) {
        System.out.println("hrmServierIMpl login ->>");
        return userDao.selectByLoginnameAndPassword(loginname,password);
    }
    @Transactional(readOnly = true)
    @Override
    public User findUserById(Integer id) {
        return userDao.selectById(id);
    }


    @Transactional(readOnly = true)
    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        /** 当前需要分页的总数据条数 **/
        Map<String,Object> params = new HashMap<>();
        params.put("user",user);
        int recordCount = userDao.count(params);
        System.out.println("recordCount ->>"+recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0 ){
            params.put("pageModel",pageModel);
        }
        List<User> users = userDao.selectByPage(params);
        return null;
    }

    /** 进行到这里了 **/
    @Override
    public void removeUserById(Integer id) {

    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
        return null;
    }

    @Override
    public void removeEmployeeById(Integer id) {

    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return null;
    }

    @Override
    public void addEmployee(Employee employee) {

    }

    @Override
    public void modifyEmployee(Employee employee) {

    }

    @Override
    public List<Dept> findDept(Dept dept, PageModel pageModel) {
        return null;
    }

    @Override
    public List<Dept> findAllDept() {
        return null;
    }

    @Override
    public void removeDeptById(Integer id) {

    }

    @Override
    public void addDept(Dept dept) {

    }

    @Override
    public Dept findDeptById(Integer id) {
        return null;
    }

    @Override
    public void modifyDept(Dept dept) {

    }

    @Override
    public List<Job> findAllJob() {
        return null;
    }

    @Override
    public List<Job> findJob(Job job, PageModel pageModel) {
        return null;
    }

    @Override
    public void removeJobById(Integer id) {

    }

    @Override
    public void addJob(Job job) {

    }

    @Override
    public Job findJobById(Integer id) {
        return null;
    }

    @Override
    public void modifyJob(Job job) {

    }

    @Override
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
        return null;
    }

    @Override
    public Notice findNoticeById(Integer id) {
        return null;
    }

    @Override
    public void removeNoticeById(Integer id) {

    }

    @Override
    public void addNotice(Notice notice) {

    }

    @Override
    public void modifyNotice(Notice notice) {

    }

    @Override
    public List<Document> findDocument(Document document, PageModel pageModel) {
        return null;
    }

    @Override
    public void addDocument(Document document) {

    }

    @Override
    public Document findDocumentById(Integer id) {
        return null;
    }

    @Override
    public void removeDocumentById(Integer id) {

    }

    @Override
    public void modifyDocument(Document document) {

    }
}
