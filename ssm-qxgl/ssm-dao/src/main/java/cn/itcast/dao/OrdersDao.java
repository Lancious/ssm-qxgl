package cn.itcast.dao;

import cn.itcast.entity.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao {

    @Select("select * from orders")
    @Results(id = "ordersMap",
            value = {
                    @Result(id = true,column = "id",property = "id"),
                    @Result(column = "orderNum",property = "orderNum"),
                    @Result(column = "orderTime",property = "orderTime"),
                    @Result(column = "peopleCount",property = "peopleCount"),
                    @Result(column = "orderDesc",property = "orderDesc"),
                    @Result(column = "payType",property = "payType"),
                    @Result(column = "orderStatus",property = "orderStatus"),
                    @Result(column = "productId",property = "product",
                    one = @One(select = "cn.itcast.dao.ProductDao.findByid"))

            })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id=#{id}")
    @Results(
            value = {
                    @Result(id = true,column = "id",property = "id"),
                    @Result(column = "orderNum",property = "orderNum"),
                    @Result(column = "orderTime",property = "orderTime"),
                    @Result(column = "peopleCount",property = "peopleCount"),
                    @Result(column = "orderDesc",property = "orderDesc"),
                    @Result(column = "payType",property = "payType"),
                    @Result(column = "orderStatus",property = "orderStatus"),
                    @Result(column = "productId",property = "product",
                            one = @One(select = "cn.itcast.dao.ProductDao.findByid")),
                    @Result(column = "memberId",property = "member",
                            one = @One(select = "cn.itcast.dao.MemberDao.findById"))     ,
                    @Result(column = "id",property = "travellers",
                            many = @Many(select = "cn.itcast.dao.TravellerDao.findByOrdersId"))
            })
    Orders findById(String id) throws Exception;
}
