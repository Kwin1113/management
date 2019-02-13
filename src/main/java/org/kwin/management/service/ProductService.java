package org.kwin.management.service;

import org.kwin.management.dto.CartDTO;
import org.kwin.management.entity.Product;
import org.kwin.management.form.ProductAddForm;

import java.util.List;

public interface ProductService {

    List<Product> selectALl();

    Product selectOne(String productId);

    Product selectByTypeAndSizeAndDirection(ProductAddForm productAddForm);

    void add(Product product);

    int deleteById(String productId);

    void update(Product product);

    void descStock(List<CartDTO> cartDTOList);

    void incrStock(List<CartDTO> cartDTOList);
}
