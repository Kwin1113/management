package org.kwin.management.service;

import org.kwin.management.dto.CartDTO;
import org.kwin.management.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> selectALl();

    Product selectOne(String productId);

    int add(Product product);

    int deleteById(String productId);

    int update(Product product);

    void descStock(List<CartDTO> cartDTOList);

    void incrStock(List<CartDTO> cartDTOList);
}
