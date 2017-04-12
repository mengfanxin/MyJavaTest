package org.fkit.hrm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.hrm.dao.provider.UserDynaSqlProvider;
import org.fkit.hrm.domain.User;

import java.util.List;
import java.util.Map;

import static org.fkit.hrm.util.HrmConstants.*;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
public interface UserDao {

    //根据登录用户名密码查询员工
    @Select("select * from "+USERTABLE+"WHERE loginname=#{loginname} and password =#{password}")
    User selectByLoginnameAndPassword(
            @Param("loginname") String loginname,
            @Param("password") String password
    );

    //根据ID查询用户
    @Select("select * from "+USERTABLE+" where id = #{id}")
    User selectById(Integer id);

    //根据ID删除用户
    @Delete("delete from "+USERTABLE+" where id = #{id}")
    void deleteById(Integer id);

    //动态修改用户
    @SelectProvider(type = UserDynaSqlProvider.class, method = "updateUser")
    void update(User user);

    //动态查询
    @SelectProvider(type = UserDynaSqlProvider.class, method = "selectWithParam")
    List<User> selectByPage(Map<String, Object> params);

    //根据参数查询用户总数
    @SelectProvider(type = UserDynaSqlProvider.class, method = "count")
    Integer count(Map<String,Object> params);

    //动态插入用户
    @SelectProvider(type = UserDynaSqlProvider.class, method = "insertUser")
    void save(User user);










}
