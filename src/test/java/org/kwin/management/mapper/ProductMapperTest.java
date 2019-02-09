package org.kwin.management.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kwin.management.dao.ProductMapper;
import org.kwin.management.entity.Product;
import org.kwin.management.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class ProductMapperTest {

    @Autowired
    ProductMapper productMapper;

    @Test
    public void insert() {
        Product product = new Product();
        product.setProductId(KeyUtil.getUniqueKey());
        product.setProductType("K320");
        product.setProductStock(100);
        product.setProductSize("200");
        product.setProductPrice(2000);
        product.setProductDirection(1);
        log.info("product={}", product);

        int insert = productMapper.insert(product);
//        assertNotEquals(0, insert);
    }

    @Test
    public void selectByPrimaryKey() {
        Product product = productMapper.selectByPrimaryKey("1549681971144709200");
        log.info("product={}", product);
    }

    @Test
    public void selectAll() {
        List<Product> products = productMapper.selectAll();
        log.info("product={}", products);
    }

    @Test
    public void updateByPrimaryKeySelective() {
        Product product = new Product();
        product.setProductId("1549681971144709200");
        product.setProductPrice(1000);

        log.info("product={}", product);
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Test
    public void deleteByPrimaryKey() {
        productMapper.deleteByPrimaryKey("1549682508704381700");
    }
}