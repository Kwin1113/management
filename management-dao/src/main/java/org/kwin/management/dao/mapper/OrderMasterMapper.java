package org.kwin.management.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.kwin.management.entity.OrderMaster;

import java.util.List;

public interface OrderMasterMapper {
    /**
     * 删除
     *
     * @param orderId
     * @return
     */
    int deleteByPrimaryKey(@Param("orderId") String orderId,@Param("userId") Integer userId);

    /**
     * 增加
     *
     * @param record
     * @return
     */
    int insert(@Param("orderMaster")OrderMaster record,@Param("userId") Integer userId);

    /**
     * 查找
     *
     * @param orderId
     * @return
     */
    OrderMaster selectByPrimaryKey(@Param("orderId") String orderId,@Param("userId") Integer userId);

    /**
     * 查找全部
     *
     * @return
     */
    List<OrderMaster> selectAll(Integer userId);

    int updateByPrimaryKeySelective(@Param("orderMaster")OrderMaster record,@Param("userId") Integer userId);

    int updateByPrimaryKey(@Param("orderMaster")OrderMaster record,@Param("userId") Integer userId);

    int insertSelective(@Param("orderMaster")OrderMaster record,@Param("userId") Integer userId);
}