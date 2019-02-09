package org.kwin.management.dao;

import org.kwin.management.entity.OrderMaster;

import java.util.List;

public interface OrderMasterMapper {
    /**
     * 删除
     * @param orderId
     * @return
     */
    int deleteByPrimaryKey(String orderId);

    /**
     * 增加
     * @param record
     * @return
     */
    int insert(OrderMaster record);

    /**
     * 查找
     * @param orderId
     * @return
     */
    OrderMaster selectByPrimaryKey(String orderId);

    /**
     * 查找全部
     * @return
     */
    List<OrderMaster> selectAll();

    int updateByPrimaryKeySelective(OrderMaster record);

    int updateByPrimaryKey(OrderMaster record);

    int insertSelective(OrderMaster record);
}