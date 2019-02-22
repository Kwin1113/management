package org.kwin.management.dao;

import org.apache.ibatis.annotations.Param;
import org.kwin.management.entity.OrderDetail;

import java.util.List;

public interface OrderDetailMapper {
    /**
     * 删除
     *
     * @param detailId
     * @return
     */
    int deleteByPrimaryKey(@Param("detailId")String detailId,@Param("userId") Integer userId);

    /**
     * 增加
     *
     * @param record
     * @return
     */
    int insert(@Param("orderDetail")OrderDetail record,@Param("userId") Integer userId);

    /**
     * 按详情id查找
     *
     * @param detailId
     * @return
     */
    OrderDetail selectByPrimaryKey(@Param("detailId")String detailId,@Param("userId") Integer userId);

    List<OrderDetail> selectByOrderId(@Param("orderId")String orderId,@Param("userId") Integer userId);

    List<OrderDetail> selectAll(Integer userId);

    int updateByPrimaryKeySelective(@Param("orderDetail")OrderDetail record,@Param("userId") Integer userId);

    int updateByPrimaryKey(@Param("orderDetail")OrderDetail record,@Param("userId") Integer userId);

    int insertSelective(@Param("orderDetail")OrderDetail record,@Param("userId") Integer userId);
}