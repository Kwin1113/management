package org.kwin.management.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.kwin.management.common.enums.ResultEnum;
import org.kwin.management.common.exception.SysException;
import org.kwin.management.entity.Product;
import org.kwin.management.form.ProductForm;
import org.kwin.management.service.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @ResponseBody
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> model) {
        List<Product> productList = productService.selectALl();
        model.put("productList", productList);
        return new ModelAndView("product/list", model);
    }

    @ResponseBody
    @GetMapping("/selectOne/{productId}")
    public ModelMap selectOne(@PathVariable String productId) {
        ModelMap model = new ModelMap();
        Product product = productService.selectOne(productId);
        model.addAttribute("product", product);
        return model;
    }

    @ResponseBody
    @PostMapping("/update")
    public ModelMap modify(ProductForm productForm) {
        ModelMap model = new ModelMap();
        Product product = productService.selectOne(productForm.getProductId());
        BeanUtils.copyProperties(productForm, product);
        try {
            productService.update(product);
        } catch (SysException e) {
            log.error("[修改商品]商品已存在={}", e.getMsg());
            model.addAttribute("msg", e.getMsg());
            return model;
        }
        model.addAttribute("msg", ResultEnum.SUCCESS.getMessage());
        return model;
    }

    @GetMapping("/delete/{productId}")
    public String delete(@PathVariable String productId) {
        productService.deleteById(productId);
        return "redirect:/product/list";
    }

    @ResponseBody
    @PostMapping("/add")
    public ModelMap add(@Valid ProductForm productForm, BindingResult result) {
        ModelMap model = new ModelMap();
        try {
            productService.add(productForm);
        } catch (SysException e) {
            log.error("[新增商品]商品已存在={}", e.getMsg());
            model.addAttribute("msg", e.getMsg());
            return model;
        }
        model.addAttribute("msg", ResultEnum.SUCCESS.getMessage());
        return model;
    }
}
