package cn.itcast.dao;

import cn.itcast.entity.Permission;
import cn.itcast.entity.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = List.class,
                    many = @Many(select = "cn.itcast.dao.PermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id not in(select roleId from users_role where userId=#{id})")
    List<Role> findOtherRole(String id) throws Exception;

    @Select("select * from role where id = #{id}")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = List.class,
            many = @Many(select = "cn.itcast.dao.PermissionDao.findByRoleId"))
    })
    Role findById(String id) throws  Exception;

    @Delete("delete from role where id = #{id}")
    void deleteRole(String id) throws Exception;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId) throws Exception;

    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("permissionId")String permissionId,@Param("roleId")String roleId) throws Exception;
}
