package cn.itcast.service;

import cn.itcast.entity.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> findAll() throws Exception;

    List<Orders> findByAllPage(int page,int pageSize) throws Exception;

    Orders findById(String id) throws Exception;
}
