package org.kwin.management.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kwin.management.dto.CartDTO;
import org.kwin.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProductServiceImpTest {

    @Autowired
    ProductService productService;

    @Test
    public void incrStock() {
        CartDTO c1 = new CartDTO("1549681971144709200", 100);
        CartDTO c2 = new CartDTO("1549683076017404166", 100);
        List<CartDTO> cartDTOList = new ArrayList<>();
        cartDTOList.add(c1);
        cartDTOList.add(c2);

        productService.incrStock(cartDTOList);
    }
}