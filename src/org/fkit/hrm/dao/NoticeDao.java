package org.fkit.hrm.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.hrm.dao.provider.NoticeDynaSqlProvider;
import org.fkit.hrm.domain.Notice;

import java.util.List;
import java.util.Map;

import static org.fkit.hrm.util.HrmConstants.NOTICETABLE;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
public interface NoticeDao {

    //动态查询
    @SelectProvider(type = NoticeDynaSqlProvider.class,method = "selectWithParam")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "CREATE_DATE",property = "createDate",javaType = java.util.Date.class),
            @Result(column = "USER_ID",property = "user",one = @One(select = "org.fkit.hrm.dao.UserDap.selectById",fetchType = FetchType.EAGER))
    })
    List<Notice> selectByPage(Map<String,Object> params);
    @SelectProvider(type = NoticeDynaSqlProvider.class,method = "count")
    Integer count(Map<String,Object> params);
    @Select("select * from "+NOTICETABLE+" where ID = #{id} ")
    Notice selectById(int id);
    @Delete(" delete from  "+NOTICETABLE+" where id = #{id} ")
    void deleteById(Integer id);
    @SelectProvider(type = NoticeDynaSqlProvider.class,method = "insertNoptice")
    void save(Notice notice);
    @SelectProvider(type = NoticeDynaSqlProvider.class,method = "updateNotice")
    void update(Notice notice);


}
