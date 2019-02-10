package org.kwin.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.kwin.management.entity.Installer;
import org.kwin.management.entity.OrderDetail;
import org.kwin.management.entity.OrderMaster;
import org.kwin.management.entity.Product;
import org.kwin.management.form.ProductAddForm;
import org.kwin.management.service.InstallerService;
import org.kwin.management.service.OrderService;
import org.kwin.management.service.ProductService;
import org.kwin.management.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/detail")
public class OrderDetailController {

    @Autowired
    OrderService orderService;

    @Autowired
    InstallerService installerService;

    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> model) {
        List<OrderDetail> orderDetailList = orderService.ListAll();
        List<OrderMaster> orderMasterList = orderService.selectAll();
        List<Installer> installerList = installerService.list();
        model.put("orderDetailList", orderDetailList);
        model.put("orderMasterList", orderMasterList);
        model.put("installerList", installerList);
        return new ModelAndView("detail/list", model);
    }

    @ResponseBody
    @GetMapping("/selectOne/{detailId}")
    public ModelMap selectOne(@PathVariable String detailId) {
        ModelMap model = new ModelMap();
        OrderDetail orderDetail = orderService.selectOneDetail(detailId);
        model.addAttribute("orderDetail", orderDetail);
        return model;
    }

    @PostMapping("/add")
    public String add(String orderId,ProductAddForm productAddForm) {
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(productAddForm, orderDetail);
        orderDetail.setOrderId(orderId);
        Product product = productService.selectByTypeAndSizeAndDirection(productAddForm);
        orderDetail.setProductId(product.getProductId());
        orderDetail.setDetailId(KeyUtil.getUniqueKey());
        orderDetail.setProductPrice(product.getProductPrice());
        orderService.addDetail(orderDetail);
        return "redirect:/order/detail/" + orderId;
    }

}
