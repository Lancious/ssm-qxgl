package cn.itcast.dao;

import cn.itcast.entity.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "email",property = "email"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",javaType = List.class,
                    many = @Many(select = "cn.itcast.dao.RoleDao.findByUserId"))
    })
    Users findById(String id) throws Exception;

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "email",property = "email"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",javaType = List.class,
            many = @Many(select = "cn.itcast.dao.RoleDao.findByUserId"))
    })
    Users findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<Users> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password}," +
            "#{phoneNum},#{status})")
    void save(Users users) throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId")String userId,@Param("roleId")String roleId);
}
