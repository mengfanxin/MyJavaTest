package org.fkit.hrm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.hrm.dao.provider.JobDynaSqlProvider;
import org.fkit.hrm.domain.Job;

import java.util.List;
import java.util.Map;

import static org.fkit.hrm.util.HrmConstants.JOBTABLE;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
public interface JobDao {

    @Select("SELECT * from"+JOBTABLE+" where id = #{id}")
    Job SelectById(int id);
    @Select("SELECT * from "+JOBTABLE+" ")
    List<Job> SelectAllJob();
    //动态查询
    @SelectProvider(type = JobDynaSqlProvider.class, method = "selectWithParam")
    List<Job> selectByPage(Map<String,Object> params);
    @SelectProvider(type = JobDynaSqlProvider.class,method = "count")
    Integer count(Map<String,Object> params);
    // 根据ID删除部门
    @Delete(" delete from  "+JOBTABLE+" where id = #{id} ")
    void deleteById(Integer id);
    //动态插入部门
    @SelectProvider(type=JobDynaSqlProvider.class,method = "insertJob")
    void save(Job job);
    //动态修改用户
    @SelectProvider(type = JobDynaSqlProvider.class,method = "updateJob")
    void update(Job job);



}
