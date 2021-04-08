package cn.itcast.service;

import cn.itcast.entity.Permission;
import cn.itcast.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    List<Role> findOtherRole(String id) throws Exception;

    Role findById(String id) throws Exception;

    void deleteRole(String id) throws Exception;

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionToRole(String[] permissionIds,String roleId) throws Exception;
}
