package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.entity.Role;
import cn.itcast.entity.Users;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = null;
        try {
            users = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Role> roles = users.getRoles();
        List<SimpleGrantedAuthority> authorities = getAuthority(roles);
        User user = new User(users.getUsername(),bCryptPasswordEncoder.encode(users.getPassword()),
                users.getStatus()==0?false:true,true,true,true,authorities);
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public List<Users> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void save(Users users) throws Exception {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        userDao.save(users);
    }

    @Override
    public Users findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }


}
