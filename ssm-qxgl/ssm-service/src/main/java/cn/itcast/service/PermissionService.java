package cn.itcast.service;

import cn.itcast.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;

    Permission findById(String id) throws Exception;

    void deletePermission(String id) throws Exception;
}
