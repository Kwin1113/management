package org.kwin.management.dao;

import org.apache.ibatis.annotations.Param;
import org.kwin.management.entity.Product;

import java.util.List;

public interface ProductMapper {
    /**
     * 删除
     *
     * @param productId
     * @return
     */
    int deleteByPrimaryKey(@Param("productId") String productId,@Param("userId") Integer userId);

    /**
     * 增加
     *
     * @param record
     * @return
     */
    int insert(@Param("product") Product record,@Param("userId") Integer userId);

    /**
     * 查找
     *
     * @param productId
     * @return
     */
    Product selectByPrimaryKey(@Param("productId") String productId,@Param("userId") Integer userId);

    /**
     * 查找所有
     *
     * @return
     */
    List<Product> selectAll(Integer userId);

    Product selectByTypeAndSizeAndDirection(@Param("userId")Integer userId,@Param("productType") String productType,@Param("productSize") String productSize,
                                            @Param("productDirection")Integer productDirection);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(@Param("product") Product record,@Param("userId") Integer userId);

    int updateByPrimaryKey(@Param("product") Product record,@Param("userId") Integer userId);

    int insertSelective(@Param("product") Product record,@Param("userId") Integer userId);
}