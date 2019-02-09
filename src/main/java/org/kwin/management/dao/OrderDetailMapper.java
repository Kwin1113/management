package org.kwin.management.dao;

import org.kwin.management.entity.OrderDetail;

import java.util.List;

public interface OrderDetailMapper {
    /**
     * 删除
     * @param detailId
     * @return
     */
    int deleteByPrimaryKey(String detailId);

    /**
     * 增加
     * @param record
     * @return
     */
    int insert(OrderDetail record);

    /**
     * 按详情id查找
     * @param detailId
     * @return
     */
    OrderDetail selectByPrimaryKey(String detailId);

    List<OrderDetail> selectByOrderId(String orderId);

    List<OrderDetail> selectAll();

    int updateByPrimaryKeySelective(OrderDetail record);






    int updateByPrimaryKey(OrderDetail record);

    int insertSelective(OrderDetail record);
}