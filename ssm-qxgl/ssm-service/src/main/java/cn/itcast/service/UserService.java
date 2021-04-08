package cn.itcast.service;

import cn.itcast.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<Users> findAll() throws Exception;

    void save(Users users) throws Exception;

    Users findById(String id) throws Exception;

    void addRoleToUser(String userId, String[] roleIds);
}
