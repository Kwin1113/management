package org.kwin.management.controller;

import org.kwin.management.dto.OrderDTO;
import org.kwin.management.entity.Installer;
import org.kwin.management.entity.OrderDetail;
import org.kwin.management.entity.OrderMaster;
import org.kwin.management.entity.Product;
import org.kwin.management.enums.OrderStatusEnum;
import org.kwin.management.form.OrderForm;
import org.kwin.management.form.OrderMasterForm;
import org.kwin.management.form.ProductAddForm;
import org.kwin.management.service.InstallerService;
import org.kwin.management.service.OrderService;
import org.kwin.management.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    InstallerService installerService;

    @ResponseBody
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> model) {
        List<OrderMaster> orderMasterList = orderService.selectAll();
        model.put("orderMasterList", orderMasterList);
        List<Installer> installerList = installerService.list();
        model.put("installerList", installerList);
        return new ModelAndView("order/list", model);
    }

    @ResponseBody
    @GetMapping("/selectOne/{orderId}")
    public ModelMap selectOne(@PathVariable String orderId) {
        ModelMap model = new ModelMap();
        OrderDTO orderDTO = orderService.selectOne(orderId);
        model.addAttribute("order", orderDTO);
        return model;
    }

    @ResponseBody
    @GetMapping("/detail/{orderId}")
    public ModelAndView selectOne(@PathVariable String orderId, Map<String, Object> model) {
        OrderDTO orderDTO = orderService.selectOne(orderId);
        List<Installer> installerList = installerService.list();
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        model.put("orderMaster", orderMaster);
        model.put("orderDetailList", orderDetailList);
        model.put("installerList", installerList);

        return new ModelAndView("order/detail", model);
    }

    @GetMapping("/cancel/{orderId}")
    public String cancel(@PathVariable String orderId) {
        orderService.cancel(orderId);
        return "redirect:/order/list";
    }

    @PostMapping(value = "/add")
    public String add(@Valid OrderForm orderForm, BindingResult result) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderForm, orderDTO);
        List<ProductAddForm> productAddFormList = orderForm.getProductAddFormList();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (ProductAddForm productAddForm : productAddFormList) {
            OrderDetail orderDetail = new OrderDetail();
            Product product = productService.selectByTypeAndSizeAndDirection(productAddForm);
            BeanUtils.copyProperties(product, orderDetail);
            orderDetail.setProductQuantity(productAddForm.getProductQuantity());
            orderDetailList.add(orderDetail);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        orderService.add(orderDTO);
        return "redirect:/order/list";
    }

    @PostMapping("/update")
    public String modify(OrderMasterForm orderMasterForm) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMasterForm, orderDTO);
        orderService.update(orderDTO);
        return "redirect:/order/list";
    }

    @GetMapping("/finish/{orderId}")
    public String finish(@PathVariable String orderId) {
        OrderDTO orderDTO = orderService.selectOne(orderId);
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderService.update(orderDTO);
        return "redirect:/order/list";
    }
}
