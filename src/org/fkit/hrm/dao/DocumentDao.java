package org.fkit.hrm.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.hrm.dao.provider.DocumentDynaSqlProvider;
import org.fkit.hrm.domain.Document;

import javax.swing.event.DocumentEvent;
import java.util.List;
import java.util.Map;

import static org.fkit.hrm.util.HrmConstants.DOCUMENTTABLE;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
public interface DocumentDao {

    //动态查询
    @SelectProvider(type = DocumentDynaSqlProvider.class,method = "selectWithParam")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "CREATE_DATE",property = "createDate",javaType = java.util.Date.class),
            @Result(column = "USER_ID", property = "user",one = @One(select = "org.kfit.hrm.dao.UserDao.selectById", fetchType = FetchType.EAGER))
    })
    List<Document> selectByPage(Map<String,Object> params);
    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "insertDocument")
    Integer count(Map<String,Object> params);
    //动态插入文档
    @SelectProvider(type = DocumentDynaSqlProvider.class,method = "insertDocument")
    void save(Document document);
    //根据ID删除文档
    @Delete(" delete from "+DOCUMENTTABLE+" where id = #{id} ")
    void deleteById(Integer id);
    //动态修改文档
    @SelectProvider(type = DocumentDynaSqlProvider.class,method = "updateDocument")
    void update(Document document);
    @Select("select * from "+DOCUMENTTABLE+" where ID = #{id}")
    Document selectById(int id);




}
