package cn.itcast.dao;

import cn.itcast.entity.Orders;
import cn.itcast.entity.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellerDao {

    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId =#{orderId})")
    List<Traveller> findByOrdersId(String orderId) throws Exception;
}
