package cn.itcast.controller;

import cn.itcast.entity.Orders;
import cn.itcast.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    //未分页
//    @RequestMapping("/findAll")
//    public ModelAndView findAll() throws Exception{
//        ModelAndView modelAndView = new ModelAndView();
//        List<Orders> orders = ordersService.findAll();
//        modelAndView.addObject("ordersList",orders);
//        modelAndView.setViewName("orders-list");
//        return modelAndView;
//    }

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
    @RequestParam(name = "pageSize",required = true,defaultValue = "10")Integer pageSize) throws Exception{
        List<Orders> ordersList = ordersService.findByAllPage(page,pageSize);
        PageInfo pageInfo = new PageInfo(ordersList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orders-page-list");
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        Orders orders = ordersService.findById(id);
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }
}
