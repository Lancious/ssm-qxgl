package cn.itcast.controller;

import cn.itcast.entity.Role;
import cn.itcast.entity.Users;
import cn.itcast.service.RoleService;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Users> users = userService.findAll();
        modelAndView.addObject("userList",users);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Users users) throws Exception{
        userService.save(users);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        Users users = userService.findById(id);
        modelAndView.addObject("user",users);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,
                                @RequestParam(name = "ids",required = true) String[] ids) throws Exception{
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll";
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        Users users = userService.findById(id);
        List<Role> roleList = roleService.findOtherRole(id);
        modelAndView.addObject("user",users);
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }
}
