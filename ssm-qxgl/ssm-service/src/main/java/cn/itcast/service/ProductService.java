package cn.itcast.service;

import cn.itcast.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;
}
