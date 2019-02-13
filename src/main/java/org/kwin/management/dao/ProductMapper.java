package org.kwin.management.dao;

import org.kwin.management.entity.Product;

import java.util.List;

public interface ProductMapper {
    /**
     * 删除
     *
     * @param productId
     * @return
     */
    int deleteByPrimaryKey(String productId);

    /**
     * 增加
     *
     * @param record
     * @return
     */
    int insert(Product record);

    /**
     * 查找
     *
     * @param productId
     * @return
     */
    Product selectByPrimaryKey(String productId);

    /**
     * 查找所有
     *
     * @return
     */
    List<Product> selectAll();

    Product selectByTypeAndSizeAndDirection(String productType, String productSize, Integer productDirection);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    int insertSelective(Product record);
}