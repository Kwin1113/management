package org.kwin.management.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kwin.management.dao.ProductMapper;
import org.kwin.management.dto.OrderDTO;
import org.kwin.management.entity.OrderDetail;
import org.kwin.management.entity.OrderMaster;
import org.kwin.management.entity.Product;
import org.kwin.management.service.OrderService;
import org.kwin.management.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImpTest {

    @Autowired
    OrderService orderService;
    @Autowired
    ProductMapper productMapper;

    @Test
    public void selectAll() {
        List<OrderMaster> orderMasterList = orderService.selectAll();
        log.info("orderMasterList={}", orderMasterList);
    }

    @Test
    public void selectOne() {
        OrderDTO orderDTO = orderService.selectOne("1549697698780950935");
        log.info("orderDTO={}", orderDTO);
    }

    @Test
    public void add() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("302");
        orderDTO.setBuyerName("Kwin");
        orderDTO.setBuyerPhone("18989464565");
        orderDTO.setInstallerId(0);
        orderDTO.setInstallTime(new Date());
        orderDTO.setOrderDeposit(100);
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1549681971144709200");
        o1.setProductQuantity(2);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("1549683076017404166");
        o2.setProductQuantity(1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO dto = orderService.add(orderDTO);
        log.info("orderDTO={}", dto);
    }

    @Test
    public void update() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1549697698780950935");
        orderDTO.setBuyerName("kwin");
        orderDTO.setInstallerId(1);
        orderDTO.setOrderStatus(1);

        OrderDTO dto = orderService.update(orderDTO);
        log.info("dto={}", dto);
    }

    @Test
    public void cancel() {
        orderService.cancel("1549697698780950935");
    }
}