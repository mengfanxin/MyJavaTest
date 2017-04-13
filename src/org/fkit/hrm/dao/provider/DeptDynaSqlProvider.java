package org.fkit.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Dept;

import java.util.Map;

import static org.fkit.hrm.util.HrmConstants.DEPTTABLE;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
public class DeptDynaSqlProvider {

    //分页动态查询
    public String selectWithParam(Map<String,Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(DEPTTABLE);
                if(params.get("dept") != null){
                    Dept dept = (Dept) params.get("dept");
                    if(dept.getName() != null && !dept.getName().equals("")){
                        WHERE(" name LIKE CONACT ('%',#{dept.name},'%')");
                    }
                }
            }
        }.toString();

        if (params.get("pageModel") != null){
            sql+=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ";
        }
        return sql;
    }
    //动态查询总数量
    public String count(Map<String , Object> params){
        return new SQL(){
            {
                SELECT("COUNT(*)");
                FROM(DEPTTABLE);
                if(params.get("dept") != null){
                    Dept dept = (Dept) params.get("dept");
                    if(dept.getName() != null && !dept.getName().equals("")){
                        WHERE(" name like CONACT ('%',#{dept.name},'%') ");
                    }
                }
            }
        }.toString();
    }
    //动态插入
    public String insertDept(Dept dept){
        return new SQL(){
            {
                INSERT_INTO(DEPTTABLE);
                if(dept.getName() != null && !dept.getName().equals("")){
                    VALUES(" name", "#{dept.name} ");
                }
                if(dept.getRemark() !=null && !dept.getRemark().equals("")){
                    VALUES(" remark","#{dept.remark} ");
                }
            }
        }.toString();
    }
    //动态更新
    public String updateDept(Dept dept){
        return new SQL(){
            {
                UPDATE(DEPTTABLE);
                if(dept.getName() != null ){
                    SET(" name = #{dept.name} ");
                }
                if (dept.getRemark() != null){
                    SET(" remark = #{dept.remark} ");
                }
            }
        }.toString();
    }





}
