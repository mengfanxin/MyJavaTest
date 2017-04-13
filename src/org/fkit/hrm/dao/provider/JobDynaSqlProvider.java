package org.fkit.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Job;

import java.util.Map;

import static org.fkit.hrm.util.HrmConstants.JOBTABLE;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
public class JobDynaSqlProvider {
    //分页动态查询
    public String selectWithParam(Map<String , Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(JOBTABLE);
                if(params.get("job") != null){
                    Job job = (Job) params.get("job");
                    if(job.getName() != null && !job.getName().equals("")){
                        WHERE(" name like CONACT('%',#{job.name},'%')  ");
                    }
                }
            }

        }.toString();
        if (params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize} ";
        }
        return sql;
    }

    //动态查询总数量
    public String count(Map<String,Object> params){
        return new SQL(){
            {
                SELECT("*");
                FROM(JOBTABLE);
                if(params.get("job") != null){
                    Job job = (Job) params.get("job");
                    if(job.getName() != null && !job.getName().equals("")){
                        WHERE(" name LIKE CONACT ('%',#{job.name}),'%' ");
                    }
                }
            }
        }.toString();
    }


    //动态插入
    public String insertJob(Job job){
        return new SQL(){
            {
                INSERT_INTO(JOBTABLE);
                if(job.getName() != null && !job.getName().equals("")){
                    VALUES("name","#{name}");
                }
                if(job.getRemark() != null && !job.getRemark().equals("")){
                    VALUES("remark","#{remark}");
                }
            }
        }.toString();
    }


    //动态更新
    public String updateJob(Job job){
        return new SQL(){
            {
                UPDATE(JOBTABLE);
                if(job.getName() != null){
                    SET(" name=#{name} ");
                }
                if(job.getRemark() != null){
                    SET(" remark = #{remark} ");
                }
            }
        }.toString();
    }



}
